package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.dao.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BarmenFeedbackDAO extends AbstractJdbcDao<BarmenFeedback, Integer> implements GenericDAO<BarmenFeedback, Integer> {

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, BarmenFeedback barmenFeedback) throws SQLException {
        int i = 0;
        statement.setInt(++i, barmenFeedback.getFromUserId());
        statement.setInt(++i, barmenFeedback.getToUserId());
        statement.setString(++i, barmenFeedback.getTitle());
        statement.setInt(++i, barmenFeedback.getMark());
        statement.setString(++i, barmenFeedback.getComment());
        statement.executeUpdate();
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, BarmenFeedback barmenFeedback) throws SQLException {
        statement.setInt(1, barmenFeedback.getId());
        statement.executeUpdate();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, BarmenFeedback barmenFeedback) throws SQLException {
        int i = 0;
        statement.setInt(++i, barmenFeedback.getFromUserId());
        statement.setInt(++i, barmenFeedback.getToUserId());
        statement.setString(++i, barmenFeedback.getTitle());
        statement.setInt(++i, barmenFeedback.getMark());
        statement.setString(++i, barmenFeedback.getComment());
        statement.executeUpdate();
    }

    @Override
    protected BarmenFeedback prepareStatementForGet(PreparedStatement statement) throws SQLException {
        BarmenFeedback barmenFeedback = new BarmenFeedback();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            barmenFeedback.setFromUserId(resultSet.getInt("from_user_id"));
            barmenFeedback.setToUserId(resultSet.getInt("to_user_id"));
            barmenFeedback.setMark(resultSet.getInt("mark"));
            barmenFeedback.setTitle(resultSet.getString("title"));
            barmenFeedback.setComment(resultSet.getString("comment"));
            barmenFeedback.setId(resultSet.getInt("id"));
        }
        return barmenFeedback;
    }

    @Override
    protected List<BarmenFeedback> prepareStatementForGetAll(PreparedStatement statement) throws SQLException {
        List<BarmenFeedback> resultList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            BarmenFeedback barmenFeedback = new BarmenFeedback();
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
    public String getSelectQuery() {
        return "SELECT * FROM barmen_feedback WHERE id=?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM barmen_feedback";
    }

    @Override
    public String getPersistQuery() {
        return "INSERT INTO barmen_feedback (from_user_id, to_user_id, title, mark, comment)" +
                " VALUES (?,?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE barmen_feedback set from_user_id = ?, to_user_id = ?, title = ?, mark = ?, comment = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM barmen_feedback WHERE id = ?";
    }

    @Override
    public Optional<BarmenFeedback> getByPK(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
            statement.setInt(1, id);
            return Optional.of(prepareStatementForGet(statement));
        }
    }

    @Override
    public List<BarmenFeedback> getAll() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getSelectAllQuery())) {
            return prepareStatementForGetAll(statement);
        }
    }


    @Override
    public void persist(BarmenFeedback barmenFeedback) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getPersistQuery())) {
            prepareStatementForInsert(statement, barmenFeedback);
        }
    }

    @Override
    public void delete(BarmenFeedback barmenFeedback) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, barmenFeedback);
        }
    }


    @Override
    public void update(BarmenFeedback barmenFeedback) throws SQLException {
       try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
           prepareStatementForUpdate(statement, barmenFeedback);
       }

    }
}
