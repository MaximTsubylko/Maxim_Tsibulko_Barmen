package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.util.DBUtil.InMemoryDBUtil;
import com.tsibulko.finaltask.util.TestUtil.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDAOTest {
    DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    GenericDAO dao;
    List<Customer> customers = JSONParser.CustomerParse("src/test/resources/JsonData/CustomerData.json");

    CustomerDAOTest() throws FileNotFoundException {
    }


    @BeforeEach
    void setUp() throws DaoException, InterruptedException, SQLException, ConnectionPoolException, IOException {
        InMemoryDBUtil.fill();
        dao = daoFactory.getDao(Customer.class);
    }

    @AfterEach
    void tearDown() throws SQLException, IOException, ConnectionPoolException, InterruptedException {
        InMemoryDBUtil.drop();
    }

    @Test
    void getByPK() throws DaoException, InterruptedException, SQLException {
        Customer customer = customers.get(1);
        assertEquals(customer, dao.getByPK(2).get());
    }

    @Test
    void getAll() throws SQLException, DaoException {
        assertEquals(customers, dao.getAll());
    }

    @Test
    void persist() throws SQLException, InterruptedException, DaoException, PersistException {
        Customer customer = customers.get(1);
        customer.setLogin("TestLogin");
        customer.setEmail("TestEmail");
        customer.setId(4);
        dao.persist(customer);
        assertEquals(customer, dao.getByPK(4).get());
    }

    @Test
    void delete() throws SQLException, PersistException, DaoException {
        Customer customer = new Customer();
        customer.setLogin("TestLogin");
        customer.setEmail("TestEmail");
        customer.setPassword("123");
        customer.setRole_id(1);
        customer.setState(1);

        dao.persist(customer);
        dao.delete(customer);

        assertEquals(customers, dao.getAll());
    }

    @Test
    void update() throws SQLException, PersistException, InterruptedException, DaoException {
        List<Customer> cu = dao.getAll();
        Customer customer = customers.get(1);
        customer.setLogin("TestLogin");
        dao.update(customer);
        assertEquals(customer, dao.getByPK(2).get());
    }
}