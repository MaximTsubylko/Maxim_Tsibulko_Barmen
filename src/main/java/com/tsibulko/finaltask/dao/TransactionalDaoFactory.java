package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.dao.exception.DaoException;

import java.io.Serializable;

public interface TransactionalDaoFactory {
    /**
     * Get generic DAO of entity without connection
     * @param entityClass
     * @return transactional DAO
     * @throws DaoException should be clarify
     */
    <T extends Identified<PK>, PK extends Serializable> GenericDAO<T, PK> getTransactionalDao(Class<T> entityClass) throws DaoException;
}