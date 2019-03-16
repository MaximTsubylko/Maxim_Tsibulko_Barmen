package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.service.CocktailFeedbackService;
import com.tsibulko.finaltask.service.ServiceErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;

import java.util.List;

public class CocktailFeedbackServiceImpl implements CocktailFeedbackService {
    private JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();


    public List<CocktaileFeedback> getCocktailFeedbacksByCocktail(Cocktail cocktail) throws ServiceException {
        CocktailFeedBackSpecificDAO dao;
        try {
            dao = (CocktailFeedBackSpecificDAO) daoFactory.getDao(CocktaileFeedback.class);
            return dao.getCocktailFeedbacksByCocktail(cocktail);
        } catch (DaoException e) {
            throw new ServiceException(e,ServiceErrorConstant.ERR_CODE_GET_COCKTAIL_FEEDBACK);
        }
    }


    @Override
    public CocktaileFeedback create(CocktaileFeedback cocktaileFeedback) throws ServiceException {
        CocktailFeedBackSpecificDAO dao;
        try {
            dao = (CocktailFeedBackSpecificDAO) daoFactory.getDao(CocktaileFeedback.class);
            dao.persist(cocktaileFeedback);
            return cocktaileFeedback;
        } catch (DaoException e) {
            throw new ServiceException(e,ServiceErrorConstant.ERR_CODE_CREATE_COCKTAIL_FEBACK);
        }
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
