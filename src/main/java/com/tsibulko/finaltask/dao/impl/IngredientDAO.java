package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IngredientDAO extends AbstractJdbcDao<Ingredient, Integer> implements IngredientSpecificDAO<Ingredient, Integer> {

    public static final String SQL_CREATE_INGREDIENTS = "INSERT INTO cocktail_ingredient (cocktail_id, ingredient_id) VALUES (?,?);";


    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Ingredient ingredient) throws SQLException {
        int i = 0;
        statement.setString(++i, ingredient.getName());
        statement.setString(++i, ingredient.getDescription());

        statement.executeUpdate();
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Ingredient ingredient) throws SQLException {
        statement.setInt(1, ingredient.getId());
        statement.executeUpdate();
    }

    @Override
    protected Ingredient prepareStatementForGet(PreparedStatement statement) throws SQLException {
        Ingredient result = new Ingredient();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            result.setName(resultSet.getString("name"));
            result.setDescription(resultSet.getString("description"));
            result.setId(resultSet.getInt("id"));
        }
        return result;
    }

    @Override
    protected List<Ingredient> prepareStatementForGetAll(PreparedStatement statement) throws SQLException {
        List<Ingredient> resultList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(resultSet.getInt("id"));
            ingredient.setName(resultSet.getString("name"));
            ingredient.setDescription(resultSet.getString("description"));
            resultList.add(ingredient);
        }

        return resultList;
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Ingredient ingredient) throws SQLException {
        int i = 0;
        statement.setString(++i, ingredient.getName());
        statement.setString(++i, ingredient.getDescription());

        statement.executeUpdate();
    }

    protected List<Ingredient> prepareStatmentForGetIngredientsList(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        List<Ingredient> resultList = new ArrayList<>();
        while (resultSet.next()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(resultSet.getInt("ingredient_id"));
            ingredient.setName(resultSet.getString("name"));
            ingredient.setDescription(resultSet.getString("description"));
            resultList.add(ingredient);
        }
        return resultList;
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM ingredient WHERE id=?";
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM ingredient";
    }

    @Override
    public String getPersistQuery() {
        return "INSERT INTO ingredient (name, description) VALUES (?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE ingredient set name = ?, description = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM ingredient WHERE id = ?";
    }

    public String getIngredientsByCocktailIdQuery(){
        return "SELECT * FROM cocktail_ingredient " +
                "INNER JOIN ingredient " +
                "ON cocktail_ingredient.ingredient_id=ingredient.id " +
                "WHERE cocktail_id = ?;";
    }

    @Override
    public Optional<Ingredient> getByPK(Integer id) throws SQLException{
      try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
          statement.setInt(1, id);
          return Optional.of(prepareStatementForGet(statement));
      }
    }

    @Override
    public List<Ingredient> getAll() throws SQLException {
       try (PreparedStatement statement = connection.prepareStatement(getSelectAllQuery())) {
           return prepareStatementForGetAll(statement);
       }
    }


    @Override
    public void persist(Ingredient ingredient) throws SQLException {
       try (PreparedStatement statement = connection.prepareStatement(getPersistQuery())) {
           prepareStatementForInsert(statement, ingredient);
       }
    }

    @Override
    public void delete(Ingredient ingredient) throws SQLException {
       try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
           prepareStatementForDelete(statement, ingredient);
       }
    }


    @Override
    public void update(Ingredient ingredient) throws SQLException {
       try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
           prepareStatementForUpdate(statement, ingredient);
       }

    }

    public List<Ingredient> getIngredientByCocktail(Cocktaile cocktaile) throws SQLException {
       try (PreparedStatement statement = connection.prepareStatement(getIngredientsByCocktailIdQuery())) {
           statement.setInt(1, cocktaile.getId());
           return prepareStatmentForGetIngredientsList(statement);
       }
    }


}
