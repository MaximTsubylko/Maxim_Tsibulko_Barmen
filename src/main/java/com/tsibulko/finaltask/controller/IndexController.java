package com.tsibulko.finaltask.controller;

import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.CommandProvider;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.exception.ViewDateValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/main")
public class IndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = CommandProvider.getInstance().takeCommand(request.getParameter("command"));
        ResponseContent responseContent;
        try {
            responseContent = command.process(request);
            if (responseContent.getRouter().getType().equals("redirect")) {
                response.sendRedirect(responseContent.getRouter().getRoute());
            } else {
                request.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ViewDateValidationException e) {
            response.sendRedirect("/jsp/error.jsp");
            //тут должно выводиться сообщение
        } catch (ServiceDateValidationException e) {
            response.sendRedirect("/jsp/error.jsp");
            //тут должно выводиться сообщение
        }
    }
}
