package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Include;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewCocktailDetailCommand implements Command {
    private static final String COCKTAIL_ATTRIBUTE_NAME = "cocktail";
    private static final String COCKTAIL_PARAMETR_NAME = "id";
    private static final String COCKTAIL_INGREDIENT_PARAMETR_NAME = "ingredients";


    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        ResponseContent responseContent = new ResponseContent();
        Cocktail cocktail = service.getByPK(Integer.parseInt(request.getParameter(COCKTAIL_PARAMETR_NAME)));
        List<Ingredient> ingredients = service.getIngredientByCocktail(cocktail);
        request.setAttribute(COCKTAIL_ATTRIBUTE_NAME, cocktail);
        request.setAttribute(COCKTAIL_INGREDIENT_PARAMETR_NAME,ingredients);
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute(Include.VIEW_NAME.getName(), Include.COCKTAIL_DETAILS_INCLUDE.getName());

        return responseContent;
    }
}
