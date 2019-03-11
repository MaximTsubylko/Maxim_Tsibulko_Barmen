package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Include;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dao.IngredientSpecificDAO;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.IngredientService;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCreateCocktailFormCommand implements Command {
    private static String INGREDIENT_LIST_ATRIBUTE = "ingredientList";
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        IngredientService service = (IngredientService) ServiceFactory.getInstance().getService(ServiceTypeEnum.INGREDIENT);
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute(INGREDIENT_LIST_ATRIBUTE, service.getList());
        request.setAttribute(Include.VIEW_NAME.getName(), Include.CREATE_COCKTAIL_CREATE.getName());
        return responseContent;
    }
}
