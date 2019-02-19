package com.tsibulko.finaltask.validation.exception;

public class ViewDateValidationException extends Exception {
    public ViewDateValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ViewDateValidationException(String message) {
        super(message);
    }
}
