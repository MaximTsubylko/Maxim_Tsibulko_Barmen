package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateNewCocktailCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        service.createNewCocktail(request);
        responseContent.setRouter(new Router("barman?command=cocktail_list", Router.Type.REDIRECT));

        return responseContent;
    }
}
