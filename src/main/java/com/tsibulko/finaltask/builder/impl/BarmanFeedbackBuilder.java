package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.FieldValidator;
import com.tsibulko.finaltask.validation.ValidationException;

import javax.servlet.http.HttpServletRequest;

public class BarmanFeedbackBuilder implements Builder<BarmenFeedback> {
    private static String FROM = "from";
    private static String TO = "to";
    private static String TITLE = "title";
    private static String COMMENT = "comment";
    private static String MARK = "mark";

    @Override
    public BarmenFeedback build(HttpServletRequest request) throws ServiceException {
        BarmenFeedback feedback = new BarmenFeedback();
        FieldValidator validator = FieldValidator.getInstance();


        String title = request.getParameter(TITLE);
        Integer from = Integer.valueOf(request.getParameter(FROM));
        Integer to = Integer.valueOf(request.getParameter(TO));
        String comment = request.getParameter(COMMENT);
        String mark = request.getParameter(MARK);

        try {
            validator.simpleStingMatches(title, 20, 1, "title");
            validator.simpleStingMatches(comment, 500, 0, "comment");
        } catch (ValidationException e) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_CREATE_CUSTOMER_FEBACK);
            throw new ServiceException(e);
        }


        feedback.setFromUserId(from);
        feedback.setToUserId(to);
        feedback.setTitle(title);
        feedback.setComment(comment);
        feedback.setMark(Integer.valueOf(mark));
        return feedback;
    }
}
