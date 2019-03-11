package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.service.IngredientService;
import com.tsibulko.finaltask.service.ServiceException;

import java.util.List;

public class IngredientServiceImpl implements IngredientService {

    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static IngredientSpecificDAO dao;
    @Override
    public Ingredient create(Ingredient obj) throws ServiceException {

        return null;
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

    @Override
    public List<Ingredient> getList() throws ServiceException {
        try {
            dao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e, "Get customer list error");
        }
    }
}
