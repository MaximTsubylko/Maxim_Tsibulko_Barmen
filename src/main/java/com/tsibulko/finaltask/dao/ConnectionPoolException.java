package com.tsibulko.finaltask.dao;

public class ConnectionPoolException extends Exception {

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Exception cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Exception e) {

    }
}
