package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.IngredientServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.validation.ValidationException;
import com.tsibulko.finaltask.validation.Validator;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CocktailBuiler implements Builder<Cocktail> {
    Validator<Cocktail> validator = ValidatorFactory.getInstance().getValidator(ValidatorType.COCKTAIL);
    IngredientServiceImpl ingredientService = (IngredientServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.INGREDIENT);

    @Override
    public Cocktail build(HttpServletRequest request) throws ServiceException {
        Cocktail cocktail = new Cocktail();

        try {
            if (request.getParameter(AppConstant.ID_PARAMETR) != null) {
                Integer id = Integer.valueOf(request.getParameter(AppConstant.ID_PARAMETR));
                cocktail.setId(id);
            }
            String name = request.getParameter(AppConstant.NAME_PARAMETR);
            String description = request.getParameter(AppConstant.DESCRIPTION_PARAMETR);
            Integer price = Integer.valueOf(request.getParameter(AppConstant.PRICE_PARAMETR));
            String[] ingredients = request.getParameterValues(AppConstant.INGREDIENTS_PARAMETR);
            if (ingredients != null) {
                List<Ingredient> ingredientList = new ArrayList<>();

                for (int i = 0; i < ingredients.length; i++) {
                    ingredientList.add(ingredientService.getByName(ingredients[i]));
                }
                cocktail.setIngredients(ingredientList);
            }


            cocktail.setName(name);
            cocktail.setDescription(description);
            cocktail.setPrice(price);
            validator.doValidation(cocktail);
        } catch (ValidationException e) {
            throw new ServiceException("some error");
        }
        return cocktail;
    }
}
