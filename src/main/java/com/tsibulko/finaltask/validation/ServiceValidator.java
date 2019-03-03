package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.validation.NewValid.Validator;

public interface ServiceValidator extends Validator {
    boolean isUniqueCocktail(Cocktail cocktail) throws LoginAndRegistrationException;

    boolean isExistCocktail(Cocktail cocktail) throws LoginAndRegistrationException;

}
