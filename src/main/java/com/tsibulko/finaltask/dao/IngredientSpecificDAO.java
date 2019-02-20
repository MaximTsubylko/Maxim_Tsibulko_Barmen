package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface IngredientSpecificDAO<T extends Identified<PK>, PK extends Number> extends GenericDAO<T, PK> {
    String getIngredientsByCocktailIdQuery();
    String getSetIngredientsQuery();
    void prepareStatmentForSetCocktailIngredient(PreparedStatement statement) throws SQLException;
    List<Ingredient> prepareStatmentForGetIngredientsList(PreparedStatement statement) throws SQLException;
    List<T> getIngredientByCocktail(Cocktail cocktaile) throws SQLException;
    Cocktail setCockrailIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws SQLException;
}
