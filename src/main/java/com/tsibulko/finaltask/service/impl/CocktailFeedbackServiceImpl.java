package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.dao.CocktailFeedBackSpecificDAO;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.CocktailFeedbackService;
import com.tsibulko.finaltask.service.ServiceException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CocktailFeedbackServiceImpl implements CocktailFeedbackService {
    private JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();

    public void setAverageMarkToCocktais (List<Cocktail> cocktais) throws ServiceException {
        CocktailFeedBackSpecificDAO dao;
        try {
            dao = (CocktailFeedBackSpecificDAO) daoFactory.getDao(CocktaileFeedback.class);
            for (Cocktail cocktail : cocktais) {
                NumberFormat formatter = new DecimalFormat("#0.00");
                cocktail.setAverageMark(formatter.format(dao.getMarkByCocktail(cocktail)));
            }

        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_GET_CUSTOMER_FEEDBACK);
            throw new ServiceException(e, "Dao error");
        }
    }

    public List<CocktaileFeedback> getCocktailFeedbacksByCocktail(Cocktail cocktail) throws ServiceException {
        CocktailFeedBackSpecificDAO dao;
        try {
            dao = (CocktailFeedBackSpecificDAO) daoFactory.getDao(CocktaileFeedback.class);
            return dao.getCocktailFeedbacksByCocktail(cocktail);
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_GET_COCKTAIL_FEEDBACK);
            throw new ServiceException(e,"Dao error");
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
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_COCKTAIL_FEBACK);
            throw new ServiceException(e, "Dao error");
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
