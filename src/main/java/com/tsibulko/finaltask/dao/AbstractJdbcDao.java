package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements GenericDAO<T, PK> {
    protected Connection connection;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    protected abstract T prepareStatementForGet(PreparedStatement statement) throws SQLException;

    protected abstract List<T> prepareStatementForGetAll(PreparedStatement statement) throws SQLException;

    public abstract String getSelectQuery();

    public abstract String getSelectAllQuery();

    public abstract String getPersistQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

}
