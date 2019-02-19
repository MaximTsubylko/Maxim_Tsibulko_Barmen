package com.tsibulko.finaltask.validation.impl;

import com.tsibulko.finaltask.util.ValidationUtil;
import com.tsibulko.finaltask.validation.Validator;
import com.tsibulko.finaltask.validation.ViewValid;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewDateValidator implements ViewValid {
    private static final String REGEX_PRICE = "^[0-9]{1,4}$";

    @Override
    public boolean doValidCocktilCreateReq(HttpServletRequest request) {
        if (request.getParameter("name").length()<20
                && request.getParameter("description").length()<500
                && ValidationUtil.checkWithRegExp(request.getParameter("price"), REGEX_PRICE)){
            return true;
        }
            return false;
    }

}
