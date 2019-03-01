package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request){
        HttpSession session = request.getSession();
        CustomerServiceImpl.logout(session);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("barman?command=show_main_page", "redirect"));
        return responseContent;
    }
}
