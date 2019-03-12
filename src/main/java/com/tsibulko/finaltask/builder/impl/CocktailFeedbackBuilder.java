package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class CocktailFeedbackBuilder implements Builder<CocktaileFeedback> {
    private static String FROM = "from";
    private static String TO = "to";
    private static String TITLE = "title";
    private static String COMMENT = "comment";
    private static String MARK = "mark";
    @Override
    public CocktaileFeedback build(HttpServletRequest request) throws ServiceException {
        CocktaileFeedback feedback = new CocktaileFeedback();
        feedback.setFromUserId(Integer.valueOf(request.getParameter(FROM)));
        feedback.setToCocktileId(Integer.valueOf(request.getParameter(TO)));
        feedback.setTitle(request.getParameter(TITLE));
        feedback.setComment(request.getParameter(COMMENT));
        feedback.setMark(Integer.valueOf(request.getParameter(MARK)));
        return feedback;
    }}
