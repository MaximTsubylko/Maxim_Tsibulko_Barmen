package com.tsibulko.finaltask.controller;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.CommandProvider;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.util.AppConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/barman")
public class IndexController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(IndexController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(AppConstant.COMMAND_PARAMETER);
        CommandEnum commandEnum = CommandEnum.getByName(commandName);
        Command command = CommandProvider.getInstance().takeCommand(commandEnum);
        LOGGER.info("Command :" + commandName + " work in controller");
        ResponseContent responseContent;
        try {
            responseContent = command.process(request, response);
            if (responseContent.getRouter().getType() == Router.Type.REDIRECT) {
                response.sendRedirect(responseContent.getRouter().getRoute());
            } else {
                request.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
            }
        } catch (ServiceException e) {
            LOGGER.error(e);
            request.setAttribute("code", ErrorCode.getInstance().getErr_code());
            request.getRequestDispatcher(CommandEnum.SHOW_ERROR_PAGE.useCommand()).forward(request, response);
        }
    }

}
