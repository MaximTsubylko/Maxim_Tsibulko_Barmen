package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;

import java.sql.SQLException;
import java.util.List;

public interface CRUDService<T> {
    T create(T obj) throws SQLException, DaoException, PersistException, ServiceDateValidationException;

    void delete(T obj) throws SQLException, PersistException, DaoException, ServiceDateValidationException;

    T getByPK(Integer id) throws DaoException, SQLException, InterruptedException, ServiceDateValidationException;

    void update(T obj) throws DaoException, SQLException, PersistException, ServiceDateValidationException;

    List<T> getList() throws DaoException, SQLException;

}
