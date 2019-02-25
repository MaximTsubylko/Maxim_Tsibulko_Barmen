package com.tsibulko.finaltask.dao;


import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T extends Identified<PK>, PK extends Serializable> {

    Optional<T> getByPK(PK id) throws DaoException, SQLException, InterruptedException;

    List<T> getAll() throws SQLException, DaoException;

    T persist(T daoObject) throws SQLException, PersistException;

    void delete(T id) throws SQLException, PersistException;

    void update(T daoObject) throws SQLException, PersistException;

    List<String> getStringsFromColumn(String column) throws DaoException;
}
