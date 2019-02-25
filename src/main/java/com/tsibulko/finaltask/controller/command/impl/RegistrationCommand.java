package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.exception.ViewDateValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegistrationCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServletException, IOException, SQLException, PersistException, DaoException, InterruptedException, ViewDateValidationException, ServiceDateValidationException, NoSuchAlgorithmException {
        Customer customer = new Customer();
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        customer.setLogin(request.getParameter("login"));
        customer.setPassword(request.getParameter("password"));
        customer.setEmail(request.getParameter("email"));
        service.create(customer);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/success_registration.jsp", "redirect"));
        return responseContent;
    }
}
