package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.Ingredient;

import java.util.List;

public interface CocktailService extends CRUDService<Cocktail> {
    Cocktail createCocktailWithIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws ServiceException;

    List<Cocktail> getCocktailByCustomer(Customer customer) throws ServiceException;
}
