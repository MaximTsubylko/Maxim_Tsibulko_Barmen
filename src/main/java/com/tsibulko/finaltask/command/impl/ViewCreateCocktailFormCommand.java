package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ViewCreateCocktailFormCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        if (CustomerServiceImpl.isAuthenticated(session)) {
            responseContent.setRouter(new Router("/jsp/barman.jsp", "forward"));
            request.setAttribute("viewName", "show_create_cocktail");
        } else {
            responseContent.setRouter(new Router("/jsp/login.jsp", "forward"));
        }
        return responseContent;
    }
}