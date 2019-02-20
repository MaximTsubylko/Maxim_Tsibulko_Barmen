package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.validation.ServiceValid;


import java.sql.SQLException;
import java.util.List;

public class ServiceDateValidator implements ServiceValid {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static GenericDAO dao;
    @Override
    public boolean isUniqueCocktil(Cocktail cocktaile) throws DaoException, SQLException {
        dao = daoFactory.getDao(Cocktail.class);
        List<Cocktail> cocktails = dao.getAll();
        return !(cocktails
                .stream()
                .anyMatch(c -> c.getName()
                        .equals(cocktaile.getName())));
    }

    @Override
    public boolean isExistCoctil(Cocktail cocktaile) throws DaoException, SQLException {
        dao = daoFactory.getDao(Cocktail.class);
        List<Cocktail> cocktails = dao.getAll();
        return cocktails
                .stream()
                .anyMatch(c->c.getId()
                        .equals(cocktaile.getId()));
    }

    @Override
    public boolean isUniqueCustomer(Customer customer) throws DaoException, SQLException {
        dao = daoFactory.getDao(Customer.class);
        List<Customer> customers = dao.getAll();
        return !(customers
                .stream()
                .anyMatch(c -> c.getLogin()
                        .equals(customer.getLogin())));
    }

    @Override
    public boolean isExistCustomer(Customer customer) throws DaoException, SQLException {
        dao = daoFactory.getDao(Customer.class);
        List<Customer> customers = dao.getAll();
        return customers
                .stream()
                .anyMatch(c->c.getLogin()
                        .equals(customer.getLogin()));

    }
}
