package com.tsibulko.finaltask.service;

import com.tsibulko.finaltask.service.impl.CocktaileService;

import java.util.Map;

public class ServiceFactory {
    private static ServiceFactory INSTANCE;
    private static Map<ServiceTypeEnum, Class<CocktaileService>> serviceMap;
    private ServiceFactory(){
    }

    public static ServiceFactory getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ServiceFactory();
        }

        return INSTANCE;
    }

    public СRUDService getSrvice(ServiceTypeEnum type) throws IllegalStateException{
        switch (type){
            case COCKTAILE:
                return new CocktaileService();
            default:
                throw new IllegalStateException();
        }

    }

}
