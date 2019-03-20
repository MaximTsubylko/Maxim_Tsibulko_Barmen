package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Ingredient;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.IngredientServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteIngreditntCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        IngredientServiceImpl service = (IngredientServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.INGREDIENT);
        ResponseContent responseContent = new ResponseContent();
        Integer id = Integer.parseInt(request.getParameter(AppConstant.ID_PARAMETR));
        Ingredient ingredient = service.getByPK(id);

        service.delete(ingredient);
        responseContent.setRouter(new Router(CommandEnum.SHOW_INGREDIENT_LIST.useCommand(), Router.Type.FORWARD));
        return responseContent;
    }
}

