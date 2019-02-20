package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.service.CocktailService;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.impl.ServiceDateValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CocktailServiceImpl implements CocktailService {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CocktileSpecificDAO cocktailDao;
    private static IngredientSpecificDAO ingredientDao;
    private ServiceDateValidator validator = (ServiceDateValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.SERVICE);
    private static CocktailServiceImpl instance;
    private static Lock lock = new ReentrantLock();

    @Override
    public Cocktail createCocktailWithIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws SQLException, DaoException, PersistException, ServiceDateValidationException {
        ingredientDao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
        return ingredientDao.setCockrailIngredients(create(cocktail),ingredients);

    }

    @Override
    public Cocktail create(Cocktail cocktaile) throws SQLException, DaoException, PersistException, ServiceDateValidationException {
        if (validator.isUniqueCocktil(cocktaile)) {
            cocktailDao = (CocktileSpecificDAO) daoFactory.getDao(Cocktail.class);
            cocktailDao.persist(cocktaile);
            return cocktaile;
        } else {
            throw new ServiceDateValidationException("Not unique cocktail name");
        }

    }

    @Override
    public void delete(Cocktail cocktaile) throws SQLException, PersistException, DaoException, ServiceDateValidationException {
        if (validator.isExistCoctil(cocktaile)) {
            cocktailDao = (CocktileSpecificDAO) daoFactory.getDao(Cocktail.class);
            cocktailDao.delete(cocktaile);
        } else {
            throw new ServiceDateValidationException("This cocktail not exist!");
        }
    }

    @Override
    public Cocktail getByPK(Integer id) throws DaoException, SQLException, InterruptedException, ServiceDateValidationException {
        cocktailDao = (CocktileSpecificDAO) daoFactory.getDao(Cocktail.class);
        if (cocktailDao.getByPK(id).isPresent()) {
            Cocktail cocktaile = (Cocktail) cocktailDao.getByPK(id).get();
            return cocktaile;
        } else {
            throw new ServiceDateValidationException("Can`t find cocktail with id = "+id);
        }
    }

    @Override
    public void update(Cocktail cocktaile) throws SQLException, PersistException, DaoException, ServiceDateValidationException {
        if (validator.isExistCoctil(cocktaile)) {
            cocktailDao = (CocktileSpecificDAO) daoFactory.getDao(Cocktail.class);
            cocktailDao.update(cocktaile);
        } else {
            throw new ServiceDateValidationException("This cocktail not exist!");
        }
    }

    @Override
    public List<Cocktail> getList() throws DaoException, SQLException {
        cocktailDao = (CocktileSpecificDAO) daoFactory.getDao(Cocktail.class);
        return cocktailDao.getAll();
    }

}
