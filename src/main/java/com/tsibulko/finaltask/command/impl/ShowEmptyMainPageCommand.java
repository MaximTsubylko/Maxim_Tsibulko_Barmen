package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class ShowEmptyMainPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute("viewName", "empty");
        return responseContent;

    }
}
