package com.tsibulko.finaltask.service.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.dao.exception.ConnectionPoolException;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.util.DBUtil.InMemoryDBUtil;
import com.tsibulko.finaltask.util.TestUtil.parser.JSONParser;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CocktailServiceImplTest {
    CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
    List<Cocktail> cocktJson = JSONParser.CocktaileParse("src/test/resources/JsonData/CocktileData.json");
    List<Ingredient> ingredients = JSONParser.IngredientParse("src/test/resources/JsonData/IngredientData.json");

    CocktailServiceImplTest() throws FileNotFoundException {
    }

    @BeforeEach
    void setUp() throws InterruptedException, SQLException, ConnectionPoolException, DaoException, IOException {
        InMemoryDBUtil.fill();
    }

    @AfterEach
    void tearDown() throws InterruptedException, SQLException, ConnectionPoolException, IOException {
        InMemoryDBUtil.drop();
    }

    @Test
    void create() throws SQLException, PersistException, DaoException, ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        Cocktail cocktail = new Cocktail();
        cocktail.setName("test");
        assertEquals(cocktailService.create(cocktail), cocktail);
    }

    @Test
    void delete() throws SQLException, DaoException, PersistException, ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        List<Cocktail> cocktails = cocktailService.getList();
        Cocktail cocktail = new Cocktail();
        cocktail.setId(3);
        cocktail.setName("test");
        cocktailService.create(cocktail);
        cocktailService.delete(cocktail);
        assertEquals(cocktails, cocktJson);
    }

    @Test
    void getByPK() throws InterruptedException, SQLException, ServiceDateValidationException, DaoException, PersistException, ServiceException, LoginAndRegistrationException {
        Cocktail cocktail = new Cocktail();
        cocktail.setId(3);
        cocktail.setName("test");
        cocktailService.create(cocktail);
        assertEquals(cocktailService.getByPK(3), cocktail);
    }

    @Test
    void update() throws SQLException, PersistException, DaoException, ServiceDateValidationException, InterruptedException, ServiceException, LoginAndRegistrationException {
        Cocktail cocktail = cocktJson.get(0);
        cocktail.setName("Test");
        cocktailService.update(cocktail);
        assertEquals(cocktail, cocktailService.getByPK(cocktail.getId()));
    }

    @Test
    void getCoctilList() throws SQLException, DaoException, ServiceException {
        assertEquals(cocktJson, cocktailService.getList());
    }

    @Test
    void createCocktailWithIngredients() throws SQLException, PersistException, DaoException, ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        Cocktail cocktail = new Cocktail();
        cocktail.setId(3);
        cocktail.setName("test");

        List<Ingredient> ingr = new ArrayList();
        ingr.add(ingredients.get(0));
        ingr.add(ingredients.get(1));
        cocktail.setIngredients(ingr);
        assertEquals(cocktail, cocktailService.createCocktailWithIngredients(cocktail, ingredients));
    }
}