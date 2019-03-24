package com.tsibulko.finaltask.builder;

import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface IngredientExtendedBuilder extends Builder<Ingredient> {
    Ingredient buildForEdit(HttpServletRequest request) throws ServiceException;

}
