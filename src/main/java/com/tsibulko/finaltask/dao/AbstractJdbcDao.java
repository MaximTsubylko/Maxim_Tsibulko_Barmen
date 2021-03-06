package com.tsibulko.finaltask.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements GenericDAO<T, PK> {
    protected Connection connection;

    protected abstract List<T> parseResultSet(ResultSet rs) throws DaoException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DaoException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DaoException;

    protected abstract boolean hasColumn(String column);

    protected abstract String getSelectColumnQuery();

    public abstract String getSelectQuery();

    public abstract String getPersistQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();


    @Override
    @AutoConnection
    public Optional<T> getByPK(PK key) throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE id = " + key)) {
            return parseResultSet(preparedStatement.executeQuery()).stream().findFirst();
        } catch (SQLException e) {
            throw new DaoException(e, "Get by PK error");
        }
    }

    @Override
    @AutoConnection
    public List<T> getAll() throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSelectQuery())) {
            return parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            throw new DaoException(e, "Get all error");
        }
    }

    @Override
    @AutoConnection
    public T persist(T object) throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getPersistQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(preparedStatement, object);
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setId(generatedKeys.getInt(1));
                    return object;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e, "Persist error");
        }
    }

    @Override
    @AutoConnection
    public void update(T object) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(preparedStatement, object);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e, "Update error");
        }
    }

    @Override
    @AutoConnection
    public void delete(T object) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
            preparedStatement.setInt(1, (Integer) object.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e, "Delete error");
        }
    }

    @Override
    @AutoConnection
    public List<String> findStringsFromColumn(String column) throws DaoException {
        if (!hasColumn(column)) {
            throw new DaoException("This column does not exist");
        }
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement("SELECT " + column + " " + getSelectColumnQuery())) {
            List<String> strings = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                strings.add(rs.getString(column));
            }
            return strings;
        } catch (SQLException e) {
            throw new DaoException(e, "Get string from column error");
        }
    }
}
