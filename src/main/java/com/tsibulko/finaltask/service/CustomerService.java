package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.DaoException;

public interface CustomerService extends CRUDService<Customer> {
    Customer getByEmail(String user) throws DaoException, ServiceException;

}
