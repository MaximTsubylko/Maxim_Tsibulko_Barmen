package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

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
        FieldValidator validator = FieldValidator.getInstance();


        String title = request.getParameter(TITLE);
        Integer from = Integer.valueOf(request.getParameter(FROM));
        Integer to = Integer.valueOf(request.getParameter(TO));
        String comment = request.getParameter(COMMENT);
        Integer mark = Integer.valueOf(request.getParameter(MARK));

        try {
            validator.simpleStingMatches(title, 20, 1, "title");
            validator.simpleStingMatches(comment, 500, 0, "comment");
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_COCKTAIL_FEBACK);
            throw new ServiceException(e);
        }

        feedback.setFromUserId(from);
        feedback.setToCocktileId(to);
        feedback.setTitle(title);
        feedback.setComment(comment);
        feedback.setMark(mark);
        return feedback;
    }
}
