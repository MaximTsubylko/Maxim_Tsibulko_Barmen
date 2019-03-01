package com.tsibulko.finaltask.command;

public enum CommandEnum {
    //    ADD_TRANSPORT_TYPE("add_transport_type", RequestMethod.POST, new AccessLevel[]{AccessLevel.ADMINISTRATOR}),


    SHOW_CREATE_COCKTAIL("show_create_cocktail"),
    MAIN("main"),
    REGISTER_COCKTAIL("register_cocktail"),
    COCKTAIL_LIST("cocktail_list"),
    SHOW_COCKTAIL_DETAILS("show_cocktail_details"),
    DELETE_COCKTAIL("delete_cocktail"),
    TRY_LOGIN("try_login"),
    REGISTRATION("registration"),
    SHOW_LOGIN_PAGE("show_login_page"),
    SHOW_SUCCESS_PAGE("show_success_page"),
    SEND_RECOVERY_MESSAGE("send_recovery_message"),
    LOGOUT("logout"),
    SHOW_PROFILE("show_profile"),
    SHOW_EDIT_PAGE("show_edit_page");





    private final String name;

    public static CommandEnum getByName(String name) {
        return CommandEnum.valueOf(name.toUpperCase());
    }

    CommandEnum(String name) {
        this.name = name;
    }
}
