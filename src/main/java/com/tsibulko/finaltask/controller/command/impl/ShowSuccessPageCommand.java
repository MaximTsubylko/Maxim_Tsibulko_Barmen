package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class ShowSuccessPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("barman?command=show_success_page", "forward"));
        return responseContent;
    }
}
