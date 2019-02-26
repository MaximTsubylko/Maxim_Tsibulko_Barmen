package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.validation.LoginAndRegistrationValid;

import java.sql.SQLException;
import java.util.List;

public class LoginAndRegistrationValidator implements LoginAndRegistrationValid {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static GenericDAO dao;

    @Override
    public boolean isUniqueCustomer(Customer customer) throws DaoException, SQLException {
        dao = daoFactory.getDao(Customer.class);
        if (dao.getStringsFromColumn("login").contains(customer.getLogin())
                || dao.getStringsFromColumn("email").equals(customer.getEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isExistCustomer(String login) throws DaoException, SQLException {
        dao = daoFactory.getDao(Customer.class);
        if (dao.getStringsFromColumn("login").contains(login)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistEmail(String email) throws DaoException {
        dao = daoFactory.getDao(Customer.class);
        if (dao.getStringsFromColumn("email").contains(email)) {
            return true;
        }
        return false;
    }


}
