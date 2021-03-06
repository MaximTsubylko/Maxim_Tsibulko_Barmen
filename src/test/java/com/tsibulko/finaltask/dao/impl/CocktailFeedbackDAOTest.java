package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.ConnectionPoolException;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.PersistException;
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

class CocktailFeedbackDAOTest {
    DaoFactory daoFactory = JdbcDaoFactory.getInstance();
    GenericDAO dao;
    List<CocktaileFeedback> feedbacks = JSONParser.CocktileFeedbackParse("src/test/resources/JsonData/CocktilFeedbackData.json");

    CocktailFeedbackDAOTest() throws FileNotFoundException {
    }


    @BeforeEach
    void setUp() throws DaoException, InterruptedException, SQLException, ConnectionPoolException, IOException {
        InMemoryDBUtil.fill();
        dao = daoFactory.getDao(CocktaileFeedback.class);
    }

    @AfterEach
    void tearDown() throws SQLException, IOException, ConnectionPoolException, InterruptedException {
        InMemoryDBUtil.drop();
    }

    @Test
    void getByPK() throws DaoException, InterruptedException, SQLException {
        assertEquals(feedbacks.get(0), dao.getByPK(1).get());
    }

    @Test
    void getAll() throws SQLException, DaoException {
        assertEquals(feedbacks, dao.getAll());
    }

    @Test
    void persist() throws SQLException, InterruptedException, DaoException, PersistException {
        CocktaileFeedback cocktaileFeedback = new CocktaileFeedback(1, 2, 1, "test", "");
        dao.persist(cocktaileFeedback);
        assertEquals(cocktaileFeedback, dao.getByPK(2).get());
    }

    @Test
    void delete() throws SQLException, PersistException, DaoException {
        CocktaileFeedback cocktaileFeedback = new CocktaileFeedback(1, 2, 1, "test", "");
        List<CocktaileFeedback> s = dao.getAll();
        dao.persist(cocktaileFeedback);
        dao.delete(cocktaileFeedback);
        assertEquals(feedbacks, dao.getAll());
    }

    @Test
    void update() throws SQLException, PersistException, InterruptedException, DaoException {
        CocktaileFeedback cocktaileFeedback = feedbacks.get(0);
        cocktaileFeedback.setMark(2);
        dao.update(cocktaileFeedback);
        assertEquals(cocktaileFeedback, dao.getByPK(1).get());
    }
}