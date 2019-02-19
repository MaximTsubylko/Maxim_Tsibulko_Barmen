package com.tsibulko.finaltask.validation;

import javax.servlet.http.HttpServletRequest;

public interface ViewValid extends Validator {
    boolean doValidCocktilCreateReq(HttpServletRequest request);
}
