package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Customer;

public interface CustomerDAO extends GenericDAO<Customer, Integer> {
    Customer getByLogin(String user) throws DaoException;

    Customer getByEmail(String email) throws DaoException;

}
