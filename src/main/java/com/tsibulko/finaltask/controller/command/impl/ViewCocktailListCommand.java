package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ViewCocktailListCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws SQLException, DaoException {
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        request.setAttribute("cocktailList", service.getList());
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/barman.jsp", "forward"));
        request.setAttribute("viewName", "cocktail_list");
        return responseContent;
    }
}
