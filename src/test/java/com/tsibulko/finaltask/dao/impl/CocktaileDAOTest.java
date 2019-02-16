package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.*;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.util.DBUtil.InMemoryDBUtil;
import com.tsibulko.finaltask.util.TestUtil.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CocktaileDAOTest {
    DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    CocktileSpecificDAO dao;
    List<Cocktaile> cocktailes = JSONParser.CocktaileParse("src/test/resources/JsonData/CocktileData.json");
    List<Customer> customers = JSONParser.CustomerParse("src/test/resources/JsonData/CustomerData.json");

    CocktaileDAOTest() throws FileNotFoundException {
    }

    @BeforeEach
    void setUp() throws InterruptedException, SQLException, DaoException, IOException, ConnectionPoolException {
        InMemoryDBUtil.fill();
        dao = (CocktileSpecificDAO) daoFactory.getDao(Cocktaile.class) ;
    }

    @AfterEach
    void tearDown() throws IOException, SQLException, ConnectionPoolException, InterruptedException {
        InMemoryDBUtil.drop();
    }

    @Test
    void getByPK() throws DaoException, InterruptedException, SQLException {
        Cocktaile cocktaile = cocktailes.get(0);
        assertEquals(cocktaile,dao.getByPK(1).get());
    }

    @Test
    void getAll() throws SQLException, DaoException {
        assertEquals(cocktailes,dao.getAll());
    }

    @Test
    void persist() throws SQLException, InterruptedException, DaoException {
        Cocktaile cocktaile = new Cocktaile();
        cocktaile.setId(3);
        cocktaile.setName("TestPersist");
        cocktaile.setPrice(1000);
        cocktaile.setDescription("TestPersist discription");
        dao.persist(cocktaile);
        assertEquals(cocktaile,dao.getByPK(3).get());

    }

    @Test
    void delete() throws SQLException, DaoException, PersistException {
        List<Cocktaile> actualCocktailes;
        Cocktaile cocktaile1 = new Cocktaile();
        cocktaile1.setId(3);
        cocktaile1.setName("TestPersist");
        cocktaile1.setPrice(1000);
        cocktaile1.setDescription("TestPersist discription");
        dao.persist(cocktaile1);
        dao.delete(cocktaile1);
        actualCocktailes = dao.getAll();
        assertEquals(cocktailes,actualCocktailes);
    }

    @Test
    void update() throws SQLException, PersistException, InterruptedException, DaoException {
        Cocktaile cocktaile = cocktailes.get(0);
        cocktaile.setPrice(1100);
        dao.update(cocktaile);
        assertEquals(cocktaile,dao.getByPK(1).get());
    }

    @Test
    void getCocktaileByCustomer() throws SQLException {
        List<Cocktaile> cocktaileList = new ArrayList<>();
        cocktaileList.add(cocktailes.get(0));
        cocktaileList.add(cocktailes.get(1));
        assertEquals(cocktaileList,dao.getCocktaileByCustomer(customers.get(2)));
    }
}