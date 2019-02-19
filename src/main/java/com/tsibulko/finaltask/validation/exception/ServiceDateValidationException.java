package com.tsibulko.finaltask.validation.exception;

public class ServiceDateValidationException extends Exception {
    public ServiceDateValidationException(String message) {
        super(message);
    }

    public ServiceDateValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
