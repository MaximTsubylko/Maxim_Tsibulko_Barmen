package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.service.impl.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceFactory {
    private static ServiceFactory instance;
    private static Lock lock = new ReentrantLock();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ServiceFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public CRUDService getService(ServiceTypeEnum type) throws IllegalStateException {
        switch (type) {
            case CUSTOMER_FEEDBACK:
                return new CustomerFeedbackServiceImpl();
            case COCKTAIL_FEEDBACK:
                return new CocktailFeedbackServiceImpl();
            case INGREDIENT:
                return new IngredientServiceImpl();
            case CUSTOMER:
                return new CustomerServiceImpl();
            case COCKTAIL:
                return new CocktailServiceImpl();
            default:
                throw new IllegalStateException();
        }

    }

}
