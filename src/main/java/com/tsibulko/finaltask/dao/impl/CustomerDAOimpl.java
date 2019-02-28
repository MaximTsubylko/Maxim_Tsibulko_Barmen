package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerDAOimpl extends AbstractJdbcDao<Customer, Integer> implements CustomerDAO {

    @Override
    protected List<Customer> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Customer> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setFirst_name(resultSet.getString("first_name"));
                customer.setSecond_name(resultSet.getString("last_name"));
                customer.setRegistr_date(resultSet.getDate("registr_date"));
                customer.setRole_id(resultSet.getInt("role_id"));
                customer.setState(resultSet.getInt("state_id"));
                customer.setEmail(resultSet.getString("email"));
                customer.setLogin(resultSet.getString("login"));
                customer.setPassword(resultSet.getString("password"));
                customer.setId(resultSet.getInt("id"));
                result.add(customer);
            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t parse customer result set!");
        }
        return result;


    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Customer customer) throws DaoException {
        statementPreparation(statement, customer);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Customer customer) throws DaoException {
        statementPreparation(statement, customer);
        try {
            statement.setInt(statement.getParameterMetaData().getParameterCount(), customer.getId());
        } catch (SQLException e){
            throw new DaoException(e,"Cun`t run statement for update customer");
        }
    }

    @Override
    protected boolean hasColumn(String column) {
        return Arrays.asList("id", "login", "password", "role_id", "first_name", "last_name", "registr_date", "email",
                "state_id")
                .contains(column);
    }

    @Override
    protected String getSelectColumnQuery() {
        return "FROM user";
    }


    private void statementPreparation(PreparedStatement statement, Customer customer) throws DaoException {
        int i = 0;
        try {
            statement.setString(++i, customer.getFirst_name());
            statement.setString(++i, customer.getSecond_name());
            statement.setString(++i, customer.getLogin());
            statement.setString(++i, customer.getPassword());
            statement.setDate(++i, customer.getRegistr_date());
            statement.setInt(++i, customer.getState());
            statement.setString(++i, customer.getEmail());
            statement.setInt(++i, customer.getRole_id());
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t prepare customer statement for using!");
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM user";
    }


    @Override
    public String getPersistQuery() {
        return "INSERT INTO user (first_name, last_name, login, password, registr_date, state_id, email ,role_id)" +
                " VALUES (?,?,?,?,?,?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE user set first_name = ?, last_name = ?, login = ?, password = ?, registr_date = ?," +
                " state_id = ?, email = ?, role_id = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM user WHERE id = ?";
    }

    @AutoConnection
    @Override
    public Customer findByLogin(Customer user) throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(getSelectQuery() + " WHERE login = ?")) {
            preparedStatement.setString(1, user.getLogin());
            return parseResultSet(preparedStatement.executeQuery()).get(0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
