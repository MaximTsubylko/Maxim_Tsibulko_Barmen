package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.validation.impl.LoginAndRegistrationValidator;
import com.tsibulko.finaltask.validation.impl.ServiceDateValidator;
import com.tsibulko.finaltask.validation.impl.ViewDateValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    public Validator getValidator(ValidatorType type) {
        switch (type) {
            case SERVICE:
                return new ServiceDateValidator();
            case VIEW:
                return new ViewDateValidator();
            case LOGANDREG:
                return new LoginAndRegistrationValidator();
        }
        throw new UnsupportedOperationException();
    }
}
