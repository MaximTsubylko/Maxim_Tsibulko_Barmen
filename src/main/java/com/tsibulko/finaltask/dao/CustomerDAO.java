package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Customer;

public interface CustomerDAO extends GenericDAO<Customer, Integer> {
    Customer findByLogin(Customer user) throws DaoException;
}
