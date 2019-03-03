package com.tsibulko.finaltask.command;

public enum CommandEnum {


    SHOW_CREATE_COCKTAIL("show_create_cocktail", new AccessLevel[]{AccessLevel.CUSTOMER}),
    MAIN("main", new AccessLevel[]{AccessLevel.CUSTOMER}),
    REGISTER_COCKTAIL("register_cocktail", new AccessLevel[]{AccessLevel.CUSTOMER}),
    COCKTAIL_LIST("cocktail_list", new AccessLevel[]{AccessLevel.CUSTOMER}),
    SHOW_COCKTAIL_DETAILS("show_cocktail_details", new AccessLevel[]{AccessLevel.CUSTOMER}),
    DELETE_COCKTAIL("delete_cocktail", new AccessLevel[]{AccessLevel.CUSTOMER}),
    TRY_LOGIN("try_login", new AccessLevel[]{AccessLevel.VISITOR}),
    REGISTRATION("registration", new AccessLevel[]{AccessLevel.VISITOR}),
    SHOW_LOGIN_PAGE("show_login_page", new AccessLevel[]{AccessLevel.VISITOR}),
    SHOW_SUCCESS_PAGE("show_success_page", new AccessLevel[]{AccessLevel.VISITOR}),
    SEND_RECOVERY_MESSAGE("send_recovery_message", new AccessLevel[]{AccessLevel.VISITOR}),
    LOGOUT("logout", new AccessLevel[]{AccessLevel.CUSTOMER}),
    SHOW_PROFILE("show_profile", new AccessLevel[]{AccessLevel.CUSTOMER}),
    SHOW_EDIT_PAGE("show_edit_page", new AccessLevel[]{AccessLevel.CUSTOMER});


    private final String name;
    private final AccessLevel[] levels;


    CommandEnum(String name, AccessLevel[] levels) {
        this.levels = levels;
        this.name = name;
    }

    public static CommandEnum getByName(String name) {
        return CommandEnum.valueOf(name.toUpperCase());
    }

    public AccessLevel[] getLevels() {
        return levels;
    }
}
