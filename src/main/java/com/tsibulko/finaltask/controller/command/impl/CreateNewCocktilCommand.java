package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Cocktaile;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.dao.impl.CocktaileDAO;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktaileService;
import com.tsibulko.finaltask.validation.Validator;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.exception.ViewDateValidationException;
import com.tsibulko.finaltask.validation.impl.ViewDateValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CreateNewCocktilCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws SQLException, PersistException, DaoException, ViewDateValidationException, ServiceDateValidationException {
        ViewDateValidator validator = (ViewDateValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.VIEW);
        if (validator.doValidCocktilCreateReq(request)) {
            Cocktaile cocktaile = new Cocktaile();
            CocktaileService service = (CocktaileService) ServiceFactory.getInstance().getSrvice(ServiceTypeEnum.COCKTAILE);
            cocktaile.setName(request.getParameter("name"));
            cocktaile.setDescription(request.getParameter("description"));
            cocktaile.setPrice(Integer.valueOf(request.getParameter("price")));
            service.create(cocktaile);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("demo?command=cocktil_list", "redirect"));
            return responseContent;
        } else {
            throw new ViewDateValidationException("Invalid date for create cocktil!");
        }
    }
}
