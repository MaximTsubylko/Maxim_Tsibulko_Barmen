package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.validation.NewValid.Validator;

import javax.servlet.http.HttpServletRequest;

public interface ViewValidator extends Validator {
    boolean doValidCocktailCreateRequest(HttpServletRequest request);

}
