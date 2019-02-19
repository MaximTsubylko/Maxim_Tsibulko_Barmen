package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.validation.Validator;
import com.tsibulko.finaltask.validation.exception.UnSupportValidatorTypeException;
import com.tsibulko.finaltask.validation.impl.ServiceDateValidator;
import com.tsibulko.finaltask.validation.impl.ViewDateValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class ValidatorFactory {
    private static Lock lock = new ReentrantLock();
    private static ValidatorFactory instance;

    public static ValidatorFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ValidatorFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public Validator getValidator(ValidatorType type){
        Validator validator = null;
        switch (type) {
            case SERVICE:
                validator = new ServiceDateValidator();
                break;
            case VIEW:
                validator = new ViewDateValidator();
                break;
        }
        return validator;
    }
}
