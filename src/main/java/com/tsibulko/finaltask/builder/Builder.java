package com.tsibulko.finaltask.builder;

import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;

public interface Builder<T> {

    T build(HttpServletRequest request) throws ServiceException;
}
