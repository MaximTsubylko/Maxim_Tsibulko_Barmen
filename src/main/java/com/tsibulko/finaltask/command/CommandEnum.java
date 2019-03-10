package com.tsibulko.finaltask.command;

public enum CommandEnum {


    SHOW_CREATE_COCKTAIL("show_create_cocktail", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    MAIN("main", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    REGISTER_COCKTAIL("register_cocktail", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    COCKTAIL_LIST("cocktail_list", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    SHOW_COCKTAIL_DETAILS("show_cocktail_details", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    DELETE_COCKTAIL("delete_cocktail", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    LOGOUT("logout", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    SHOW_PROFILE("show_profile", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    SHOW_EDIT_PAGE("show_edit_page", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    SHOW_UPDATE_COCKTAIL_PAGE("show_update_cocktail_page",AccessLevel.ALL),
    EDIT_PROFILE("edit_profile", AccessLevel.CUSTOMER),
    ACTIVATE_USER("activate_user", AccessLevel.ALL),
    CHANGE_PASSWORD("change_password", AccessLevel.ALL),
    SHOW_INDEX_PAGE("show_index_page", AccessLevel.VISITOR),
    TRY_LOGIN("try_login", AccessLevel.VISITOR),
    REGISTRATION("registration", AccessLevel.VISITOR),
    SHOW_LOGIN_PAGE("show_login_page", AccessLevel.VISITOR),
    SHOW_SUCCESS_PAGE("show_success_page", AccessLevel.VISITOR),
    SHOW_ERROR_PAGE("show_error_page", AccessLevel.VISITOR),
    EDIT_COCKTAIL("edit_cocktail", AccessLevel.CUSTOMER),
    SEND_RECOVERY_MESSAGE("send_recovery_message", AccessLevel.VISITOR);


    private final String name;
    private final AccessLevel[] levels;


    CommandEnum(String name, AccessLevel... levels) {
        this.levels = levels;
        this.name = name;
    }

    public static CommandEnum getByName(String name) {
        return CommandEnum.valueOf(name.toUpperCase());
    }

    public String useCommand() {
        return "barman?command=" + name;

    }

    public AccessLevel[] getLevels() {
        return levels;
    }
}
