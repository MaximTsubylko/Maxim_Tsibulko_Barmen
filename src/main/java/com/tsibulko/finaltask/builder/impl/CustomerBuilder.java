package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.dto.UserDto;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.CustomerValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;

public class CustomerBuilder implements Builder<Customer> {

    private static final String EMAIL_PARAMETR = "email";
    private static final String LOGIN_PARAMENR = "login";
    private static final String PASSWORD_PARAMETR = "password";

    @Override
    public Customer build(HttpServletRequest request) throws ServiceException {
        Customer customer = new Customer();
        customer.setLogin(request.getParameter(LOGIN_PARAMENR));
        customer.setPassword(request.getParameter(PASSWORD_PARAMETR));
        customer.setEmail(request.getParameter(EMAIL_PARAMETR));

        CustomerValidator validator = new CustomerValidator();
        try {
            validator.doValidation(customer);
        } catch (ValidationException e) {
            throw new ServiceException("error");
        }

        return customer;
    }


    public UserDto bu(HttpServletRequest request) throws ServiceException{
        UserDto dto = new UserDto();
        return dto;
    }
}
