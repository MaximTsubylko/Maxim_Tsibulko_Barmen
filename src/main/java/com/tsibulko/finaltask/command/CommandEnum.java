package com.tsibulko.finaltask.command;

public enum CommandEnum {

    SHOW_CREATE_COCKTAIL("show_create_cocktail", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    MAIN("main", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    REGISTER_COCKTAIL("register_cocktail", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    COCKTAIL_LIST("cocktail_list", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    SHOW_COCKTAIL_DETAILS("show_cocktail_details", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    DELETE_COCKTAIL("delete_cocktail", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    LOGOUT("logout", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    SHOW_PROFILE("show_profile", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    SHOW_EDIT_PAGE("show_edit_page", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    SHOW_CUSTOMER_LIST("show_customer_list", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    CHANGE_PASSWORD("change_password", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    SHOW_UPDATE_COCKTAIL_PAGE("show_update_cocktail_page", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    EDIT_PROFILE("edit_profile", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    ADD_USER_FEEDBACK("add_user_feedback", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    ADD_COCKTAIL_FEEDBACK("add_cocktail_feedback", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    DELETE_CUSTOMER("delete_customer", AccessLevel.CUSTOMER, AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    ACTIVATE_USER("activate_user", AccessLevel.ALL),
    RESTORE_PASSWORD("restore_password", AccessLevel.ALL),
    SHOW_ERROR_PAGE("show_error_page", AccessLevel.ALL),
    SHOW_INDEX_PAGE("show_index_page", AccessLevel.VISITOR),
    TRY_LOGIN("try_login", AccessLevel.VISITOR),
    REGISTRATION("registration", AccessLevel.VISITOR),
    SHOW_LOGIN_PAGE("show_login_page", AccessLevel.VISITOR),
    SHOW_SUCCESS_PAGE("show_success_page", AccessLevel.VISITOR),
    EDIT_COCKTAIL("edit_cocktail", AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR),
    SEND_RECOVERY_MESSAGE("send_recovery_message", AccessLevel.VISITOR),
    SHOW_INGREDIENT_LIST("show_ingredient_list", AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    SHOW_CREATE_INGREDIENT("show_create_ingredient", AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    DELETE_INGREDIENT("delete_ingredient", AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    EDIT_INGREDIENT("edit_ingredient", AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    SHOW_EDIT_INGREDIENT("show_edit_ingredient", AccessLevel.BARMEN, AccessLevel.ADMINISTRATOR),
    CHANGE_STATE("change_state", AccessLevel.ADMINISTRATOR),
    CHANGE_ROLE("change_role", AccessLevel.ADMINISTRATOR),
    CREATE_INGREDIENT("create_ingredient", AccessLevel.ADMINISTRATOR, AccessLevel.BARMEN);


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


    public String getName() {
        return name;
    }

    public AccessLevel[] getLevels() {
        return levels;
    }
}
