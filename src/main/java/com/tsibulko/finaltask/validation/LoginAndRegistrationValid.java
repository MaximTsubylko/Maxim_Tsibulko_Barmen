package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.sql.SQLException;

public interface LoginAndRegistrationValid extends Validator{
    boolean isUniqueCustomer(Customer customer) throws DaoException, SQLException;

    boolean isExistCustomer(String login) throws DaoException, SQLException;

}
