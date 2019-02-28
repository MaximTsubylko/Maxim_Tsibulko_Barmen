package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.util.List;

public interface IngredientSpecificDAO<T extends Identified<PK>, PK extends Number> extends GenericDAO<T, PK> {
    String getIngredientsByCocktailIdQuery();

    String getSetIngredientsQuery();

    void prepareStatementForSetCocktailIngredient(PreparedStatement statement) throws DaoException;

    List<Ingredient> prepareStatementForGetIngredientsList(PreparedStatement statement) throws DaoException;

    List<T> getIngredientByCocktail(Cocktail cocktail) throws DaoException;

    Cocktail setCocktailIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws DaoException;
}
