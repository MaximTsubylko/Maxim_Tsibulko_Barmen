package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteCocktailCommand implements Command {
    private static final String PARAMETR_NAME = "cocktailId";
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException {
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        ResponseContent responseContent = new ResponseContent();
        Integer id = Integer.parseInt(request.getParameter(PARAMETR_NAME));
        Cocktail cocktaile = service.getByPK(id);
        service.delete(cocktaile);
        responseContent.setRouter(new Router(CommandEnum.COCKTAIL_LIST.useCommand(), Router.Type.REDIRECT));
        return responseContent;
    }
}
