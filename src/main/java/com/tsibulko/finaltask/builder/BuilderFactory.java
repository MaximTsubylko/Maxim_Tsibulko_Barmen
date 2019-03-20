package com.tsibulko.finaltask.builder;


import com.tsibulko.finaltask.bean.*;
import com.tsibulko.finaltask.builder.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuilderFactory {

    private static BuilderFactory instance;
    private static Lock lock = new ReentrantLock();
    private static Map<Class, Builder> builderMap = new HashMap<>();

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
        builderMap.put(BarmenFeedback.class, new BarmanFeedbackBuilder());
        builderMap.put(CocktaileFeedback.class, new CocktailFeedbackBuilder());
        builderMap.put(Ingredient.class, new IngredientBuilder());


    }

    public Builder getBuilder(Class type) {
        return builderMap.get(type);
    }

}
