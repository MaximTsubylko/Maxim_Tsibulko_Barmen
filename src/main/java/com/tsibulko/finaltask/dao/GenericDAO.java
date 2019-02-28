package com.tsibulko.finaltask.dao;


import com.tsibulko.finaltask.dao.exception.DaoException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T extends Identified<PK>, PK extends Serializable> {

    Optional<T> getByPK(PK id) throws DaoException;

    List<T> getAll() throws DaoException;

    T persist(T daoObject) throws DaoException;

    void delete(T id) throws DaoException;

    void update(T daoObject) throws DaoException;

    List<String> getStringsFromColumn(String column) throws DaoException;
}
