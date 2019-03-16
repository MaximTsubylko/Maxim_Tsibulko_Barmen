package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.IngredientSpecificDAO;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.service.IngredientService;
import com.tsibulko.finaltask.service.ServiceErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;

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
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        }

    }

    @Override
    public void delete(Ingredient obj) throws ServiceException {

    }

    @Override
    public Ingredient getByPK(Integer id) throws ServiceException {
        return null;
    }

    @Override
    public void update(Ingredient obj) throws ServiceException {

    }

    public Ingredient getByName(String name) throws ServiceException {
        IngredientSpecificDAO dao;
        try {
            dao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            return (Ingredient) dao.getByName(name).get();
        } catch (DaoException e) {
            throw new ServiceException(ServiceErrorConstant.ERR_CODE_NOT_EXIST_INGREDIENT);
        }
    }

    @Override
    public List<Ingredient> getList() throws ServiceException {
        IngredientSpecificDAO dao;
        try {
            dao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        }
    }
}
