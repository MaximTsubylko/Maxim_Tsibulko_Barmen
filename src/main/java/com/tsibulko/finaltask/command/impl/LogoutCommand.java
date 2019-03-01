package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CustomerServiceImpl.logout(session);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("barman?command=show_login_page", Router.Type.REDIRECT));
        return responseContent;
    }
}
