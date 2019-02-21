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
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getSrvice(ServiceTypeEnum.COCKTAILE);
        request.setAttribute("cocktilList", service.getList());
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/main.jsp", "forward"));
        request.setAttribute("viewName", "cocktil_list");
        return responseContent;
    }
}
