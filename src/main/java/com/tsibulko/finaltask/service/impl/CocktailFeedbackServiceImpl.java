package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.service.CocktailFeedbackService;
import com.tsibulko.finaltask.service.ServiceException;

import java.util.List;

public class CocktailFeedbackServiceImpl implements CocktailFeedbackService {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CocktailFeedBackSpecificDAO dao;

    public List<CocktaileFeedback> getCocktailFeedbacksByCocktail(Cocktail cocktail) throws ServiceException {
        try {
            dao = (CocktailFeedBackSpecificDAO) daoFactory.getDao(CocktaileFeedback.class);
            return dao.getCocktailFeedbacksByCocktail(cocktail);
        } catch (DaoException e) {
            throw new ServiceException(e, "Create cocktail with ingredients error");
        }
    }


    @Override
    public CocktaileFeedback create(CocktaileFeedback obj) throws ServiceException {
        return null;
    }

    @Override
    public void delete(CocktaileFeedback obj) throws ServiceException {

    }

    @Override
    public CocktaileFeedback getByPK(Integer id) throws ServiceException {
        return null;
    }

    @Override
    public void update(CocktaileFeedback obj) throws ServiceException {

    }

    @Override
    public List<CocktaileFeedback> getList() throws ServiceException {
        return null;
    }
}
