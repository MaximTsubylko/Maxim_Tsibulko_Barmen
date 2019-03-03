package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowLoginPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(Page.LOG_IN.getRout(), Router.Type.FORWARD));
        request.setAttribute("viewName", "empty"); //ПЕРЕДЕЛАТЬ КАК ТОЛЬКО ТАК СРАЗУ!!
        return responseContent;
    }
}
