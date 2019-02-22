package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteCocktailCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws SQLException, PersistException, DaoException, InterruptedException, ServiceDateValidationException {
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        Integer id = Integer.parseInt(request.getParameter("cocktailId"));
        Cocktail cocktaile = service.getByPK(id);
        service.delete(cocktaile);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("barman?command=cocktail_list", "redirect"));
        return responseContent;
    }
}
