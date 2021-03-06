package com.tsibulko.finaltask.error;

public class ErrorConstant {

    public final static String ERR_CODE_DAO_ERROR = "113";

    //cocktail feedback code
    public final static String ERR_CODE_GET_COCKTAIL_FEEDBACK = "115";
    public final static String ERR_CODE_CREATE_COCKTAIL_FEBACK = "116";

    //customer feedback code
    public final static String ERR_CODE_GET_CUSTOMER_FEEDBACK = "117";
    public final static String ERR_CODE_CREATE_CUSTOMER_FEBACK = "118";

    //customer code
    public final static String ERR_CODE_CHANGE_STATE = "119";
    public final static String ERR_CODE_CHANGE_PASSWORD = "120";
    public final static String ERR_CODE_NOT_UNIQUE_CUSTOMER = "121";
    public final static String ERR_CODE_NOT_EXIST_CUSTOMER = "122";
    public final static String ERR_CODE_CREATE_CUSTOMER = "132";
    public final static String ERR_CODE_DELETE_CUSTOMER = "133";
    public final static String ERR_CODE_INCORRECT_LOGIN = "135";
    public final static String ERR_CODE_NOT_UNIQUE_EMAIL = "138";
    public final static String ERR_CODE_INCORRECT_EMAIL = "139";
    public final static String ERR_CODE_EDIT_CUSTOMER = "140";
    public final static String ERR_CODE_NOT_UNIQUE_LOGIN = "141";


    //cocktail code
    public final static String ERR_CODE_CREATE_COCKTAIL_WHITH_INGREDIENTS = "123";
    public final static String ERR_CODE_GET_COCKTAIL_BY_CUSTOMER = "130";
    public final static String ERR_CODE_GET_INGREDIENT_BY_COCKTAIL = "131";
    public final static String ERR_CODE_NOT_EXIST_COCKTAIL = "124";
    public final static String ERR_CODE_NOT_UNIQUE_COCKTAIL = "136";
    public final static String ERR_CODE_LARGE_PRICE = "142";
    public final static String ERR_CODE_LARGE_NAME = "143";
    public final static String ERR_CODE_LARGE_DESCRIPTION = "144";
    public final static String ERR_CODE_INCORRECT_NAME = "145";


    //ingredient code
    public final static String ERR_CODE_NOT_EXIST_INGREDIENT = "125";
    public final static String ERR_CODE_CREATE_INGREDIENT = "134";
    public final static String ERR_CODE_NOT_UNIQUE_INGREDIENT = "137";


    //login service code
    public final static String ERR_CODE_INCORRECT_PASSWORD = "126";
    public final static String ERR_CODE_NOT_EXIST_EMAIL = "127";


    //customer state code
    public final static String ERR_CODE_WAITING_CONFORMATION = "128";
    public final static String ERR_CODE_BLOCED = "129";

    //page code
    public final static String ERR_PAGE_NOT_FOUDN = "404";


}
