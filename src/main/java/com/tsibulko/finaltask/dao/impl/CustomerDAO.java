package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAO extends AbstractJdbcDao<Customer, Integer> implements GenericDAO<Customer, Integer> {

    @Override
    protected List<Customer> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Customer> result = new ArrayList<>();
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
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Customer customer) throws SQLException {
        statementPreparation(statement, customer);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Customer customer) throws SQLException {
        statementPreparation(statement,customer);
        statement.setInt(statement.getParameterMetaData().getParameterCount(),customer.getId());
    }


    private void statementPreparation(PreparedStatement statement, Customer customer) throws SQLException {
        int i = 0;
        statement.setString(++i, customer.getFirst_name());
        statement.setString(++i, customer.getSecond_name());
        statement.setString(++i, customer.getLogin());
        statement.setString(++i, customer.getPassword());
        statement.setDate(++i, customer.getRegistr_date());
        statement.setInt(++i, customer.getState());
        statement.setString(++i, customer.getEmail());
        statement.setInt(++i, customer.getRole_id());
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

}
