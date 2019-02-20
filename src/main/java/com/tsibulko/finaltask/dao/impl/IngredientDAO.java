package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IngredientDAO extends AbstractJdbcDao<Ingredient, Integer> implements IngredientSpecificDAO<Ingredient, Integer> {


    @Override
    protected List<Ingredient> parseResultSet(ResultSet rs) throws SQLException {
        return null;
    }

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

    @Override
    public List<Ingredient> prepareStatmentForGetIngredientsList(PreparedStatement statement) throws SQLException {
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
    public void prepareStatmentForSetCocktailIngredient(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
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
    public Optional<Ingredient> getByPK(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getSelectQuery())) {
            statement.setInt(1, id);
            return Optional.of(prepareStatementForGet(statement));
        }
    }

    @AutoConnection
    @Override
    public List<Ingredient> getAll() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getSelectAllQuery())) {
            return prepareStatementForGetAll(statement);
        }
    }

    @AutoConnection
    @Override
    public Ingredient persist(Ingredient ingredient) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getPersistQuery())) {
            prepareStatementForInsert(statement, ingredient);
            return ingredient;
        }
    }

    @AutoConnection
    @Override
    public void delete(Ingredient ingredient) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, ingredient);
        }
    }

    @AutoConnection
    @Override
    public void update(Ingredient ingredient) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, ingredient);
        }

    }

    @AutoConnection
    @Override
    public List<Ingredient> getIngredientByCocktail(Cocktail cocktaile) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getIngredientsByCocktailIdQuery())) {
            statement.setInt(1, cocktaile.getId());
            return prepareStatmentForGetIngredientsList(statement);
        }
    }

    @AutoConnection
    @Override
    public Cocktail setCockrailIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(getSetIngredientsQuery())){
            statement.setInt(1,cocktail.getId());
            for (Ingredient i:ingredients) {
                statement.setInt(2,i.getId());
                prepareStatmentForSetCocktailIngredient(statement);
            }
        }
        cocktail.setIngredients(ingredients);
        return cocktail;
    }


}
