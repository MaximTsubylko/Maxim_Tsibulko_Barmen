package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.validation.ServiceValidator;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;


import java.sql.SQLException;
import java.util.List;

public class ServiceDateValidator implements ServiceValidator {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static GenericDAO dao;

    @Override
    public boolean isUniqueCocktail(Cocktail cocktail) throws LoginAndRegistrationException {
        try {
            dao = daoFactory.getDao(Cocktail.class);
            if (dao.getStringsFromColumn("name").contains(cocktail.getName())) {
                return false;
            }
            return true;
        } catch (DaoException e) {
            throw new LoginAndRegistrationException(e, "Error in validation of unique cocktail");
        }
    }

    @Override
    public boolean isExistCocktail(Cocktail cocktail) throws LoginAndRegistrationException {
        return !isUniqueCocktail(cocktail);
    }

}
