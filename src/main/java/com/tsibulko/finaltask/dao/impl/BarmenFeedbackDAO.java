package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BarmenFeedbackDAO extends AbstractJdbcDao<BarmenFeedback, Integer> implements GenericDAO<BarmenFeedback, Integer> {

    @Override
    protected List<BarmenFeedback> parseResultSet(ResultSet resultSet) throws SQLException {
        List<BarmenFeedback> resultList = new ArrayList<>();
        BarmenFeedback barmenFeedback = new BarmenFeedback();
        while (resultSet.next()) {
            barmenFeedback.setId(resultSet.getInt("id"));
            barmenFeedback.setFromUserId(resultSet.getInt("from_user_id"));
            barmenFeedback.setToUserId(resultSet.getInt("to_user_id"));
            barmenFeedback.setTitle(resultSet.getString("title"));
            barmenFeedback.setMark(resultSet.getInt("mark"));
            barmenFeedback.setComment(resultSet.getString("comment"));
            resultList.add(barmenFeedback);
        }
        return resultList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, BarmenFeedback barmenFeedback) throws SQLException {
        statementPreparation(statement,barmenFeedback);
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, BarmenFeedback barmenFeedback) throws SQLException {
        statementPreparation(statement,barmenFeedback);
        statement.setInt(statement.getParameterMetaData().getParameterCount(),barmenFeedback.getId());
    }


    private void statementPreparation(PreparedStatement statement, BarmenFeedback barmenFeedback) throws SQLException {
        int i = 0;
        statement.setInt(++i, barmenFeedback.getFromUserId());
        statement.setInt(++i, barmenFeedback.getToUserId());
        statement.setString(++i, barmenFeedback.getTitle());
        statement.setInt(++i, barmenFeedback.getMark());
        statement.setString(++i, barmenFeedback.getComment());
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
