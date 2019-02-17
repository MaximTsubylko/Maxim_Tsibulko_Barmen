package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.dao.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CocktaileFeedbackDAO extends AbstractJdbcDao<CocktaileFeedback, Integer> implements GenericDAO<CocktaileFeedback, Integer> {

    @Override
    protected List<CocktaileFeedback> parseResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, CocktaileFeedback cocktaileFeedback) throws SQLException {
        int i = 0;
        statement.setInt(++i, cocktaileFeedback.getFromUserId());
        statement.setInt(++i, cocktaileFeedback.getToCocktileId());
        statement.setString(++i, cocktaileFeedback.getTitle());
        statement.setInt(++i, cocktaileFeedback.getMark());
        statement.setString(++i, cocktaileFeedback.getComment());
        statement.executeUpdate();
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, CocktaileFeedback cocktaileFeedback) throws SQLException {
        statement.setInt(1, cocktaileFeedback.getId());
        statement.executeUpdate();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, CocktaileFeedback cocktaileFeedback) throws SQLException {
        int i = 0;
        statement.setInt(++i, cocktaileFeedback.getFromUserId());
        statement.setInt(++i, cocktaileFeedback.getToCocktileId());
        statement.setString(++i, cocktaileFeedback.getTitle());
        statement.setInt(++i, cocktaileFeedback.getMark());
        statement.setString(++i, cocktaileFeedback.getComment());
        statement.executeUpdate();
    }

    @Override
    protected CocktaileFeedback prepareStatementForGet(PreparedStatement statement) throws SQLException {
        CocktaileFeedback cocktaileFeedback = new CocktaileFeedback();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {

            cocktaileFeedback.setFromUserId(resultSet.getInt("user_id"));
            cocktaileFeedback.setToCocktileId(resultSet.getInt("cocktail_id"));
            cocktaileFeedback.setMark(resultSet.getInt("mark"));
            cocktaileFeedback.setTitle(resultSet.getString("title"));
            cocktaileFeedback.setComment(resultSet.getString("comment"));
            cocktaileFeedback.setId(resultSet.getInt("id"));
        }
        return cocktaileFeedback;
    }

    @Override
    protected List<CocktaileFeedback> prepareStatementForGetAll(PreparedStatement statement) throws SQLException {
        List<CocktaileFeedback> resultList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            CocktaileFeedback cocktaileFeedback = new CocktaileFeedback();
            cocktaileFeedback.setId(resultSet.getInt("id"));
            cocktaileFeedback.setFromUserId(resultSet.getInt("user_id"));
            cocktaileFeedback.setToCocktileId(resultSet.getInt("cocktail_id"));
            cocktaileFeedback.setTitle(resultSet.getString("title"));
            cocktaileFeedback.setMark(resultSet.getInt("mark"));
            cocktaileFeedback.setComment(resultSet.getString("comment"));
            resultList.add(cocktaileFeedback);
        }
        return resultList;
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM cocktaile_feedback WHERE id=?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM cocktaile_feedback";
    }

    @Override
    public String getPersistQuery() {
        return "INSERT INTO cocktaile_feedback (user_id, cocktail_id, title, mark, comment) " +
                "VALUES (?,?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE cocktaile_feedback set user_id=?, cocktail_id = ?, title = ?, mark = ?, comment = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM cocktaile_feedback WHERE id = ?";
    }

    @AutoConnection
    @Override
    public Optional<CocktaileFeedback> getByPK(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
            statement.setInt(1, id);
            return Optional.of(prepareStatementForGet(statement));
        }
    }

    @AutoConnection
    @Override
    public List<CocktaileFeedback> getAll() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getSelectAllQuery())) {
            return prepareStatementForGetAll(statement);
        }
    }

    @AutoConnection
    @Override
    public CocktaileFeedback persist(CocktaileFeedback cocktaileFeedback) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getPersistQuery())) {
            prepareStatementForInsert(statement, cocktaileFeedback);
            return cocktaileFeedback;
        }
    }

    @AutoConnection
    @Override
    public void delete(CocktaileFeedback cocktaileFeedback) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, cocktaileFeedback);
        }
    }

    @AutoConnection
    @Override
    public void update(CocktaileFeedback cocktaileFeedback) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, cocktaileFeedback);
        }
    }
}
