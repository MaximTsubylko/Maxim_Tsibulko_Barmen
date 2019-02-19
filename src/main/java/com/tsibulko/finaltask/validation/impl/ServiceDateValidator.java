package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.validation.ServiceValid;


import java.sql.SQLException;
import java.util.List;

public class ServiceDateValidator implements ServiceValid {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static GenericDAO dao;
    @Override
    public boolean isUniqueCocktil(Cocktaile cocktaile) throws DaoException, SQLException {
        dao = daoFactory.getDao(Cocktaile.class);
        List<Cocktaile> cocktailes = dao.getAll();
        return cocktailes
                .stream()
                .anyMatch(c -> c.equals(cocktaile.getName()));
    }
}
