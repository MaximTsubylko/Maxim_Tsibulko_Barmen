package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.dao.ConnectionPool;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of Connection Pool
 */
public class JDBCConnectionPool implements ConnectionPool {
    private static ConnectionPool instance;
    private static final String DB_PATH = "property/database.properties";
    private static final String CLOSE_REGEX = ".*close.*";

    private Queue<Connection> availableConnection;
    private Queue<Connection> usedConnection;
    private Semaphore semaphore;
    private Lock safeLock;
    private AtomicInteger counter;

    private String url;
    private String user;
    private String password;
    private int poolCapacity;

    private static Lock lock = new ReentrantLock();

    private JDBCConnectionPool() {
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        lock.lock();
        try {
            if (instance == null) {
                instance = new JDBCConnectionPool();
                instance.init();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public void init() throws ConnectionPoolException {//???
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(DB_PATH)) {
            properties.load(inputStream);
            url = properties.getProperty("url") + "?" +
                    "useUnicode=" + properties.getProperty("useUnicode") + "&" +
                    "characterEncoding=" + properties.getProperty("characterEncoding") + "&" +
                    "autoReconnect=" + properties.getProperty("autoReconnect");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            poolCapacity = Integer.parseInt(properties.getProperty("poolCapacity"));
            Class.forName(properties.getProperty("driver"));
            semaphore = new Semaphore(poolCapacity);
            availableConnection = new LinkedList<>();
            usedConnection = new LinkedList<>();
            safeLock = new ReentrantLock();
            counter = new AtomicInteger();
        } catch (IOException | ClassNotFoundException e) {
            throw new ConnectionPoolException("Initialize error.", e);
        }
    }

    @Override
    public Connection retrieveConnection() throws ConnectionPoolException {
        try {
            semaphore.acquire();

            if (availableConnection.size() + usedConnection.size() < poolCapacity && counter.get() < poolCapacity) {
                safeLock.lock();
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    usedConnection.add(connection);
                    counter.incrementAndGet();
                    return getProxyConnection(connection);
                } finally {
                    safeLock.unlock();
                }
            } else {
                safeLock.lock();
                try {
                    Connection connection = availableConnection.remove();
                    usedConnection.add(connection);
                    return getProxyConnection(connection);
                } finally {
                    safeLock.unlock();
                }

            }
        } catch (InterruptedException | SQLException e) {
            throw new ConnectionPoolException("Failed to get connection.", e);
        }
    }

    private Connection getProxyConnection(Connection connection) {
        return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    if (method.getName().matches(CLOSE_REGEX)) {
                        putBackConnection(connection);
                        return null;
                    }

                    return method.invoke(connection, args);
                });
    }

    @Override
    public void putBackConnection(Connection connection) {
        safeLock.lock();
        try {
            if (usedConnection.remove(connection) && availableConnection.add(connection)) {
                semaphore.release();
            }
        } finally {
            safeLock.unlock();
        }
    }

    @Override
    public void destroyPool() throws SQLException {
        List<Connection> connectionList = new ArrayList<>();
        connectionList.addAll(availableConnection);
        connectionList.addAll(usedConnection);
        for (Connection connection : connectionList) {
            connection.close();
        }
    }


}
