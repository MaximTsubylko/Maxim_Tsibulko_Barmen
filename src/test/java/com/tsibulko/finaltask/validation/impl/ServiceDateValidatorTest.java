package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.util.DBUtil.InMemoryDBUtil;
import com.tsibulko.finaltask.validation.ServiceValid;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceDateValidatorTest {
    ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
    ServiceValid validator;

    @BeforeEach
    void setUp() throws InterruptedException, SQLException, ConnectionPoolException, DaoException, IOException {
        InMemoryDBUtil.fill();
        validator = (ServiceValid) validatorFactory.getValidator(ValidatorType.SERVICE);
    }

    @AfterEach
    void tearDown() throws InterruptedException, SQLException, ConnectionPoolException, IOException {
        InMemoryDBUtil.drop();
    }

    @Test
    void isUniqueCocktil() throws SQLException, DaoException {
        Cocktaile cocktaile = new Cocktaile();
        cocktaile.setName("test name");
        assertTrue(validator.isUniqueCocktil(cocktaile));
    }

    @Test
    void isExistCoctil() throws SQLException, DaoException {
        Cocktaile cocktaile = new Cocktaile();
        cocktaile.setId(1);
        cocktaile.setName("Blood Mary");
        cocktaile.setDescription("Blood Mary description");
        cocktaile.setPrice(1000);
        assertTrue(validator.isExistCoctil(cocktaile));
    }
}