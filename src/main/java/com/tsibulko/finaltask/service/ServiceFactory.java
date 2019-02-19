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

    public CRUDService getSrvice(ServiceTypeEnum type) throws IllegalStateException{
        switch (type){
            case COCKTAILE:
                return CocktaileService.getInstance();
            default:
                throw new IllegalStateException();
        }

    }

}
