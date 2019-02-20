package com.tsibulko.finaltask.dao.impl;

import com.tsibulko.finaltask.bean.Cocktail;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class IngredientDAOTest {
    DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    IngredientSpecificDAO dao;
    CocktileSpecificDAO daoCocktail;
    List<Cocktail> cocktailes = JSONParser.CocktaileParse("src/test/resources/JsonData/CocktileData.json");
    List<Ingredient> ingredients = JSONParser.IngredientParse("src/test/resources/JsonData/IngredientData.json");

    IngredientDAOTest() throws FileNotFoundException {
    }

    @BeforeEach
    void setUp() throws DaoException, InterruptedException, SQLException, ConnectionPoolException, IOException {
        InMemoryDBUtil.fill();
        dao = (IngredientSpecificDAO) daoFactory.getDao(Ingredient.class);
        daoCocktail = (CocktileSpecificDAO) daoFactory.getDao(Cocktail.class);
    }

    @AfterEach
    void tearDown() throws SQLException, IOException, ConnectionPoolException, InterruptedException {
        InMemoryDBUtil.drop();
    }

    @Test
    void getByPK() throws DaoException, InterruptedException, SQLException {
        Ingredient ingredient = ingredients.get(0);
        assertEquals(ingredient, dao.getByPK(1).get());
    }

    @Test
    void getAll() throws SQLException, DaoException {
        assertEquals(ingredients, dao.getAll());
    }

    @Test
    void persist() throws DaoException, InterruptedException, SQLException, PersistException {
        Ingredient ingredient = ingredients.get(1);
        ingredient.setName("Test");
        ingredient.setDescription("Test");
        ingredient.setId(5);
        dao.persist(ingredient);
        assertEquals(ingredient, dao.getByPK(5).get());
    }

    @Test
    void delete() throws SQLException, PersistException, InterruptedException, DaoException {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("test");
        ingredient.setDescription("test");
        ingredient.setId(5);
        dao.persist(ingredient);
        dao.delete(ingredient);
        assertEquals(ingredients, dao.getAll());

    }

    @Test
    void update() throws SQLException, PersistException, InterruptedException, DaoException {
        Ingredient ingredient = ingredients.get(1);
        ingredient.setName("Test");
        dao.update(ingredient);
        assertEquals(ingredient, dao.getByPK(2).get());
    }

    @Test
    void getIngredientByCocktail() throws SQLException, DaoException {
        Cocktail cocktaile = cocktailes.get(0);
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredients.get(0));
        ingredientList.add(ingredients.get(1));
        assertEquals(ingredientList, dao.getIngredientByCocktail(cocktaile));
    }

    @Test
    void setCockrailIngredients() throws SQLException, PersistException, DaoException {
        Cocktail cocktaile = new Cocktail();
        cocktaile.setName("Test");
        cocktaile.setDescription("Test descr");
        cocktaile.setId(3);
        cocktaile.setPrice(1000);

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredients.get(0));
        ingredientList.add(ingredients.get(1));

        daoCocktail.persist(cocktaile);
        dao.setCockrailIngredients(cocktaile,ingredientList);


        assertEquals(ingredientList,dao.getIngredientByCocktail(cocktaile));
    }
}