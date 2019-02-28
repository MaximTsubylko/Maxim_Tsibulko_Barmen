package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CocktailDAO extends AbstractJdbcDao<Cocktail, Integer> implements CocktailSpecificDAO<Cocktail, Integer> {

    @Override
    protected List<Cocktail> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Cocktail> resultList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Cocktail cocktail = new Cocktail();
                cocktail.setName(resultSet.getString("name"));
                cocktail.setDescription(resultSet.getString("description"));
                cocktail.setPrice(resultSet.getInt("price"));
                cocktail.setId(resultSet.getInt("id"));
                resultList.add(cocktail);
            }
            return resultList;
        } catch (SQLException e){
            throw new DaoException(e,"Can`t parse cocktail result set!");
        }
    }


    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Cocktail cocktail) throws DaoException {
        statementPreparation(statement, cocktail);
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Cocktail cocktail) throws DaoException {
        statementPreparation(statement, cocktail);
        try {
            statement.setInt(statement.getParameterMetaData().getParameterCount(), cocktail.getId());
        } catch (SQLException e){
            throw new DaoException(e,"Cun`t run statement for update cocktail");
        }
    }

    @Override
    protected boolean hasColumn(String column) {
        return Arrays.asList("id", "name", "price", "description")
                .contains(column);
    }

    @Override
    protected String getSelectColumnQuery() {
        return "FROM cocktail";
    }


    private void statementPreparation(PreparedStatement statement, Cocktail cocktail) throws DaoException {
        int i = 0;
        try {
            statement.setString(++i, cocktail.getName());
            statement.setString(++i, cocktail.getDescription());
            statement.setInt(++i, cocktail.getPrice());
        } catch (SQLException e){
            throw new DaoException(e,"Can`t prepare cocktail statement for using!");
        }
    }

    protected List<Cocktail> preparedStatementForGetCocktailList(PreparedStatement statement, Customer customer) throws DaoException {
        List<Cocktail> resultList = new ArrayList<>();
        try {
            statement.setInt(1, customer.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cocktail cocktaile = new Cocktail();
                cocktaile.setId(resultSet.getInt("cocktail_id"));
                cocktaile.setName(resultSet.getString("name"));
                cocktaile.setDescription(resultSet.getString("description"));
                cocktaile.setPrice(resultSet.getInt("price"));
                resultList.add(cocktaile);
            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t parse cocktail result set!");
        }
        return resultList;
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM cocktail";
    }

    @Override
    public String getPersistQuery() {
        return "INSERT INTO cocktail (name, description, price) VALUES (?,?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE cocktail set name = ?, description = ?, price = ?  WHERE id=?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM cocktail WHERE id = ?";
    }

    public String getCocktailListQuery() {
        return "SELECT * FROM user_coctails INNER JOIN cocktail ON cocktail_id=cocktail.id WHERE user_id = ?";
    }

    @AutoConnection
    public List<Cocktail> getCocktailByCustomer(Customer customer) throws DaoException {
        try {

            try (PreparedStatement statment = connection.prepareStatement(getCocktailListQuery())) {
                statment.setInt(1, customer.getId());
                return preparedStatementForGetCocktailList(statment, customer);
            }
        } catch (SQLException e){
            throw new DaoException(e,"Can`t get cocktails by customer");
        }
    }

}
