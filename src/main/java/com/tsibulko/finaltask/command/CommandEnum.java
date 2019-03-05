package com.tsibulko.finaltask.command;

public enum CommandEnum {


    SHOW_CREATE_COCKTAIL("show_create_cocktail", new AccessLevel[]{AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR}),
    MAIN("main", new AccessLevel[]{AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR}),
    REGISTER_COCKTAIL("register_cocktail", new AccessLevel[]{AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR}),
    COCKTAIL_LIST("cocktail_list", new AccessLevel[]{AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR}),
    SHOW_COCKTAIL_DETAILS("show_cocktail_details", new AccessLevel[]{AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR}),
    DELETE_COCKTAIL("delete_cocktail", new AccessLevel[]{AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR}),
    LOGOUT("logout", new AccessLevel[]{AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR}),
    SHOW_PROFILE("show_profile", new AccessLevel[]{AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR}),
    SHOW_EDIT_PAGE("show_edit_page", new AccessLevel[]{AccessLevel.CUSTOMER, AccessLevel.BARMEN,
            AccessLevel.ADMINISTRATOR}),
    EDIT_PROFILE("edit_profile", new AccessLevel[]{AccessLevel.CUSTOMER}),
    ACTIVATE_USER("activate_user", new AccessLevel[]{AccessLevel.ALL}),
    CHANGE_PASSWORD("change_password", new AccessLevel[]{AccessLevel.ALL}),
    SHOW_INDEX_PAGE("show_index_page", new AccessLevel[]{AccessLevel.ALL}),
    TRY_LOGIN("try_login", new AccessLevel[]{AccessLevel.VISITOR}),
    REGISTRATION("registration", new AccessLevel[]{AccessLevel.VISITOR}),
    SHOW_LOGIN_PAGE("show_login_page", new AccessLevel[]{AccessLevel.VISITOR}),
    SHOW_SUCCESS_PAGE("show_success_page", new AccessLevel[]{AccessLevel.VISITOR}),
    SHOW_ERROR_PAGE("show_error_page", new AccessLevel[]{AccessLevel.VISITOR}),
    SEND_RECOVERY_MESSAGE("send_recovery_message", new AccessLevel[]{AccessLevel.VISITOR});


    private final String name;
    private final AccessLevel[] levels;


    CommandEnum(String name, AccessLevel[] levels) {
        this.levels = levels;
        this.name = name;
    }

    public String useCommand() {
        return "barman?command=" + name;

    }

    public static CommandEnum getByName(String name) {
        return CommandEnum.valueOf(name.toUpperCase());
    }

    public AccessLevel[] getLevels() {
        return levels;
    }
}
