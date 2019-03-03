package com.tsibulko.finaltask.service;

import java.util.List;

public interface CRUDService<T> {
    T create(T obj) throws ServiceException;

    void delete(T obj) throws ServiceException;

    T getByPK(Integer id) throws ServiceException;

    void update(T obj) throws ServiceException;

    List<T> getList() throws ServiceException;

}
