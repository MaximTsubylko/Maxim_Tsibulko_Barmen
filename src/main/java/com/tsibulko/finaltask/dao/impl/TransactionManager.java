package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.dao.AbstractJdbcDao;
import com.tsibulko.finaltask.dao.ConnectionPool;
import com.tsibulko.finaltask.dao.ConnectionPoolFactory;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of transaction with DAO
 */
public final class TransactionManager {
    private Connection proxyConnection;
    private List<GenericDAO> currentDaos;

    public void begin(GenericDAO dao, GenericDAO... daos) throws DaoException {
        try {
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            proxyConnection = connectionPool.retrieveConnection();
            setConnectionWithReflection(dao, proxyConnection);
            for (GenericDAO d : daos) {
                setConnectionWithReflection(d, proxyConnection);
            }

            currentDaos = new ArrayList<>(daos.length + 1);
            currentDaos.addAll(Arrays.asList(daos));
            currentDaos.add(dao);

        } catch (ConnectionPoolException e) {
            throw new DaoException("Failed to get a connection from CP.", e);
        }
    }

    public void end() throws DaoException {
        try {
            for (GenericDAO d : currentDaos) {
                setConnectionWithReflection(d, null);
            }
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            connectionPool.putBackConnection(proxyConnection);
            proxyConnection = null;
            currentDaos = null;
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public void commit() throws SQLException {
        proxyConnection.commit();
    }

    public void rollback() throws SQLException {
        proxyConnection.rollback();
    }

    static void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DaoException("DAO implementation does not extend AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, connection);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DaoException("Failed to set connection for transactional DAO. ", e);
        }
    }
}
