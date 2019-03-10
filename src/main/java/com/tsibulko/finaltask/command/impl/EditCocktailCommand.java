package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCocktailCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CocktailServiceImpl service = new CocktailServiceImpl();
        service.editCocktail(request);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(CommandEnum.COCKTAIL_LIST.useCommand(), Router.Type.FORWARD));
        return responseContent;
    }
}
