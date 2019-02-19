package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktaileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class ViewCoctilListCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServletException, IOException, SQLException, PersistException, DaoException {
        CocktaileService service = (CocktaileService) ServiceFactory.getInstance().getSrvice(ServiceTypeEnum.COCKTAILE);
        request.setAttribute("cocktilList", service.getCoctilList());
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/jsp/main.jsp", "forward"));
        request.setAttribute("viewName", "cocktil_list");
        return responseContent;
    }
}
