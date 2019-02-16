package com.tsibulko.finaltask.service;
//
//public class ServiceFactory {
//    private static ServiceFactory INSTANCE;
//    private ServiceFactory(){
//
//    }
//
//    public static ServiceFactory getInstance(){
//        if (INSTANCE == null){
//            INSTANCE = new ServiceFactory();
//        }
//        return INSTANCE;
//    }
//
//    public Service getSrvice(ServiceTypeEnum type) throws IllegalStateException{
//        switch (type){
//            case USER:
//               return new UserService();
//            case COCKTAILE:
//                return new CocktaileService();
//            default:
//                throw new IllegalStateException();
//        }
//
//    }
//}
