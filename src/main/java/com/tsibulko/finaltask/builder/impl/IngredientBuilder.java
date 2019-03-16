package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;

public class IngredientBuilder implements Builder<Ingredient> {
    @Override
    public Ingredient build(HttpServletRequest request) throws ServiceException {
        Ingredient ingredient = new Ingredient();

        String name = request.getParameter(AppConstant.NAME_PARAMETR);
        String description = request.getParameter(AppConstant.DESCRIPTION_PARAMETR);

        ingredient.setName(name);
        ingredient.setDescription(description);

        return ingredient;
    }
}
