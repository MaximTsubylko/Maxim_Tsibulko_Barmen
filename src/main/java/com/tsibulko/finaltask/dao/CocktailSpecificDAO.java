package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;

import java.util.List;

public interface CocktailSpecificDAO<T extends Identified<PK>, PK extends Number> extends GenericDAO<T, PK> {
    List<T> getCocktailByCustomer(Customer customer) throws DaoException;

    void setCocktailToCustomer(Customer customer, Cocktail cocktail) throws DaoException;

    void deleteCoctail(Customer customer, Cocktail cocktail) throws DaoException;
}
