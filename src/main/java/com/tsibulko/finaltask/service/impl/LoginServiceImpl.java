package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;
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

    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CustomerDAO dao;
    private static Map<String, Customer> authenticatedCustomer = new WeakHashMap<>();


    public Customer logIn(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        Customer customer = new Customer();
        customer.setLogin(request.getParameter(AppConstant.LOGIN_PARAMENR));
        customer.setPassword(request.getParameter(AppConstant.PASSWORD_PARAMETR));
        try {
            dao = (CustomerDAO) daoFactory.getDao(Customer.class);
            if (!dao.findStringsFromColumn(AppConstant.LOGIN_PARAMENR).contains(customer.getLogin())) {
                throw new ServiceException("Not match customer with this login");
            }
            EncryptPassword.encrypt(customer);
            Optional<Customer> validUser = dao.getByLogin(customer.getLogin());
            if (!validUser.isPresent()){
                throw new ServiceException("err");
            }
            if (!customer.getPassword().equals(validUser.get().getPassword())) {
                throw new ServiceException("Incorrect password!");
            }

            validUser.get().setCocktails(cocktailService.getCocktailByCustomer(validUser.get()));
            session.setAttribute(AppConstant.SESSION_ATTRIBUTE, validUser.get());
            authenticatedCustomer.put(session.getId(), validUser.get());
            return validUser.get();
        } catch (DaoException e) {
            throw new ServiceException("Incorrect login or password", e);
        }
    }

    public void logout(HttpSession session) {
        session.setAttribute(AppConstant.SESSION_ATTRIBUTE, null);
    }

}
