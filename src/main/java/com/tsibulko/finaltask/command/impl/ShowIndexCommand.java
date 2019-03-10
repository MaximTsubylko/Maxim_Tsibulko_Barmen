package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowIndexCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request,HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(Page.INDEX.getRout(), Router.Type.FORWARD));
        return responseContent;
    }
}
