package com.tsibulko.finaltask.dao.exception;

import java.sql.SQLException;

public class PersistException extends Exception {

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(Exception e) {

    }
    //provide your code here

}

