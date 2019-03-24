package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.builder.IngredientExtendedBuilder;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;

public class IngredientBuilder implements IngredientExtendedBuilder {
    @Override
    public Ingredient build(HttpServletRequest request) throws ServiceException {
        Ingredient ingredient = new Ingredient();
        FieldValidator validator = FieldValidator.getInstance();


        if (request.getParameter(AppConstant.ID_PARAMETR) != null) {
            Integer id = Integer.valueOf(request.getParameter(AppConstant.ID_PARAMETR));
            ingredient.setId(id);
        }
        String name = request.getParameter(AppConstant.NAME_PARAMETR);
        String description = request.getParameter(AppConstant.DESCRIPTION_PARAMETR);


        try {
            validator.simpleStingMatches(name, 30, 3, "name");
            validator.simpleStingMatches(description, 500, 0, "description");
            validator.isMatcesByPattern("[а-яА-Яa-zA-Z0-9]+$|[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+$|[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+$", name);

        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_INGREDIENT);
            throw new ServiceException(e);
        }

        try {
            validator.isUnique(new String[]{"name"}, Ingredient.class, name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_UNIQUE_INGREDIENT);
            throw new ServiceException(e);
        }


        ingredient.setName(name);
        ingredient.setDescription(description);

        return ingredient;
    }

    @Override
    public Ingredient buildForEdit(HttpServletRequest request) throws ServiceException {
        Ingredient ingredient = new Ingredient();
        FieldValidator validator = FieldValidator.getInstance();


        if (request.getParameter(AppConstant.ID_PARAMETR) != null) {
            Integer id = Integer.valueOf(request.getParameter(AppConstant.ID_PARAMETR));
            ingredient.setId(id);
        }
        String name = request.getParameter(AppConstant.NAME_PARAMETR);
        String description = request.getParameter(AppConstant.DESCRIPTION_PARAMETR);


        try {
            validator.simpleStingMatches(name, 30, 3, "name");
            validator.simpleStingMatches(description, 500, 0, "description");
            validator.isMatcesByPattern("[а-яА-Яa-zA-Z0-9]+$|[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+$|[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+$", name);

        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_INGREDIENT);
            throw new ServiceException(e);
        }


        ingredient.setName(name);
        ingredient.setDescription(description);

        return ingredient;
    }
}
