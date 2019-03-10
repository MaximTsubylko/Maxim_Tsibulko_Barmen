package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Include;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowEditCocktailPage implements Command {
    private static final String COCKTAIL_PARAMETR_NAME = "id";
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        CocktailServiceImpl cocktailService = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        Cocktail cocktail = cocktailService.getByPK(Integer.parseInt(request.getParameter(COCKTAIL_PARAMETR_NAME)));
        request.setAttribute("cocktail",cocktail);
        request.setAttribute(Include.VIEW_NAME.getName(), Include.EDIT_COCKTAIL_PAGE.getName());

        return responseContent;
    }
}
