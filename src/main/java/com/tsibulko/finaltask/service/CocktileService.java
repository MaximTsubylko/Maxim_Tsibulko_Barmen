package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface CocktileService extends CRUDService<Cocktaile> {
    List<Cocktaile> getCoctilList() throws DaoException, SQLException;
}
