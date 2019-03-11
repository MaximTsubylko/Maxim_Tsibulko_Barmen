package com.tsibulko.finaltask.builder;


import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.builder.impl.CocktailBuiler;
import com.tsibulko.finaltask.builder.impl.CustomerBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuilderFactory {

    private static BuilderFactory instance;
    private static Lock lock = new ReentrantLock();
    private static Map<Class,Builder> builderMap = new HashMap<>();

    public static BuilderFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new BuilderFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    private BuilderFactory() {
        builderMap.put(Customer.class, new CustomerBuilder());
        builderMap.put(Cocktail.class, new CocktailBuiler());
    }

    public Builder getBuilder(Class type){
        return builderMap.get(type);
    }

}
