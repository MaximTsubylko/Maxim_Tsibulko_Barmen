package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.UserRole;
import com.tsibulko.finaltask.bean.UserState;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.util.EncryptPassword;

public class RegistrationServiceImpl {
    public Customer signUp(Customer customer) throws ServiceException {
        JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();
        try {
            customer.setRole_id(UserRole.CUSTOMER.getId());
            customer.setState(UserState.WAITING_CONFIRMATION.getId());

            GenericDAO<Customer, Integer> userDao = daoFactory.getDao(Customer.class);
            EncryptPassword.encrypt(customer);
            customer = userDao.persist(customer);
            return customer;
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_CUSTOMER);
            throw new ServiceException(e, "DAO error");
        }
    }
}
