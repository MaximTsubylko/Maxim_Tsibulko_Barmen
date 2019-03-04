package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Include;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ViewCocktailListCommand implements Command {
    private static final String COCKTAIL_LIST_ATTRIBUTE_NAME = "cocktailList";

    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        request.setAttribute(COCKTAIL_LIST_ATTRIBUTE_NAME, service.getList());
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute(Include.VIEW_NAME.getName(), Include.COCKTAIL_LIST_INCLUDE.getName());

        return responseContent;
    }
}
