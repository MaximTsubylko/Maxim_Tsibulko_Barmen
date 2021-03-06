package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.dao.ConnectionPoolException;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.PersistException;
import com.tsibulko.finaltask.service.CRUDService;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
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

class CustomerServiceImplTest {
    CRUDService<Customer> customerService = ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
    List<Customer> customers = JSONParser.CustomerParse("src/test/resources/JsonData/CustomerData.json");

    CustomerServiceImplTest() throws FileNotFoundException {
    }

    @BeforeEach
    void setUp() throws InterruptedException, SQLException, ConnectionPoolException, DaoException, IOException {
        InMemoryDBUtil.fill();
    }

    @AfterEach
    void tearDown() throws  SQLException, ConnectionPoolException, IOException {
        InMemoryDBUtil.drop();
    }

    @Test
    void create() throws ServiceException {
        Customer customer = customers.get(1);
        customer.setId(4);
        customer.setLogin("test");
        customer.setEmail("test");
        assertEquals(customerService.create(customer), customer);
    }

    @Test
    void delete() throws ServiceException {
        Customer customer = new Customer();
        customer.setLogin("TestLogin");
        customer.setEmail("TestEmail");
        customer.setPassword("123");
        customer.setRole_id(1);
        customer.setState(1);
        customer.setId(4);
        customerService.delete(customerService.create(customer));
        assertEquals(customerService.getList(), customers);
    }

    @Test
    void getByPK() throws InterruptedException, SQLException, DaoException, ServiceException {
        assertEquals(customers.get(0), customerService.getByPK(1));
    }

    @Test
    void update() throws SQLException, PersistException, DaoException, InterruptedException, ServiceException {
        Customer customer = customers.get(1);
        customer.setFirst_name("asdasd");
        customerService.update(customer);
        assertEquals(customer, customerService.getByPK(2));
    }

    @Test
    void getList() throws SQLException, DaoException, ServiceException {
        assertEquals(customers, customerService.getList());
    }
}