package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.dao.exception.DaoException;

public interface DaoFactory {
    GenericDAO getDao(Class entityClass) throws DaoException;
}