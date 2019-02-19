package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;

import java.sql.SQLException;

public interface CRUDService<T> {
    T create(T obj) throws SQLException, DaoException, PersistException;
    void delete(T obj) throws SQLException, PersistException, DaoException;
    T getByPK(Integer id) throws DaoException, SQLException, InterruptedException;
    void update(T obj) throws DaoException, SQLException, PersistException;
}
