package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.validation.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.ServiceValidator;

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
        try {
            dao = daoFactory.getDao(Cocktail.class);
            if (dao.getStringsFromColumn("id").contains(String.valueOf(cocktail.getId()))) {
                 return true;
            }
            return false;
        } catch (DaoException e) {
            throw new LoginAndRegistrationException(e, "Error in validation of unique cocktail");
        }
    }



    @Override
    public void doValidation(Object entity) {
    }
}
