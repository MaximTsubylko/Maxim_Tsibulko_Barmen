package com.tsibulko.finaltask.validation;

public class ServiceDateValidationException extends Exception {
    public ServiceDateValidationException(String message) {
        super(message);
    }

    public ServiceDateValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
