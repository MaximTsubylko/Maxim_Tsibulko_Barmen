package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.builder.Builder;

import javax.servlet.http.HttpServletRequest;

public class CocktailBuiler implements Builder<Cocktail> {
    @Override
    public Cocktail build(HttpServletRequest request) {
        return null;
    }
}
