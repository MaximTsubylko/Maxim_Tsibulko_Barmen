package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import java.util.Map;

public class ServiceFactory {
    private static ServiceFactory INSTANCE;
    private static Map<ServiceTypeEnum, Class<CocktailServiceImpl>> serviceMap;
    private ServiceFactory(){
    }

    public static ServiceFactory getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ServiceFactory();
        }

        return INSTANCE;
    }

    public CRUDService getSrvice(ServiceTypeEnum type) throws IllegalStateException{
        switch (type){
            case CUSTOMER:
                return new CustomerServiceImpl();
            case COCKTAILE:
                return new CocktailServiceImpl();
            default:
                throw new IllegalStateException();
        }

    }

}
