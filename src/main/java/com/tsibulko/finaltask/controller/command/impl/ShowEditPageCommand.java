package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.controller.command.exception.CommandRuningException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.exception.ViewDateValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowEditPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException, CommandRuningException, ServiceDateValidationException, LoginAndRegistrationException, ViewDateValidationException {
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("sessionAttribute");
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        if (CustomerServiceImpl.isAuthenticated(session)) {
            responseContent.setRouter(new Router("/jsp/barman.jsp", "forward"));
            request.setAttribute("cocktailList", cocktailService.getCocktailByCustomer(customer));
            request.setAttribute("customer", service.getByPK(customer.getId()));
            request.setAttribute("viewName", "edit");
        } else {
            responseContent.setRouter(new Router("/jsp/login.jsp", "forward"));
        }
        return responseContent;
    }
}
