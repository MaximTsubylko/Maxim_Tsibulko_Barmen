package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.bean.Ingredient;

import java.sql.SQLException;
import java.util.List;

public interface IngredientSpecificDAO<T extends Identified<PK>, PK extends Number> extends GenericDAO<T, PK> {
    List<T> getIngredientByCocktail(Cocktaile cocktaile) throws SQLException;
}
