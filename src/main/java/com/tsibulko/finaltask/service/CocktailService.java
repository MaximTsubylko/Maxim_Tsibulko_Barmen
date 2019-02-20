package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;

import java.sql.SQLException;
import java.util.List;

public interface CocktailService extends CRUDService<Cocktail> {
    Cocktail createCocktailWithIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws SQLException, DaoException, PersistException, ServiceDateValidationException;
}
