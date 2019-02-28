package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowProfilePage implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("sessionAttribute");
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        ResponseContent responseContent = new ResponseContent();
        if (CustomerServiceImpl.isAuthenticated(session)) {
            responseContent.setRouter(new Router("/jsp/barman.jsp", "forward"));
            request.setAttribute("cocktailList", cocktailService.getCocktailByCustomer(customer));
            request.setAttribute("customer", service.getByPK(customer.getId()));
            request.setAttribute("viewName", "profile");
        } else {
            responseContent.setRouter(new Router("/jsp/login.jsp", "forward"));
        }
        return responseContent;
    }
}
