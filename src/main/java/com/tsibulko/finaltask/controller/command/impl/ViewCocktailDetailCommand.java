package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ViewCocktailDetailCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException, ServiceDateValidationException {
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        if (CustomerServiceImpl.isAuthenticated(session)) {
            request.setAttribute("cocktail", service.getByPK(Integer.parseInt(request.getParameter("id"))));
            responseContent.setRouter(new Router("/jsp/barman.jsp", "forward"));
            request.setAttribute("viewName", "cocktail_detail");
        } else {
            responseContent.setRouter(new Router("/jsp/login.jsp", "forward"));
        }
        return responseContent;
    }
}
