package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.dao.ConnectionPool;
import com.tsibulko.finaltask.dao.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
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

    private Queue<Connection> availableConnection;
    private Queue<Connection> usedConnection;
    private Semaphore semaphore;
    private Lock safeLock;
    private AtomicInteger counter;

    private String url;
    private String user;
    private String password;
    private int poolCapacity;

    private final Deque<Connection> connectionDeque = new ConcurrentLinkedDeque<>();
    private final List<Connection> allConnections = new ArrayList<>();


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

    public void init() throws ConnectionPoolException {
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
            if (connectionDeque.size() == 0) {
                return createConnection();
            }
            return connectionDeque.pop();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        }
    }

    @Override
    public void putBackConnection(Connection connection) {
        connectionDeque.push(connection);
        semaphore.release();
    }

    @Override
    public void destroyPool() throws ConnectionPoolException {
        List<Connection> connectionList = new ArrayList<>();
        try {
            connectionList.addAll(availableConnection);
            connectionList.addAll(usedConnection);
            for (Connection connection : connectionList) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Destroy pool error", e);
        }
    }

    private Connection createConnection() throws ConnectionPoolException {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            allConnections.add(connection);
            InvocationHandler connectionHandler = (Object proxy, Method method, Object[] args) -> {
                if (method.getName().equals("close")) {
                    putBackConnection((Connection) proxy);
                    return null;
                }
                return method.invoke(connection, args);
            };

            return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(),
                    connection.getClass().getInterfaces(), connectionHandler);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Error connection get", e);
        }
    }

}
