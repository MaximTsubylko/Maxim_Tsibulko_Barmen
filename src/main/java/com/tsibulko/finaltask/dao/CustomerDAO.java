package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.bean.Customer;

import java.util.Optional;

public interface CustomerDAO extends GenericDAO<Customer, Integer> {
    Optional<Customer> getByLogin(String user) throws DaoException;

    Customer getByEmail(String email) throws DaoException;

}
