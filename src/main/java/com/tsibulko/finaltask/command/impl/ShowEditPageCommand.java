package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.validation.ServiceDateValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowEditPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("sessionAttribute");
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute("cocktailList", cocktailService.getCocktailByCustomer(customer));
        request.setAttribute("customer", service.getByPK(customer.getId()));
        request.setAttribute("viewName", "edit");

        return responseContent;
    }
}
