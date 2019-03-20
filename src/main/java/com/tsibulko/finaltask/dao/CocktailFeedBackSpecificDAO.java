package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.CocktaileFeedback;

import java.util.List;

public interface CocktailFeedBackSpecificDAO<T extends Identified<PK>, PK extends Number> extends GenericDAO<T, PK> {
    List<CocktaileFeedback> getCocktailFeedbacksByCocktail(Cocktail cocktail) throws DaoException;

}
