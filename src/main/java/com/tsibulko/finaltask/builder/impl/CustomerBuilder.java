package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.builder.CustomerExtendedBuilder;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomerBuilder implements CustomerExtendedBuilder {


    @Override
    public Customer build(HttpServletRequest request) throws ServiceException {
        Customer customer = new Customer();


        if (request.getParameter("id") != null) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            customer.setId(id);
        }
        String firstName = request.getParameter(AppConstant.FIRST_NAME_PARAMETR);
        String secondName = request.getParameter(AppConstant.SECOND_NAME_PARAMETR);
        String email = request.getParameter(AppConstant.EMAIL_PARAMETR);

        if (request.getParameter(AppConstant.LOGIN_PARAMENR) != null) {
            String login = request.getParameter(AppConstant.LOGIN_PARAMENR);
            customer.setLogin(login);
        }

        if (request.getParameter(AppConstant.PASSWORD_PARAMETR) != null) {
            String password = request.getParameter(AppConstant.PASSWORD_PARAMETR);
            customer.setPassword(password);
        }

        customer.setFirst_name(firstName);
        customer.setSecond_name(secondName);
        customer.setEmail(email);


        return customer;
    }

    @Override
    public Customer buildForEdit(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute(AppConstant.SESSION_ATTRIBUTE);
        customer.setFirst_name(request.getParameter(AppConstant.FIRST_NAME_PARAMETR));
        customer.setSecond_name(request.getParameter(AppConstant.SECOND_NAME_PARAMETR));
        customer.setEmail(request.getParameter(AppConstant.EMAIL_PARAMETR));
        return customer;
    }
}
