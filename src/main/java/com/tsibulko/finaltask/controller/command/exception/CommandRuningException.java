package com.tsibulko.finaltask.controller.command.exception;

public class CommandRuningException extends Exception {
    public CommandRuningException() {
        super();
    }

    public CommandRuningException(String message) {
        super(message);
    }

    public CommandRuningException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandRuningException(Throwable cause) {
        super(cause);
    }

    protected CommandRuningException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
