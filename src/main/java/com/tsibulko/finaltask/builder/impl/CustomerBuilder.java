package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.builder.CustomerExtendedBuilder;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomerBuilder implements CustomerExtendedBuilder {


    @Override
    public Customer build(HttpServletRequest request) throws ServiceException {
        Customer customer = new Customer();
        FieldValidator validator = FieldValidator.getInstance();


        if (request.getParameter("id") != null) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            customer.setId(id);
        }
        String firstName = request.getParameter(AppConstant.FIRST_NAME_PARAMETR);
        String secondName = request.getParameter(AppConstant.SECOND_NAME_PARAMETR);
        String email = request.getParameter(AppConstant.EMAIL_PARAMETR);

        if (request.getParameter(AppConstant.LOGIN_PARAMENR) != null) {
            String login = request.getParameter(AppConstant.LOGIN_PARAMENR);
            try {
                validator.simpleStingMatches(login, 20, 4, "login");
                validator.isMatcesByPattern("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$", login);
                validator.isUnique(new String[]{"login"}, Customer.class, login);
            } catch (ValidationException e) {
                ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_INCORRECT_LOGIN);
                throw new ServiceException(e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            customer.setLogin(login);
        }

        if (request.getParameter(AppConstant.PASSWORD_PARAMETR) != null) {
            String password = request.getParameter(AppConstant.PASSWORD_PARAMETR);
            try {
                validator.simpleStingMatches(password, 30, 6, "password");
                validator.isMatcesByPattern("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", password);
            } catch (ValidationException e) {
                ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_INCORRECT_PASSWORD);
                throw new ServiceException(e);
            }
            customer.setPassword(password);
        }

        try {
            validator.isUnique(new String[]{"email"}, Customer.class, email);
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_UNIQUE_EMAIL);
            throw new ServiceException(e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        try {
            validator.isMatcesByPattern("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$",email);
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_INCORRECT_EMAIL);
            throw new ServiceException(e);
        }



        customer.setFirst_name(firstName);
        customer.setSecond_name(secondName);
        customer.setEmail(email);


        return customer;
    }

    @Override
    public Customer buildForEdit(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        FieldValidator validator = FieldValidator.getInstance();

        Customer customer = (Customer) session.getAttribute(AppConstant.SESSION_ATTRIBUTE);
        String firstName = request.getParameter(AppConstant.FIRST_NAME_PARAMETR);
        String secondName = request.getParameter(AppConstant.SECOND_NAME_PARAMETR);
        String email = request.getParameter(AppConstant.EMAIL_PARAMETR);


        try {
            validator.isMatcesByPattern("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$",email);
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_INCORRECT_EMAIL);
            throw new ServiceException(e);
        }

        try {
            validator.isMatcesByPattern("^[а-яА-ЯёЁa-zA-Z]{1,10}$", firstName);
            validator.isMatcesByPattern("^[а-яА-ЯёЁa-zA-Z]{1,10}$", secondName);
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_EDIT_CUSTOMER);
            throw new ServiceException(e);
        }

        customer.setFirst_name(firstName);
        customer.setSecond_name(secondName);
        customer.setEmail(email);
        return customer;
    }

    @Override
    public Customer buildForEnter(HttpServletRequest request) throws ServiceException {
        Customer customer = new Customer();
        FieldValidator validator = FieldValidator.getInstance();
        String login = request.getParameter(AppConstant.LOGIN_PARAMENR);
        if (login.equals("root")){
            customer.setLogin(login);
        } else {
            try {
                validator.simpleStingMatches(login, 20, 4, "login");
                validator.isMatcesByPattern("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$", login);
                validator.isUnique(new String[]{"login"}, Customer.class, login);
            } catch (ValidationException e) {
                ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_INCORRECT_LOGIN);
                throw new ServiceException(e);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        customer.setLogin(login);

        if (request.getParameter(AppConstant.PASSWORD_PARAMETR) != null) {
            String password = request.getParameter(AppConstant.PASSWORD_PARAMETR);
            try {
                validator.simpleStingMatches(password, 30, 6, "password");
                validator.isMatcesByPattern("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", password);
            } catch (ValidationException e) {
                ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_INCORRECT_PASSWORD);
                throw new ServiceException(e);
            }
            customer.setPassword(password);
        }
        return customer;

    }
}
