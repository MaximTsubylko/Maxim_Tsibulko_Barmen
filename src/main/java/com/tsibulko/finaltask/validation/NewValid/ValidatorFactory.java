package com.tsibulko.finaltask.validation.NewValid;

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
        Validator validator;
        switch (type) {
            case COCKTAIL:
                return new CocktailValidator();
            case CUSTOMER:
                return new CustomerValidator();
        }
        throw new UnsupportedOperationException();
    }
}
