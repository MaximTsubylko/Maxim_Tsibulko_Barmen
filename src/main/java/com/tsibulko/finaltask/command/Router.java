package com.tsibulko.finaltask.command;

import java.util.Arrays;

/**
 * Provide route to jsp page
 */
public class Router {
    public static final String INDEX_ROUT = "barman?command=main";
    private String route;
    private Type type;

    public enum Type {
        FORWARD, REDIRECT
    }

    public Router(String route, Type type) {
        this.route = route;
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public Type getType() {
        return type;
    }

}