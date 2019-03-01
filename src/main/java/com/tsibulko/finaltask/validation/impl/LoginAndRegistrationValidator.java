package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.validation.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.LoginAndRegistrationValid;

public class LoginAndRegistrationValidator implements LoginAndRegistrationValid {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static GenericDAO dao;

    @Override
    public boolean isUniqueCustomer(Customer customer) throws LoginAndRegistrationException {
        try {
            dao = daoFactory.getDao(Customer.class);
            if (dao.getStringsFromColumn("login").contains(customer.getLogin())
                    || dao.getStringsFromColumn("email").contains(customer.getEmail())) {
                return false;
            }
            return true;
        } catch (DaoException e) {
            throw new LoginAndRegistrationException(e, "Error in validation of unique customer");
        }
    }

    @Override
    public boolean isExistCustomer(String login) throws LoginAndRegistrationException {
        try {
            dao = daoFactory.getDao(Customer.class);
            if (dao.getStringsFromColumn("login").contains(login)) {
                return true;
            }
            return false;
        } catch (DaoException e) {
            throw new LoginAndRegistrationException(e, "Error in validation of exist customer");
        }
    }

    @Override
    public boolean isExistEmail(String email) throws LoginAndRegistrationException {
        try {

            dao = daoFactory.getDao(Customer.class);
            if (dao.getStringsFromColumn("email").contains(email)) {
                return true;
            }
            return false;
        } catch (DaoException e) {
            throw new LoginAndRegistrationException(e, "Error in validation of exist email");
        }
    }


}
