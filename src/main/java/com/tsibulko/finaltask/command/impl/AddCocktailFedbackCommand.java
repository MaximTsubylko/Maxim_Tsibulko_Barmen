package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.CocktaileFeedback;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.builder.BuilderFactory;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailFeedbackServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCocktailFedbackCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        CocktailFeedbackServiceImpl service = (CocktailFeedbackServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL_FEEDBACK);
        Builder<CocktaileFeedback> builder = BuilderFactory.getInstance().getBuilder(CocktaileFeedback.class);
        CocktaileFeedback cocktaileFeedback = builder.build(request);
        service.create(cocktaileFeedback);
        responseContent.setRouter(new Router(CommandEnum.SHOW_PROFILE.useCommand(), Router.Type.FORWARD));

        return responseContent;
    }
}
