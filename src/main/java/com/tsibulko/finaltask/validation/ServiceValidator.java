package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;

import java.sql.SQLException;

public interface ServiceValidator extends Validator {
    boolean isUniqueCocktail(Cocktail cocktail) throws LoginAndRegistrationException;

    boolean isExistCocktail(Cocktail cocktail) throws LoginAndRegistrationException;

}
