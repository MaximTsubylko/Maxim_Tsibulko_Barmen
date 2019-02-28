package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.service.CocktailService;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.impl.ServiceDateValidator;

import java.util.List;

public class CocktailServiceImpl implements CocktailService {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CocktailSpecificDAO cocktailDao;
    private static IngredientSpecificDAO ingredientDao;
    private ServiceDateValidator validator = (ServiceDateValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.SERVICE);

    @Override
    public Cocktail createCocktailWithIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        try {
            ingredientDao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            return ingredientDao.setCocktailIngredients(create(cocktail), ingredients);
        } catch (DaoException e) {
            throw new ServiceException(e, "Create cocktail with ingredients error");
        }
    }

    @Override
    public List<Cocktail> getCocktailByCustomer(Customer customer) throws ServiceException {
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            return cocktailDao.getCocktailByCustomer(customer);
        } catch (DaoException e) {
            throw new ServiceException(e, "Get by cocktail by customer error");
        }
    }

    @Override
    public Cocktail create(Cocktail cocktaile) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        try {
            if (validator.isUniqueCocktail(cocktaile)) {
                cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
                cocktailDao.persist(cocktaile);
                return cocktaile;
            } else {
                throw new ServiceDateValidationException("Not unique cocktail name");
            }
        } catch (DaoException e) {
            throw new ServiceException(e, "Create cocktail error");
        }

    }

    @Override
    public void delete(Cocktail cocktaile) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        try {
            if (validator.isExistCocktail(cocktaile)) {
                cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
                cocktailDao.delete(cocktaile);
            } else {
                throw new ServiceDateValidationException("This cocktail not exist!");
            }
        } catch (DaoException e) {
            throw new ServiceException(e, "Delete cocktail error");
        }
    }

    @Override
    public Cocktail getByPK(Integer id) throws ServiceException, ServiceDateValidationException {
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            if (cocktailDao.getByPK(id).isPresent()) {
                Cocktail cocktaile = (Cocktail) cocktailDao.getByPK(id).get();
                return cocktaile;
            } else {
                throw new ServiceDateValidationException("Can`t find cocktail with id = " + id);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, "Get by PK cocktail error");
        }
    }

    @Override
    public void update(Cocktail cocktaile) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        try {
            if (validator.isExistCocktail(cocktaile)) {
                cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
                cocktailDao.update(cocktaile);
            } else {
                throw new ServiceDateValidationException("This cocktail not exist!");
            }
        } catch (DaoException e) {
            throw new ServiceException(e, "Update cocktail error");
        }
    }

    @Override
    public List<Cocktail> getList() throws ServiceException {
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            return cocktailDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e, "Get cocktail list error");
        }
    }

}
