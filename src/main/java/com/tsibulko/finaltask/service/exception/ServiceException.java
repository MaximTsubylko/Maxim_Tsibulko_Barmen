package com.tsibulko.finaltask.service.exception;


public class ServiceException extends Exception {
    public ServiceException(String error) {
    }

    public ServiceException(Exception e) {
    }

    public ServiceException(Exception e, String encrypt_password_error) {
    }
}

