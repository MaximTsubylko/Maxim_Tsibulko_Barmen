package com.tsibulko.finaltask.validation.NewValid;

public interface Validator<T> {
    void doValidation(T entity) throws ValidationException;
}
