package com.tsibulko.finaltask.controller;

import com.tsibulko.finaltask.command.*;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.ViewDateValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/barman")
public class IndexController extends HttpServlet {
    private static final String COMMAND_PARAMETER = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND_PARAMETER);
        CommandEnum commandEnum = CommandEnum.getByName(commandName);
        Command command = CommandProvider.getInstance().takeCommand(commandEnum);
        ResponseContent responseContent;
        try {
            responseContent = command.process(request);
            if (responseContent.getRouter().getType() == Router.Type.REDIRECT) {
                response.sendRedirect(responseContent.getRouter().getRoute());
            } else {
                request.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ServiceDateValidationException e) {
            e.printStackTrace();
        } catch (CommandRuningException e) {
            e.printStackTrace();
        } catch (LoginAndRegistrationException e) {
            e.printStackTrace();
        } catch (ViewDateValidationException e) {
            e.printStackTrace();
        }
    }
}
