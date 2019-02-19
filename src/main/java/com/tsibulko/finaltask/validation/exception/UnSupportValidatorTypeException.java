package com.tsibulko.finaltask.validation.exception;

public class UnSupportValidatorTypeException extends Exception {
    public UnSupportValidatorTypeException() {
        super();
    }

    public UnSupportValidatorTypeException(String message) {
        super(message);
    }

    public UnSupportValidatorTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
