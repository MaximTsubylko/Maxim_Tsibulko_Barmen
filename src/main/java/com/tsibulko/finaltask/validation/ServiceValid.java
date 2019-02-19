package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.dao.exception.DaoException;

import java.sql.SQLException;

public interface ServiceValid extends Validator {
    boolean isUniqueCocktil(Cocktaile cocktaile) throws DaoException, SQLException;
    boolean isExistCoctil(Cocktaile cocktaile) throws DaoException, SQLException;
}
