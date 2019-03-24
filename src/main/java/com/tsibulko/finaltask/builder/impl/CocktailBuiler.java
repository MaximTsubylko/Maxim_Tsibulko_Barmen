package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.builder.CocktailExtendedBuilder;
import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.IngredientServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CocktailBuiler implements CocktailExtendedBuilder {
    IngredientServiceImpl ingredientService = (IngredientServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.INGREDIENT);

    @Override
    public Cocktail build(HttpServletRequest request) throws ServiceException {
        Cocktail cocktail = new Cocktail();
        FieldValidator validator = FieldValidator.getInstance();

        if (request.getParameter(AppConstant.ID_PARAMETR) != null) {
            Integer id = Integer.valueOf(request.getParameter(AppConstant.ID_PARAMETR));
            cocktail.setId(id);
        }
        String name = request.getParameter(AppConstant.NAME_PARAMETR);
        String description = request.getParameter(AppConstant.DESCRIPTION_PARAMETR);
        String price = request.getParameter(AppConstant.PRICE_PARAMETR);
        String[] ingredients = request.getParameterValues(AppConstant.INGREDIENTS_PARAMETR);
        if (ingredients != null) {
            List<Ingredient> ingredientList = new ArrayList<>();

            for (int i = 0; i < ingredients.length; i++) {
                ingredientList.add(ingredientService.getByName(ingredients[i]));
            }
            cocktail.setIngredients(ingredientList);
        }

        try {//FIXME
            validator.simpleStingMatches(name, 20,3,"name");
            validator.simpleStingMatches(description, 500, 0, "description");
            validator.isMatchesInt(price, new int[]{1, 1000});
            validator.isMatcesByPattern("[а-яА-Яa-zA-Z0-9]+$|[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+$|[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+$",name);
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_COCKTAIL_WHITH_INGREDIENTS);
            throw new ServiceException(e);
        }

        try {
            validator.isUnique(new String[]{"name"},Cocktail.class,name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_NOT_UNIQUE_COCKTAIL);
            throw new ServiceException(e);
        }

        cocktail.setName(name);
        cocktail.setDescription(description);
        cocktail.setPrice(Integer.valueOf(price));
        return cocktail;
    }

    @Override
    public Cocktail buildForEdit(HttpServletRequest request) throws ServiceException {
        Cocktail newCocktail = new Cocktail();
        FieldValidator validator = FieldValidator.getInstance();


        if (request.getParameter(AppConstant.ID_PARAMETR) != null) {
            Integer id = Integer.valueOf(request.getParameter(AppConstant.ID_PARAMETR));
            newCocktail.setId(id);
        }

        String name = request.getParameter(AppConstant.NAME_PARAMETR);
        String description = request.getParameter(AppConstant.DESCRIPTION_PARAMETR);
        String price = request.getParameter(AppConstant.PRICE_PARAMETR);
        try {
            validator.simpleStingMatches(name, 20,3,"name");
            validator.simpleStingMatches(description, 500, 0, "description");
            validator.isMatchesInt(price, new int[]{1, 1000});
            validator.isMatcesByPattern("[а-яА-Яa-zA-Z0-9]+$|" +
                    "[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+$|" +
                    "[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+\\s+[а-яА-Яa-zA-Z0-9]+$",name);

        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_COCKTAIL_WHITH_INGREDIENTS);
            throw new ServiceException(e);
        }
        newCocktail.setName(name);
        newCocktail.setPrice(Integer.valueOf(price));
        newCocktail.setDescription(description);
        return newCocktail;
    }
}
