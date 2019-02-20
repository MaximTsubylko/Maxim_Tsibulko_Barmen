package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.service.CustomerService;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.impl.ServiceDateValidator;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static GenericDAO dao;
    private ServiceDateValidator validator = (ServiceDateValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.SERVICE);

    @Override
    public Customer create(Customer customer) throws SQLException, DaoException, PersistException, ServiceDateValidationException {
        if (validator.isUniqueCustomer(customer)) {
            dao = daoFactory.getDao(Customer.class);
            dao.persist(customer);
            return customer;
        } else {
            throw new ServiceDateValidationException("Not unique user name");
        }
    }

    @Override
    public void delete(Customer customer) throws SQLException, PersistException, DaoException, ServiceDateValidationException {
        if (validator.isExistCustomer(customer)) {
            dao = daoFactory.getDao(Customer.class);
            dao.delete(customer);
        } else {
            throw new ServiceDateValidationException("This customer not exist!");
        }
    }

    @Override
    public Customer getByPK(Integer id) throws DaoException, SQLException, InterruptedException, ServiceDateValidationException {
        dao = daoFactory.getDao(Customer.class);
        if (dao.getByPK(id).isPresent()) {
            Customer customer = (Customer) dao.getByPK(id).get();
            return customer;
        } else {
            throw new ServiceDateValidationException("Can`t find customer with id = "+id);
        }
    }

    @Override
    public void update(Customer customer) throws DaoException, SQLException, PersistException, ServiceDateValidationException {
        if (validator.isExistCustomer(customer)) {
            dao = daoFactory.getDao(Customer.class);
            dao.update(customer);
        } else {
            throw new ServiceDateValidationException("This customer not exist!");
        }
    }

    @Override
    public List<Customer> getList() throws DaoException, SQLException {
        dao = daoFactory.getDao(Customer.class);
        return dao.getAll();
    }
}
