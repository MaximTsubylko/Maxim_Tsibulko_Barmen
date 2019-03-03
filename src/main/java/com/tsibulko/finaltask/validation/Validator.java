package com.tsibulko.finaltask.validation;

public interface Validator<T> {
    void doValidation(T entity) throws ValidationException;
}
