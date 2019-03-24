package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Include;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.error.ErrorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowErrorPage implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        ResponseContent responseContent = new ResponseContent();
        String code = ErrorCode.getInstance().getErr_code();
        request.setAttribute("code", code);
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute(Include.VIEW_NAME.getName(), Include.ERROR.getName());

        return responseContent;
    }
}
