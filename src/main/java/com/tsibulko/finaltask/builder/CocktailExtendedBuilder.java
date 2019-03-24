package com.tsibulko.finaltask.builder;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface CocktailExtendedBuilder extends Builder<Cocktail> {
    Cocktail buildForEdit(HttpServletRequest request) throws ServiceException;
}
