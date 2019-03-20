package com.tsibulko.finaltask.command;

public enum Page {
    LOG_IN("/WEB-INF/jsp/login.jsp"),
    INDEX("index.jsp"),
    MAIN_PAGE("/WEB-INF/jsp/barman.jsp"),
    EMPTY_MAIN_INCLUDE("/WEB-INF/jsp/include/empty.jsp"),
    ERROR("/WEB-INF/jsp/error.jsp"),
    ACTIVATE_USER("/WEB-INF/jsp/activate_user.jsp"),
    CHANGE_PASSWORD("/WEB-INF/jsp/change_password.jsp"),
    SUCCESS_REGISTRATION("/WEB-INF/jsp/success_registration.jsp"),
    START_PAGE("index.jsp");

    private final String rout;


    Page(String rout) {
        this.rout = rout;
    }

    public String getRout() {
        return rout;
    }
}
