package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;

import java.util.List;

public interface CocktailService extends CRUDService<Cocktail> {
    Cocktail createCocktailWithIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException;

    List<Cocktail> getCocktailByCustomer(Customer customer) throws ServiceException;
}
