package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.builder.BuilderFactory;
import com.tsibulko.finaltask.builder.CustomerExtendedBuilder;
import com.tsibulko.finaltask.builder.impl.IngredientBuilder;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.service.impl.IngredientServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditIngredientCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        IngredientServiceImpl service = (IngredientServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.INGREDIENT);
        IngredientBuilder builder = (IngredientBuilder) BuilderFactory.getInstance().getBuilder(Ingredient.class);
        Ingredient newIngredient = builder.buildForEdit(request);

        service.update(newIngredient);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(CommandEnum.SHOW_INGREDIENT_LIST.useCommand(), Router.Type.REDIRECT));
        return responseContent;
    }
}
