package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.*;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;
import org.hsqldb.rights.User;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class JdbcDaoFactory implements DaoFactory, TransactionalDaoFactory {
    private static volatile JdbcDaoFactory instance;
    private Map<Class, Supplier<GenericDAO>> creators = new HashMap<>();

    @Override
    public <T extends Identified<PK>, PK extends Serializable> GenericDAO<T, PK> getTransactionalDao(Class<T> entityClass) throws DaoException {
        Supplier<GenericDAO> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }

        return daoCreator.get();
    }

    private class DaoInvocationHandler implements InvocationHandler {
        private GenericDAO dao;

        DaoInvocationHandler(GenericDAO dao) {
            this.dao = dao;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Object result = null;
            if (Arrays.stream(Object.class.getMethods())
                    .map(Method::getName)
                    .noneMatch(m -> m.equals(method.getName()))) {

                ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
                Connection connection = connectionPool.retrieveConnection();

                setConnectionWithReflection(dao, connection);

                result = method.invoke(dao, args);

                connectionPool.putBackConnection(connection);
                setConnectionWithReflection(dao, null);
            } else {

                method.invoke(dao, args);
            }

            return result;
        }

    }

    public JdbcDaoFactory() {
        creators.put(Customer.class, CustomerDAO::new);
        creators.put(Cocktaile.class, CocktaileDAO::new);
        creators.put(CocktaileFeedback.class, CocktaileFeedbackDAO::new);
        creators.put(BarmenFeedback.class, BarmenFeedbackDAO::new);
        creators.put(Ingredient.class, IngredientDAO::new);
    }

    public static JdbcDaoFactory getInstance() {
        if (instance == null) {
            synchronized (JdbcDaoFactory.class) {
                if (instance == null) {
                    instance = new JdbcDaoFactory();
                }
            }
        }

        return instance;
    }

    @Override
    public GenericDAO getDao(Class entityClass) throws DaoException {
        Supplier<GenericDAO> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }

        GenericDAO dao = daoCreator.get();

        return (GenericDAO) Proxy.newProxyInstance(dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                new DaoInvocationHandler(dao));
    }


    private void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
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