package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowWhatingConfirmatioCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(Page.WAITING_CONFIRMATION.getRout(), Router.Type.FORWARD));
        return responseContent;
    }
}
