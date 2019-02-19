package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class TestCommand implements Command {
    @Override
    public ResponseContent process (HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/hello.jsp", "forward"));
        request.setAttribute("viewName", "hello");
        return responseContent;
    }
}
