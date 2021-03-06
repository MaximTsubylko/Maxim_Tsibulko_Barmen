package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public interface IngredientSpecificDAO<T extends Identified<PK>, PK extends Number> extends GenericDAO<T, PK> {
    String getIngredientsByCocktailIdQuery();

    String getSetIngredientsQuery();

    void prepareStatementForSetCocktailIngredient(PreparedStatement statement) throws DaoException;

    List<Ingredient> prepareStatementForGetIngredientsList(PreparedStatement statement) throws DaoException;

    Optional<Ingredient> getByName(String login) throws DaoException;

    List<T> getIngredientByCocktail(Cocktail cocktail) throws DaoException;

    Cocktail setCocktailIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws DaoException;
}


