package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.AbstractJdbcDao;
import com.tsibulko.finaltask.dao.AutoConnection;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.IngredientSpecificDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientDAO extends AbstractJdbcDao<Ingredient, Integer> implements IngredientSpecificDAO<Ingredient, Integer> {


    @Override
    protected List<Ingredient> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Ingredient> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(resultSet.getInt("id"));
                ingredient.setName(resultSet.getString("name"));
                ingredient.setDescription(resultSet.getString("description"));
                result.add(ingredient);

            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t parse ingredient result set!");
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Ingredient ingredient) throws DaoException {
        int i = 0;
        statementPreparation(statement, ingredient);
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Ingredient ingredient) throws DaoException {
        statementPreparation(statement, ingredient);
        try {
            statement.setInt(statement.getParameterMetaData().getParameterCount(), ingredient.getId());
        } catch (SQLException e) {
            throw new DaoException(e, "Cun`t run statement for update ingredient feedback!");
        }
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
    public List<Ingredient> prepareStatementForGetIngredientsList(PreparedStatement statement) throws DaoException {
        try {
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
        } catch (SQLException e) {
            throw new DaoException(e, "Cun`t run statement for get ingredient list");
        }
    }

    private void statementPreparation(PreparedStatement statement, Ingredient ingredient) throws DaoException {
        int i = 0;
        try {
            statement.setString(++i, ingredient.getName());
            statement.setString(++i, ingredient.getDescription());
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t prepare ingredient statement for using!");
        }

    }


    @Override
    public void prepareStatementForSetCocktailIngredient(PreparedStatement statement) throws DaoException {
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t run statement for set cocktail ingredient");
        }
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
    public List<Ingredient> getIngredientByCocktail(Cocktail cocktaile) throws DaoException {
        try {
            try (PreparedStatement statement = connection.prepareStatement(getIngredientsByCocktailIdQuery())) {
                statement.setInt(1, cocktaile.getId());
                List<Ingredient> i = prepareStatementForGetIngredientsList(statement);
                return prepareStatementForGetIngredientsList(statement);
            }
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t get ingredient by cocktail");
        }
    }

    @AutoConnection
    @Override
    public Cocktail setCocktailIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws DaoException {
        try {
            try (PreparedStatement statement = connection.prepareStatement(getSetIngredientsQuery())) {
                statement.setInt(1, cocktail.getId());
                for (Ingredient i : ingredients) {
                    statement.setInt(2, i.getId());
                    prepareStatementForSetCocktailIngredient(statement);
                }
            }
            cocktail.setIngredients(ingredients);
            return cocktail;
        } catch (SQLException e) {
            throw new DaoException(e, "Can`t set cocktail ingredient");
        }
    }


}
