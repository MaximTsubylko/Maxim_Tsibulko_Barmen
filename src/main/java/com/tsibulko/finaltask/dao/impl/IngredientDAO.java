package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class IngredientDAO extends AbstractJdbcDao<Ingredient, Integer> implements IngredientSpecificDAO<Ingredient, Integer> {


    @Override
    protected List<Ingredient> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Ingredient> result = new ArrayList<>();
        while (resultSet.next()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(resultSet.getInt("id"));
            ingredient.setName(resultSet.getString("name"));
            ingredient.setDescription(resultSet.getString("description"));
            result.add(ingredient);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Ingredient ingredient) throws SQLException {
        int i = 0;
        statement.setString(++i, ingredient.getName());
        statement.setString(++i, ingredient.getDescription());
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Ingredient ingredient) throws SQLException {
        statementPreparation(statement,ingredient);
        statement.setInt(statement.getParameterMetaData().getParameterCount(),ingredient.getId());
    }

    @Override
    protected boolean hasColumn(String column) {
        return Arrays.asList("id", "name", "description")
                .contains(column);
    }

    @Override
    protected String getSelectColumnQuery() {
        return "FROM ingredient";
    }
    @Override
    public List<Ingredient> prepareStatementForGetIngredientsList(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            List<Ingredient> result = new ArrayList<>();
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(resultSet.getInt("ingredient_id"));
                ingredient.setName(resultSet.getString("name"));
                ingredient.setDescription(resultSet.getString("description"));
                result.add(ingredient);
            }
            return result;
        }
    }

    private void statementPreparation(PreparedStatement statement, Ingredient ingredient) throws SQLException {
        int i = 0;
        statement.setString(++i, ingredient.getName());
        statement.setString(++i, ingredient.getDescription());

    }


    @Override
    public void prepareStatementForSetCocktailIngredient(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM ingredient";
    }


    @Override
    public String getPersistQuery() {
        return "INSERT INTO ingredient (name, description) VALUES (?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE ingredient set name = ?, description = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM ingredient WHERE id = ?";
    }

    @Override
    public String getIngredientsByCocktailIdQuery() {
        return "SELECT * FROM cocktail_ingredient " +
                "INNER JOIN ingredient " +
                "ON cocktail_ingredient.ingredient_id=ingredient.id " +
                "WHERE cocktail_id = ?;";
    }

    @Override
    public String getSetIngredientsQuery() {
        return "INSERT INTO cocktail_ingredient (cocktail_id, ingredient_id) VALUES (?,?);";
    }

    @AutoConnection
    @Override
    public List<Ingredient> getIngredientByCocktail(Cocktail cocktaile) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getIngredientsByCocktailIdQuery())) {
            statement.setInt(1, cocktaile.getId());
            List<Ingredient> i = prepareStatementForGetIngredientsList(statement);
            return prepareStatementForGetIngredientsList(statement);
        }
    }

    @AutoConnection
    @Override
    public Cocktail setCocktailIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getSetIngredientsQuery())) {
            statement.setInt(1, cocktail.getId());
            for (Ingredient i : ingredients) {
                statement.setInt(2, i.getId());
                prepareStatementForSetCocktailIngredient(statement);
            }
        }
        cocktail.setIngredients(ingredients);
        return cocktail;
    }


}
