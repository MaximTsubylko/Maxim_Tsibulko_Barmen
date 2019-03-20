package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.AbstractJdbcDao;
import com.tsibulko.finaltask.dao.AutoConnection;
import com.tsibulko.finaltask.dao.CocktailSpecificDAO;
import com.tsibulko.finaltask.dao.DaoException;

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
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t parse cocktail result set!");
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
        } catch (SQLException e) {
            throw new DaoException(e, "Cun`t run statement for update cocktail");
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

    protected String getSetCoctailToCustomerQuery() {
        return "INSERT INTO user_coctails (user_id,cocktail_id) values (?,?)";
    }

    private void statementPreparation(PreparedStatement statement, Cocktail cocktail) throws DaoException {
        int i = 0;
        try {
            statement.setString(++i, cocktail.getName());
            statement.setString(++i, cocktail.getDescription());
            statement.setInt(++i, cocktail.getPrice());
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t prepare cocktail statement for using!");
        }
    }

    protected void preparedStatmentForSetCocktailToCustomer(PreparedStatement statement,
                                                            Customer customer, Cocktail cocktail) throws DaoException {
        try {
            statement.setInt(1, customer.getId());
            statement.setInt(2, cocktail.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e, "");
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

    public String getCocktailDeleteByCustomerQuary() {
        return "DELETE from user_coctails WHERE user_id = ? and cocktail_id = ?";
    }

    @AutoConnection
    public List<Cocktail> getCocktailByCustomer(Customer customer) throws DaoException {
        try {

            try (PreparedStatement statment = connection.prepareStatement(getCocktailListQuery())) {
                statment.setInt(1, customer.getId());
                return preparedStatementForGetCocktailList(statment, customer);
            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t get cocktails by customer");
        }
    }

    @Override
    @AutoConnection
    public void setCocktailToCustomer(Customer customer, Cocktail cocktail) throws DaoException {
        try {
            try (PreparedStatement statement = connection.prepareStatement(getSetCoctailToCustomerQuery())) {
                preparedStatmentForSetCocktailToCustomer(statement, customer, cocktail);
            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t get cocktails by customer");
        }
    }

    @AutoConnection
    @Override
    public void deleteCoctail(Customer customer, Cocktail cocktail) throws DaoException {
        try {
            try (PreparedStatement statement = connection.prepareStatement(getCocktailDeleteByCustomerQuary())) {
                preparedStatmentForSetCocktailToCustomer(statement, customer, cocktail);
            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t delete cocktails by customer");
        }
    }

}
