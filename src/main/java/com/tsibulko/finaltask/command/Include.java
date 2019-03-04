package com.tsibulko.finaltask.command;

public enum Include {
    VIEW_NAME("viewName"),
    EDIT_INCLUDE("edit"),
    EMPTY_INCLUDE("empty"),
    PROFILE_INCLUDE("profile"),
    COCKTAIL_DETAILS_INCLUDE("cocktail_detail"),
    COCKTAIL_LIST_INCLUDE("cocktail_list"),
    CREATE_COCKTAIL_CREATE("show_create_cocktail");


    private final String name;


    Include(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}