package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.dao.AbstractJdbcDao;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.GenericDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarmenFeedbackDAO extends AbstractJdbcDao<BarmenFeedback, Integer> implements GenericDAO<BarmenFeedback, Integer> {

    @Override
    protected List<BarmenFeedback> parseResultSet(ResultSet resultSet) throws DaoException {
        List<BarmenFeedback> resultList = new ArrayList<>();
        BarmenFeedback barmenFeedback = new BarmenFeedback();
        try {
            while (resultSet.next()) {
                barmenFeedback.setId(resultSet.getInt("id"));
                barmenFeedback.setFromUserId(resultSet.getInt("from_user_id"));
                barmenFeedback.setToUserId(resultSet.getInt("to_user_id"));
                barmenFeedback.setTitle(resultSet.getString("title"));
                barmenFeedback.setMark(resultSet.getInt("mark"));
                barmenFeedback.setComment(resultSet.getString("comment"));
                resultList.add(barmenFeedback);
            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t parse barman feedback result set!");
        }
        return resultList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, BarmenFeedback barmenFeedback) throws DaoException {
        statementPreparation(statement, barmenFeedback);
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, BarmenFeedback barmenFeedback) throws DaoException {
        statementPreparation(statement, barmenFeedback);
        try {
            statement.setInt(statement.getParameterMetaData().getParameterCount(), barmenFeedback.getId());
        } catch (SQLException e) {
            throw new DaoException(e, "Cun`t run statement for update barmen feedback!");
        }
    }

    @Override
    protected boolean hasColumn(String column) {
        return Arrays.asList("id", "from_user_id", "title", "to_user_id", "mark", "comment")
                .contains(column);
    }

    @Override
    protected String getSelectColumnQuery() {
        return "FROM barmen_feedback";
    }


    private void statementPreparation(PreparedStatement statement, BarmenFeedback barmenFeedback) throws DaoException {
        int i = 0;
        try {
            statement.setInt(++i, barmenFeedback.getFromUserId());
            statement.setInt(++i, barmenFeedback.getToUserId());
            statement.setString(++i, barmenFeedback.getTitle());
            statement.setInt(++i, barmenFeedback.getMark());
            statement.setString(++i, barmenFeedback.getComment());
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t prepare barmen feedback statement for using!");
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM barmen_feedback";
    }

    @Override
    public String getPersistQuery() {
        return "INSERT INTO barmen_feedback (from_user_id, to_user_id, title, mark, comment)" +
                " VALUES (?,?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE barmen_feedback set from_user_id = ?, to_user_id = ?, title = ?, mark = ?, comment = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM barmen_feedback WHERE id = ?";
    }

}
