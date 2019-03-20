package com.tsibulko.finaltask.builder.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.service.ServiceException;

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


        String title = request.getParameter(TITLE);
        Integer from = Integer.valueOf(request.getParameter(FROM));
        Integer to = Integer.valueOf(request.getParameter(TO));
        String comment = request.getParameter(COMMENT);
        Integer mark = Integer.valueOf(request.getParameter(MARK));


        feedback.setFromUserId(from);
        feedback.setToUserId(to);
        feedback.setTitle(title);
        feedback.setComment(comment);
        feedback.setMark(mark);
        return feedback;
    }
}
