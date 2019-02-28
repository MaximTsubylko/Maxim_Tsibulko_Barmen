package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface CRUDService<T> {
    T create(T obj) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException;

    void delete(T obj) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException;

    T getByPK(Integer id) throws ServiceDateValidationException, ServiceException;

    void update(T obj) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException;

    List<T> getList() throws ServiceException;

}
