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
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.exception.ViewDateValidationException;
import com.tsibulko.finaltask.validation.impl.ViewDateValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class CreateNewCocktailCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws SQLException, PersistException, DaoException, ViewDateValidationException, ServiceDateValidationException {
        ViewDateValidator validator = (ViewDateValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.VIEW);
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        if (CustomerServiceImpl.isAuthenticated(session)) {
            if (validator.doValidCocktailCreateRequest(request)) {
                Cocktail cocktail = new Cocktail();
                CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
                cocktail.setName(request.getParameter("name"));
                cocktail.setDescription(request.getParameter("description"));
                cocktail.setPrice(Integer.valueOf(request.getParameter("price")));
                service.create(cocktail);
                responseContent.setRouter(new Router("barman?command=cocktail_list", "redirect"));
            } else {
                throw new ViewDateValidationException("Invalid date for create cocktail!");
            }
        } else {
            responseContent.setRouter(new Router("/jsp/login.jsp", "forward"));
        }
        return responseContent;
    }
}
