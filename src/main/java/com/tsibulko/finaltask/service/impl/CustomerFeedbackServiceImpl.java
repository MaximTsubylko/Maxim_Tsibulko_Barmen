package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.BarmanFeedBackSpecificDAO;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.CustomerFedbackService;
import com.tsibulko.finaltask.service.ServiceException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CustomerFeedbackServiceImpl implements CustomerFedbackService {
    private JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();

    public List<BarmenFeedback> getCustomerFeedbacksByCustomer(Customer customer) throws ServiceException {
        BarmanFeedBackSpecificDAO dao;
        try {
            dao = (BarmanFeedBackSpecificDAO) daoFactory.getDao(BarmenFeedback.class);
            return dao.getCocktailFeedbackByCustomer(customer);
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_GET_CUSTOMER_FEEDBACK);
            throw new ServiceException(e, "Dao error");
        }
    }

    public void setAverageMarkToCustomer (List<Customer> customers) throws ServiceException {
        BarmanFeedBackSpecificDAO dao;
        try {
            dao = (BarmanFeedBackSpecificDAO) daoFactory.getDao(BarmenFeedback.class);
            for (Customer customer : customers) {
                NumberFormat formatter = new DecimalFormat("#0.00");
                customer.setAverageMark(formatter.format(dao.getMarkByCustomer(customer)));
            }

        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_GET_CUSTOMER_FEEDBACK);
            throw new ServiceException(e, "Dao error");
        }
    }

    @Override
    public BarmenFeedback create(BarmenFeedback barmenFeedback) throws ServiceException {
        BarmanFeedBackSpecificDAO dao;
        try {
            dao = (BarmanFeedBackSpecificDAO) daoFactory.getDao(BarmenFeedback.class);
            dao.persist(barmenFeedback);
            return barmenFeedback;
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_CUSTOMER_FEBACK);
            throw new ServiceException(e, "Dao error");
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
