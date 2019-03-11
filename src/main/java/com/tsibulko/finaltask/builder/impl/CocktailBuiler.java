package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.dao.CocktailSpecificDAO;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.ValidationException;
import com.tsibulko.finaltask.validation.Validator;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;

import javax.servlet.http.HttpServletRequest;

public class CocktailBuiler implements Builder<Cocktail> {
    Validator<Cocktail> validator = ValidatorFactory.getInstance().getValidator(ValidatorType.COCKTAIL);

    @Override
    public Cocktail build(HttpServletRequest request) throws ServiceException {
        Cocktail cocktail = new Cocktail();

        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Integer price = Integer.valueOf(request.getParameter("price"));

            cocktail.setName(name);
            cocktail.setDescription(description);
            cocktail.setPrice(price);
            validator.doValidation(cocktail);
        } catch (ValidationException e){
            throw new ServiceException("some error");
        }
        return cocktail;
    }
}
