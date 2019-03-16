package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.impl.CocktailDAO;
import com.tsibulko.finaltask.dao.impl.IngredientDAO;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.dao.impl.TransactionManager;
import com.tsibulko.finaltask.service.CocktailService;
import com.tsibulko.finaltask.service.ServiceErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CocktailServiceImpl implements CocktailService {
    private static JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();


    public void editCocktail(Cocktail cocktail) throws ServiceException {
        update(cocktail);
    }

    @Override
    public Cocktail createCocktailWithIngredients(Cocktail cocktail, List<Ingredient> ingredients) throws ServiceException {
        IngredientSpecificDAO ingredientDao;
        try {
            ingredientDao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            return ingredientDao.setCocktailIngredients(create(cocktail), ingredients);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        }
    }

    @Override
    public List<Cocktail> getCocktailByCustomer(Customer customer) throws ServiceException {
        CocktailSpecificDAO cocktailDao;
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            return cocktailDao.getCocktailByCustomer(customer);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        }
    }

    public List<Ingredient> getIngredientByCocktail(Cocktail cocktail) throws ServiceException {
        IngredientSpecificDAO ingredientDao;
        try {
            ingredientDao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            return ingredientDao.getIngredientByCocktail(cocktail);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        }
    }

    public Cocktail createNewCocktail(Cocktail cocktail, Customer customer) throws ServiceException {
        CocktailSpecificDAO cocktailDao;
        IngredientSpecificDAO ingredientDao;
        TransactionManager transactionManager = new TransactionManager();
        try {
            ingredientDao = (IngredientSpecificDAO) daoFactory.getTransactionalDao(Ingredient.class);
            cocktailDao = (CocktailSpecificDAO) daoFactory.getTransactionalDao(Cocktail.class);

            transactionManager.begin(cocktailDao,ingredientDao);

            cocktailDao.persist(cocktail);
            ingredientDao.setCocktailIngredients(cocktail,cocktail.getIngredients());
            cocktailDao.setCocktailToCustomer(customer,cocktail);

            transactionManager.commit();
        } catch (DaoException e) {

            try {
                transactionManager.rollback();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }

            throw new ServiceException(e,ServiceErrorConstant.ERR_CODE_GET_CREATE_COCKTAIL_WHITH_INGREDIENTS);
        }finally {

            try {
                transactionManager.end();
            } catch (DaoException e) {
                e.printStackTrace();
            }

        }

        return cocktail;
    }

    @Override
    public Cocktail create(Cocktail cocktaile) throws ServiceException {
        CocktailSpecificDAO cocktailDao;
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            cocktailDao.persist(cocktaile);
            return cocktaile;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        }

    }

    @Override
    public void delete(Cocktail cocktaile) throws ServiceException {
        FieldValidator fieldValidator = FieldValidator.getInstance();
        CocktailSpecificDAO cocktailDao;
        try {
            fieldValidator.isExist("name", Cocktail.class, cocktaile.getName());
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            cocktailDao.delete(cocktaile);

        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        } catch (ValidationException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_NOT_EXIST_COCKTAIL);
        }
    }

    @Override
    public Cocktail getByPK(Integer id) throws ServiceException {
        CocktailSpecificDAO cocktailDao;
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            if (cocktailDao.getByPK(id).isPresent()) {
                Cocktail cocktaile = (Cocktail) cocktailDao.getByPK(id).get();
                return cocktaile;
            } else {
                throw new ServiceException(ServiceErrorConstant.ERR_CODE_NOT_EXIST_COCKTAIL);
            }
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        }
    }

    @Override
    public void update(Cocktail cocktaile) throws ServiceException {
        CocktailSpecificDAO cocktailDao;
        FieldValidator fieldValidator = FieldValidator.getInstance();
        try {
            fieldValidator.isExist("id",Cocktail.class,String.valueOf(cocktaile.getId()));
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            cocktailDao.update(cocktaile);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        } catch (ValidationException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_NOT_EXIST_COCKTAIL);
        }
    }

    @Override
    public List<Cocktail> getList() throws ServiceException {
        CocktailSpecificDAO cocktailDao;
        try {
            cocktailDao = (CocktailSpecificDAO) daoFactory.getDao(Cocktail.class);
            return cocktailDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        }
    }

}
