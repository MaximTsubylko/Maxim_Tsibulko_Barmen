package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.*;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * Jdbc DAO Factory
 */
public class JdbcDaoFactory implements DaoFactory, TransactionalDaoFactory {
    private static JdbcDaoFactory instance;
    private static Lock lock = new ReentrantLock();
    private Map<Class, Supplier<GenericDAO>> creators = new HashMap<>();

    private class DaoInvocationHandler implements InvocationHandler {
        private GenericDAO dao;

        DaoInvocationHandler(GenericDAO dao) {
            this.dao = dao;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;

            if (Arrays.stream(dao.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(AutoConnection.class))
                    .map(Method::getName)
                    .anyMatch(m -> m.equals(method.getName()))) {

                ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
                Connection connection = connectionPool.retrieveConnection();

                TransactionManager.setConnectionWithReflection(dao, connection);

                result = method.invoke(dao, args);

                connectionPool.putBackConnection(connection);
                TransactionManager.setConnectionWithReflection(dao, null);

            } else {
                result = method.invoke(dao, args);
            }

            return result;
        }

    }

    private JdbcDaoFactory() {
        creators.put(Customer.class, CustomerDAO::new);
        creators.put(Cocktaile.class, CocktaileDAO::new);
        creators.put(CocktaileFeedback.class, CocktaileFeedbackDAO::new);
        creators.put(BarmenFeedback.class, BarmenFeedbackDAO::new);
        creators.put(Ingredient.class, IngredientDAO::new);
    }

    public static JdbcDaoFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new JdbcDaoFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> GenericDAO<T, PK> getDao(Class<T> entityClass) throws DaoException {
        Supplier<GenericDAO> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }
        GenericDAO dao = daoCreator.get();

        return (GenericDAO) Proxy.newProxyInstance(dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                new DaoInvocationHandler(dao));
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> GenericDAO<T, PK> getTransactionalDao(Class<T> entityClass) throws DaoException {
        Supplier<GenericDAO> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }

        return daoCreator.get();
    }
}
