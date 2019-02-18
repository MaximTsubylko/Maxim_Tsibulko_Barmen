package com.tsibulko.finaltask.service.impl;


import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.dao.CocktileSpecificDAO;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.IllegalTypeDAOException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.service.CocktileService;

import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CocktaileService implements CocktileService {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static CocktileSpecificDAO dao;

    private static CocktaileService instance;
    private static Lock lock = new ReentrantLock();

    public static CocktaileService getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new CocktaileService();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public CocktaileService() {
    }

    @Override
    public Cocktaile create(Cocktaile cocktaile) throws SQLException, DaoException, PersistException {
        dao = (CocktileSpecificDAO) daoFactory.getDao(Cocktaile.class);
        dao.persist(cocktaile);
        return cocktaile;
    }

    @Override
    public void delete (Cocktaile cocktaile) throws SQLException, PersistException {
        dao.delete(cocktaile);
    }

    public void uppdateCocktile(Cocktaile cocktaile) throws IllegalTypeDAOException, SQLException, PersistException {
        dao.update(cocktaile);
    }
}
