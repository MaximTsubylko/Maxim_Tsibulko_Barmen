package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.service.ServiceException;

public interface Validator<T> {
    void doValidation(T entity) throws ValidationException, ServiceException;
}
