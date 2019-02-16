package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CocktaileDAO extends AbstractJdbcDao<Cocktaile, Integer> implements CocktileSpecificDAO<Cocktaile, Integer> {

    @AutoConnection

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Cocktaile cocktaile) throws SQLException {
        int i = 0;
        statement.setString(++i, cocktaile.getName());
        statement.setString(++i, cocktaile.getDescription());
        statement.setInt(++i, cocktaile.getPrice());
        statement.executeUpdate();
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Cocktaile cocktaile) throws SQLException {
        statement.setInt(1, cocktaile.getId());
        statement.executeUpdate();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Cocktaile cocktaile) throws SQLException {
        int i = 0;
        statement.setString(++i, cocktaile.getName());
        statement.setString(++i, cocktaile.getDescription());
        statement.setInt(++i, cocktaile.getPrice());

        statement.executeUpdate();
    }

    @Override
    protected Cocktaile prepareStatementForGet(PreparedStatement statement) throws SQLException {
        Cocktaile cocktaile = new Cocktaile();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            cocktaile.setName(resultSet.getString("name"));
            cocktaile.setDescription(resultSet.getString("description"));
            cocktaile.setPrice(resultSet.getInt("price"));
            cocktaile.setId(resultSet.getInt("id"));
        }
        return cocktaile;
    }

    @Override
    protected List<Cocktaile> prepareStatementForGetAll(PreparedStatement statement) throws SQLException {
        List<Cocktaile> resultList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Cocktaile cocktaile = new Cocktaile();
            cocktaile.setId(resultSet.getInt("id"));
            cocktaile.setName(resultSet.getString("name"));
            cocktaile.setDescription(resultSet.getString("description"));
            cocktaile.setPrice(resultSet.getInt("price"));
            resultList.add(cocktaile);
        }
        return resultList;
    }

    protected List<Cocktaile> prepareStatmentForGetCocktailList(PreparedStatement statement, Customer customer) throws SQLException {
        List<Cocktaile> resultList = new ArrayList<>();
        statement.setInt(1,customer.getId());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Cocktaile cocktaile = new Cocktaile();
            cocktaile.setId(resultSet.getInt("id"));
            cocktaile.setName(resultSet.getString("name"));
            cocktaile.setDescription(resultSet.getString("description"));
            cocktaile.setPrice(resultSet.getInt("price"));
            resultList.add(cocktaile);
        }
        return resultList;
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM cocktail WHERE id=?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM cocktail";
    }

    @Override
    public String getPersistQuery() {
        return "INSERT INTO cocktail (name, description, price) VALUES (?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE cocktail set name = ?, description = ?, price = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM cocktail WHERE id = ?";
    }

    public String getCocktailListQuery(){return "SELECT * FROM user_coctails INNER JOIN cocktail ON cocktail_id=cocktail.id WHERE user_id = ?";}

    @Override
    public Optional<Cocktaile> getByPK(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
            statement.setInt(1, id);
            return Optional.of(prepareStatementForGet(statement));
        }
    }

    @Override
    public List<Cocktaile> getAll() throws SQLException {
       try (PreparedStatement statement = connection.prepareStatement(getSelectAllQuery())) {
           return prepareStatementForGetAll(statement);
       }
    }

    @Override
    public void persist(Cocktaile cocktaile) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getPersistQuery())) {
            prepareStatementForInsert(statement, cocktaile);
        }
    }

    @Override
    public void delete(Cocktaile cocktaile) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, cocktaile);
        }

    }

    @Override
    public void update(Cocktaile cocktaile) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, cocktaile);
        }
    }

    public List<Cocktaile> getCocktaileByCustomer(Customer customer) throws SQLException {
        try (PreparedStatement statment = connection.prepareStatement(getCocktailListQuery())) {
            statment.setInt(1, customer.getId());
            return prepareStatmentForGetCocktailList(statment, customer);
        }
    }

}
