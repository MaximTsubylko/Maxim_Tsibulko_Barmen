package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.service.CustomerFedbackService;
import com.tsibulko.finaltask.service.CustomerService;
import com.tsibulko.finaltask.service.ServiceErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;

import java.util.List;

public class CustomerFeedbackServiceImpl implements CustomerFedbackService {
    private static JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();
    private static BarmanFeedBackSpecificDAO dao;

    public List<BarmenFeedback> getCustomerFeedbacksByCustomer(Customer customer) throws ServiceException {
        try {
            dao = (BarmanFeedBackSpecificDAO) daoFactory.getDao(BarmenFeedback.class);
            return dao.getCocktailFeedbackByCustomer(customer);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_GET_CUSTOMER_FEEDBACK);
        }
    }

    @Override
    public BarmenFeedback create(BarmenFeedback barmenFeedback) throws ServiceException {
        try {
            dao.persist(barmenFeedback);
            return barmenFeedback;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorConstant.ERR_CODE_CREATE_CUSTOMER_FEBACK);
        }

    }

    @Override
    public void delete(BarmenFeedback obj) throws ServiceException {

    }

    @Override
    public BarmenFeedback getByPK(Integer id) throws ServiceException {
        return null;
    }

    @Override
    public void update(BarmenFeedback obj) throws ServiceException {

    }

    @Override
    public List<BarmenFeedback> getList() throws ServiceException {
        return null;
    }
}
