package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.service.CocktailService;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CocktailServiceImpl implements CocktailService {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CocktailSpecificDAO cocktailDao;
    private static IngredientSpecificDAO ingredientDao;

    public void editCocktail(HttpServletRequest request) throws ServiceException {
        Cocktail cocktail = getByPK(Integer.valueOf(request.getParameter("id")));
        cocktail.setName(request.getParameter("name"));
        cocktail.setPrice(Integer.valueOf(request.getParameter("price")));
        cocktail.setDescription(request.getParameter("description"));
        update(cocktail);
    }

    @Override
    public Cocktail createCocktailWithIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws ServiceException {
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

    public List<Ingredient> getIngredientByCocktail(Cocktail cocktail) throws ServiceException {
        try {
            ingredientDao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            return ingredientDao.getIngredientByCocktail(cocktail);
        } catch (DaoException e) {
            throw new ServiceException(e, "Geting cocktail ingredient error with ingredients error");
        }
    }

    public Cocktail createNewCocktail(Cocktail cocktail, Customer customer) throws ServiceException {
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            create(cocktail);
            cocktailDao.setCocktailToCustomer(customer,cocktail);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return cocktail;
    }

    @Override
    public Cocktail create(Cocktail cocktaile) throws ServiceException {
        try {

            cocktailDao.persist(cocktaile);
            return cocktaile;
        } catch (DaoException e) {
            throw new ServiceException(e, "Create cocktail error");
        }

    }

    @Override
    public void delete(Cocktail cocktaile) throws ServiceException {
        FieldValidator fieldValidator = FieldValidator.getInstance();

        try {
            fieldValidator.isExist("name", Cocktail.class, cocktaile.getName());
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            cocktailDao.delete(cocktaile);

        } catch (DaoException e) {
            throw new ServiceException(e, "Delete cocktail error");
        } catch (ValidationException e) {
            throw new ServiceException(e, "This cocktail not exist");
        }
    }

    @Override
    public Cocktail getByPK(Integer id) throws ServiceException {
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            if (cocktailDao.getByPK(id).isPresent()) {
                Cocktail cocktaile = (Cocktail) cocktailDao.getByPK(id).get();
                return cocktaile;
            } else {
                throw new ServiceException("This cocktail doesn`t exist");
            }
        } catch (DaoException e) {
            throw new ServiceException(e, "Get by PK cocktail error");
        }
    }

    @Override
    public void update(Cocktail cocktaile) throws ServiceException {
        FieldValidator fieldValidator = FieldValidator.getInstance();
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            cocktailDao.update(cocktaile);
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
