package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class CreateNewCocktailCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        service.createNewCocktail(request);
        responseContent.setRouter(new Router(CommandEnum.COCKTAIL_LIST.useCommand(), Router.Type.REDIRECT));

        return responseContent;
    }
}
