package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.validation.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.ServiceDateValidationException;

import java.util.List;

public interface CRUDService<T> {
    T create(T obj) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException;

    void delete(T obj) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException;

    T getByPK(Integer id) throws ServiceDateValidationException, ServiceException;

    void update(T obj) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException;

    List<T> getList() throws ServiceException;

}
