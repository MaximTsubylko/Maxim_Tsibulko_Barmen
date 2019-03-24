package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Include;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.IngredientServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewCreateCocktailFormCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        IngredientServiceImpl service = (IngredientServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.INGREDIENT);
        List<Ingredient> ingredientList = service.getList();

        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute(AppConstant.INGREDIENT_LIST_PARAMETR, ingredientList);
        request.setAttribute(Include.VIEW_NAME.getName(), Include.CREATE_COCKTAIL_CREATE.getName());
        return responseContent;
    }
}
