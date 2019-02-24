package com.tsibulko.finaltask.validation;

import javax.servlet.http.HttpServletRequest;

public interface ViewValidator extends Validator {
    boolean doValidCocktailCreateRequest(HttpServletRequest request);

}
