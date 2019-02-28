package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;

import java.sql.SQLException;

public interface LoginAndRegistrationValid extends Validator{
    boolean isUniqueCustomer(Customer customer) throws LoginAndRegistrationException;

    boolean isExistCustomer(String login) throws LoginAndRegistrationException;

    boolean isExistEmail(String email) throws LoginAndRegistrationException;

}
