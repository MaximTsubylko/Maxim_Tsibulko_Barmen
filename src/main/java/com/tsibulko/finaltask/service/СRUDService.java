package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;

import java.sql.SQLException;

public interface СRUDService<T> {
    T create(T obj) throws SQLException, DaoException, PersistException;
    void delete(T obj) throws SQLException, PersistException;
}
