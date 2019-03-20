package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;

public class CocktailValidator implements Validator<Cocktail> {
    @Override
    public void doValidation(Cocktail entity) {
        FieldValidator validator = FieldValidator.getInstance();
        try {
            validator.simpleStingMatches(entity.getName(), 20, "name");
            validator.simpleStingMatches(entity.getDescription(), 500, "description");
            validator.isMatchesInt(String.valueOf(entity.getPrice()), new int[]{1, 1000});

        } catch (ValidationException e) {
            throw new SecurityException(e);
        }
    }
}
