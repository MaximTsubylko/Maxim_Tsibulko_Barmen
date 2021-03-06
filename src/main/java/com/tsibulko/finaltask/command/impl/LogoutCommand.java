package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        session.setAttribute(AppConstant.SESSION_ATTRIBUTE, null);

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(CommandEnum.SHOW_LOGIN_PAGE.useCommand(), Router.Type.REDIRECT));
        return responseContent;
    }
}
