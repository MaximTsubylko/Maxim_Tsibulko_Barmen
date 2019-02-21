package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.sql.SQLException;

public interface ServiceValidator extends Validator {
    boolean isUniqueCocktail(Cocktail cocktail) throws DaoException, SQLException;

    boolean isExistCocktail(Cocktail cocktail) throws DaoException, SQLException;

    boolean isUniqueCustomer(Customer customer) throws DaoException, SQLException;

    boolean isExistCustomer(Customer customer) throws DaoException, SQLException;
}
