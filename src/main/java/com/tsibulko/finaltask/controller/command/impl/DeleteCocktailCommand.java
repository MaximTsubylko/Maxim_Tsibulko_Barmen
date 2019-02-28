package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteCocktailCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        if (CustomerServiceImpl.isAuthenticated(session)) {

            Integer id = Integer.parseInt(request.getParameter("cocktailId"));
            Cocktail cocktaile = service.getByPK(id);
            service.delete(cocktaile);
            responseContent.setRouter(new Router("barman?command=cocktail_list", "redirect"));
        } else {
            responseContent.setRouter(new Router("/jsp/login.jsp", "forward"));
        }
        return responseContent;
    }
}
