package com.tsibulko.finaltask.builder;

import com.tsibulko.finaltask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Builder<T> {

    T build(HttpServletRequest request) throws ServiceException;
}
