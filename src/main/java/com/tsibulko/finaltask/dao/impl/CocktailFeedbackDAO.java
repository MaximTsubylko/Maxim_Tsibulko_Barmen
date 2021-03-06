package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.dao.AbstractJdbcDao;
import com.tsibulko.finaltask.dao.AutoConnection;
import com.tsibulko.finaltask.dao.CocktailFeedBackSpecificDAO;
import com.tsibulko.finaltask.dao.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CocktailFeedbackDAO extends AbstractJdbcDao<CocktaileFeedback, Integer> implements CocktailFeedBackSpecificDAO<CocktaileFeedback, Integer> {

    @Override
    protected List<CocktaileFeedback> parseResultSet(ResultSet resultSet) throws DaoException {
        List<CocktaileFeedback> resultList = new ArrayList<>();
        try {
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
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t parse cocktail feedback result set!");
        }
        return resultList;

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

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, CocktaileFeedback cocktaileFeedback) throws DaoException {
        statementPreparation(statement, cocktaileFeedback);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, CocktaileFeedback cocktaileFeedback) throws DaoException {
        statementPreparation(statement, cocktaileFeedback);
        try {
            statement.setInt(statement.getParameterMetaData().getParameterCount(), cocktaileFeedback.getId());
        } catch (SQLException e) {
            throw new DaoException(e, "Cun`t run statement for update cocktail feedback!");
        }
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


    private void statementPreparation(PreparedStatement statement, CocktaileFeedback cocktaileFeedback)
            throws DaoException {
        int i = 0;
        try {
            statement.setInt(++i, cocktaileFeedback.getFromUserId());
            statement.setInt(++i, cocktaileFeedback.getToCocktileId());
            statement.setString(++i, cocktaileFeedback.getTitle());
            statement.setInt(++i, cocktaileFeedback.getMark());
            statement.setString(++i, cocktaileFeedback.getComment());
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t prepare cocktail feedback statement for using!");
        }
    }

    @AutoConnection
    @Override
    public List<CocktaileFeedback> getCocktailFeedbacksByCocktail(Cocktail cocktail) throws DaoException {
        try {
            try (PreparedStatement statment = connection.prepareStatement(getSelectQuery() + " WHERE cocktail_id = ?")) {
                statment.setInt(1, cocktail.getId());
                return parseResultSet(statment.executeQuery());
            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t get cocktail feedback by customer");
        }
    }

    @AutoConnection
    @Override
    public double getMarkByCocktail(Cocktail cocktail) throws DaoException {
        try {
            try (PreparedStatement statment = connection.prepareStatement(getSelectQuery() + " WHERE cocktail_id = ?")) {
                statment.setInt(1, cocktail.getId());
                List<Integer> marks = new ArrayList<>();
                try (ResultSet resultSet = statment.executeQuery()) {
                    while (resultSet.next()) {
                        marks.add(resultSet.getInt("mark"));
                    }
                }
                return marks.stream().collect(Collectors.averagingDouble(m -> m));
            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t get mark by cocktail");
        }

    }

}
