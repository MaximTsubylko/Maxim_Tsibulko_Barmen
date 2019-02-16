package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.ConnectionPool;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.io.File;
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
    private static final String CLOSE_REGEX = ".*close.*";

    private volatile List<Connection> availableConnection;
    private volatile List<Connection> usedConnection;
    private Semaphore semaphore;
    private Lock lock;
    private AtomicInteger counter;
    private static Lock lockin = new ReentrantLock();

    private String JDBC_URL;
    private String USER;
    private String PASSWORD;
    private int POOL_CAPACITY;

    private static JDBCConnectionPool instance;

    private JDBCConnectionPool() {}

    public static JDBCConnectionPool getInstance() {
        lockin.lock();
        try {
            if (instance == null) {
                instance = new JDBCConnectionPool();
                instance.init();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lockin.unlock();
        }

        return instance;
    }

    @Override
    public Connection retrieveConnection() throws ConnectionPoolException {
        try {
            semaphore.acquire();

            if (counter.get() <= POOL_CAPACITY) {
                lock.lock();
                try {
                    Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                    usedConnection.add(connection);
                    counter.incrementAndGet();
                    return getProxyConnection(connection);
                } finally {
                    lock.unlock();
                }
            } else {
                lock.lock();
                try {
                    Connection connection = availableConnection.remove(0);
                    usedConnection.add(connection);
                    return getProxyConnection(connection);
                } finally {
                    lock.unlock();
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
                    }

                    return method.invoke(connection, args);
                });
    }

    @Override
    public void putBackConnection(Connection connection) {
        lock.lock();
        try {
            if (usedConnection.remove(connection) && availableConnection.add(connection)) {
                semaphore.release();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void destroyPool() {
        List<Connection> con = new ArrayList<>();
        con.addAll(availableConnection);
        con.addAll(usedConnection);
        con.forEach(c -> {
            try {
                c.close();
            } catch (SQLException e) {
            }
        });
    }

    private void initDriver(String driverClass) {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Driver cannot be found", e);
        }
    }
    private Integer getAvaid(){
        return semaphore.availablePermits();
    }//удалить нужен для дебага

    private void init() throws IOException {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream in = classLoader.getResourceAsStream("property/database.properties")) {
            properties.load(in);
            POOL_CAPACITY = Integer.valueOf(properties.getProperty("POOL_CAPACITY"));
            semaphore = new Semaphore(POOL_CAPACITY);
            availableConnection = new LinkedList<>();
            usedConnection = new LinkedList<>();
            lock = new ReentrantLock();
            counter = new AtomicInteger();
            this.JDBC_URL = properties.getProperty("URL");
            this.USER = properties.getProperty("USER");
            this.PASSWORD = properties.getProperty("PASSWORD");
            initDriver(properties.getProperty("DRIVERCLASS"));
//                    properties.getProperty("URL"),
//                    properties.getProperty("USER"),
//                    properties.getProperty("PASSWORD"),
//                    properties.getProperty("DRIVERCLASS"),
//                    Integer.valueOf(properties.getProperty("POOL_CAPACITY"));
        }
;

    }


}