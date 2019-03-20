package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.CustomerDAO;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.util.EncryptPassword;

import java.util.Optional;

public class LoginServiceImpl {

    private JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();


    public Customer logIn(Customer customer) throws ServiceException {
        CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        CustomerDAO dao;
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            if (!dao.findStringsFromColumn(AppConstant.LOGIN_PARAMENR).contains(customer.getLogin())) {
                ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_CUSTOMER);
                throw new ServiceException("DAO error");
            }
            EncryptPassword.encrypt(customer);
            Optional<Customer> validUser = dao.getByLogin(customer.getLogin());

            if (!customer.getPassword().equals(validUser.get().getPassword())) {
                ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_INCORRECT_PASSWORD);
                throw new ServiceException("DAO error");
            }

            validUser.get().setCocktails(cocktailService.getCocktailByCustomer(validUser.get()));
            return validUser.get();
        } catch (DaoException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_EXIST_CUSTOMER);
            throw new ServiceException(e, "DAO error");
        }
    }


}
