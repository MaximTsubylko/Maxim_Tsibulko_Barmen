package com.tsibulko.finaltask.dao;


import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.impl.JDBCConnectionPool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connection Pool Factory
 */
public class ConnectionPoolFactory {
    private static ConnectionPoolFactory instance;
    private static Lock lock = new ReentrantLock();

    private ConnectionPoolFactory() {
    }

    public static ConnectionPoolFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConnectionPoolFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public ConnectionPool getConnectionPool() throws ConnectionPoolException {
        return JDBCConnectionPool.getInstance();
    }
}
