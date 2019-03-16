package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.service.ServiceErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.util.EncryptPassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class LoginServiceImpl {

    private JdbcDaoFactory daoFactory = JdbcDaoFactory.getInstance();


    public Customer logIn(Customer customer) throws ServiceException {
        CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        CustomerDAO dao;
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            if (!dao.findStringsFromColumn(AppConstant.LOGIN_PARAMENR).contains(customer.getLogin())) {
                throw new ServiceException(ServiceErrorConstant.ERR_CODE_NOT_EXIST_CUSTOMER);
            }
            EncryptPassword.encrypt(customer);
            Optional<Customer> validUser = dao.getByLogin(customer.getLogin());

            if (!customer.getPassword().equals(validUser.get().getPassword())) {
                throw new ServiceException(ServiceErrorConstant.ERR_CODE_INCORRECT_PASSWORD);
            }

            validUser.get().setCocktails(cocktailService.getCocktailByCustomer(validUser.get()));
            return validUser.get();
        } catch (DaoException e) {
            throw new ServiceException(e,ServiceErrorConstant.ERR_CODE_DAO_ERROR);
        }
    }


}
