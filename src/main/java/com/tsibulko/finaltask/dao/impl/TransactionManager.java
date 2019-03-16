package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.dao.*;

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

    public void begin(GenericDAO... daos) throws DaoException {
        currentDaos = new ArrayList<>(daos.length + 1);
        try {
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            proxyConnection = connectionPool.retrieveConnection();

            try {
                proxyConnection.setAutoCommit(false);
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }

            for (GenericDAO d : daos) {
                currentDaos.add(d);
                setConnectionWithReflection(d, proxyConnection);
            }


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

    public void commit() throws DaoException{
        try {
            proxyConnection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void rollback() throws DaoException {
        try {
            proxyConnection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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
