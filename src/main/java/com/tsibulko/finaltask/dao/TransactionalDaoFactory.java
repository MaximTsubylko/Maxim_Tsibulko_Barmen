package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.dao.exception.DaoException;

public interface TransactionalDaoFactory<T> {

    GenericDAO getTransactionalDao(Class entityClass, T connection) throws DaoException;
}