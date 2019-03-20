package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.CocktailSpecificDAO;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.IngredientSpecificDAO;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.IngredientService;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import java.util.List;

public class IngredientServiceImpl implements IngredientService {

    private JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();

    @Override
    public Ingredient create(Ingredient ingredient) throws ServiceException {
        IngredientSpecificDAO dao;
        try {
            dao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            dao.persist(ingredient);
            return ingredient;
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_INGREDIENT);
            throw new ServiceException(e, "DAO error");
        }

    }

    @Override
    public void delete(Ingredient ingredient) throws ServiceException {
        FieldValidator fieldValidator = FieldValidator.getInstance();
        IngredientSpecificDAO ingredientDAO;
        try {
            fieldValidator.isExist("name", Cocktail.class, ingredient.getName());
            ingredientDAO = (IngredientSpecificDAO) daoFactory.getDao(Cocktail.class);
            ingredientDAO.delete(ingredient);

        } catch (DaoException e) {
            throw new ServiceException(e, "Dao error");
        } catch (ValidationException e) {
            throw new ServiceException(e, "Dao error");
        }
    }

    @Override
    public Ingredient getByPK(Integer id) throws ServiceException {
        IngredientSpecificDAO ingredientDAO;
        try {
            ingredientDAO = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            if (ingredientDAO.getByPK(id).isPresent()) {
                Ingredient ingredient = (Ingredient) ingredientDAO.getByPK(id).get();
                return ingredient;
            } else {
                ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_INGREDIENT);
                throw new ServiceException("Dao error");
            }
        } catch (DaoException e) {
            throw new ServiceException(e, "Dao error");
        }
    }

    @Override
    public void update(Ingredient ingredient) throws ServiceException {
        IngredientSpecificDAO ingredientDao;
        FieldValidator fieldValidator = FieldValidator.getInstance();
        try {
            fieldValidator.isExist("id", Ingredient.class, String.valueOf(ingredient.getId()));
            ingredientDao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            ingredientDao.update(ingredient);
        } catch (DaoException e) {
            throw new ServiceException(e, "Dao error");
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_INGREDIENT);
            throw new ServiceException(e, "Dao error");
        }
    }

    public Ingredient getByName(String name) throws ServiceException {
        IngredientSpecificDAO dao;
        try {
            dao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            return (Ingredient) dao.getByName(name).get();
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_INGREDIENT);
            throw new ServiceException("DAO error");
        }
    }

    @Override
    public List<Ingredient> getList() throws ServiceException {
        IngredientSpecificDAO dao;
        try {
            dao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e, "DAO error");
        }
    }
}
