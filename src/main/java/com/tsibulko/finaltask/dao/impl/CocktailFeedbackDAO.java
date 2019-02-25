package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CocktailFeedbackDAO extends AbstractJdbcDao<CocktaileFeedback, Integer> implements GenericDAO<CocktaileFeedback, Integer> {

    @Override
    protected List<CocktaileFeedback> parseResultSet(ResultSet resultSet) throws SQLException {
        List<CocktaileFeedback> resultList = new ArrayList<>();
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
    protected void prepareStatementForInsert(PreparedStatement statement, CocktaileFeedback cocktaileFeedback) throws SQLException {
        statementPreparation(statement,cocktaileFeedback);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, CocktaileFeedback cocktaileFeedback) throws SQLException {
        statementPreparation(statement, cocktaileFeedback);
        statement.setInt(statement.getParameterMetaData().getParameterCount(), cocktaileFeedback.getId());
    }


    @Override
    protected boolean hasColumn(String column) {
        return Arrays.asList("id", "user_id", "title", "cocktail_id", "mark", "comment")
                .contains(column);
    }

    @Override
    protected String getSelectColumnQuery() {
        return "FROM cocktaile_feedback";
    }


    private void statementPreparation(PreparedStatement statement, CocktaileFeedback cocktaileFeedback) throws SQLException {
        int i = 0;
        statement.setInt(++i, cocktaileFeedback.getFromUserId());
        statement.setInt(++i, cocktaileFeedback.getToCocktileId());
        statement.setString(++i, cocktaileFeedback.getTitle());
        statement.setInt(++i, cocktaileFeedback.getMark());
        statement.setString(++i, cocktaileFeedback.getComment());
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM cocktaile_feedback";
    }

    @Override
    public String getPersistQuery() {
        return "INSERT INTO cocktaile_feedback (user_id, cocktail_id, title, mark, comment) " +
                "VALUES (?,?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE cocktaile_feedback set user_id=?, cocktail_id = ?, title = ?, mark = ?, comment = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM cocktaile_feedback WHERE id = ?";
    }

}