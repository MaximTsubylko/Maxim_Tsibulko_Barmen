package com.tsibulko.finaltask.dao;

import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;

public class FactoryProducer {
    private static volatile FactoryProducer instance;
    private FactoryProducer() {}

    public FactoryProducer getInstance() {
        if (instance == null) {
            synchronized (FactoryProducer.class) {
                if (instance == null) {
                    instance = new FactoryProducer();
                }
            }
        }
        return instance;
    }

    public static DaoFactory getDaoFactory(DaoFactoryType type) {
        switch (type){
            case JDBC:
                return new JdbcDaoFactory();
        }
        throw new UnsupportedOperationException();
    }
}