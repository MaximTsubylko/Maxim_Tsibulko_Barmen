package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.util.ValidationUtil;
import com.tsibulko.finaltask.validation.ViewValidator;

import javax.servlet.http.HttpServletRequest;

public class ViewDateValidator implements ViewValidator {
    private static final String REGEX_PRICE = "^[0-9]{1,4}$";

    @Override
    public boolean doValidCocktailCreateRequest(HttpServletRequest request) {
        if (request.getParameter("name").length() < 20
                && request.getParameter("description").length() < 500
                && ValidationUtil.checkWithRegExp(request.getParameter("price"), REGEX_PRICE)) {
            return true;
        }
        return false;
    }

}
