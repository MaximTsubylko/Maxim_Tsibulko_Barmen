package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ViewCocktailDetailCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException {
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        request.setAttribute("cocktail", service.getByPK(Integer.parseInt(request.getParameter("id"))));
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute("viewName", "cocktail_detail");

        return responseContent;
    }
}
