package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Cocktail;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.builder.BuilderFactory;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateNewCocktailCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        Builder<Cocktail> builder = BuilderFactory.getInstance().getBuilder(Cocktail.class);
        CocktailServiceImpl service = (CocktailServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.COCKTAIL);
        Cocktail cocktail = builder.build(request);
//        String[] s = request.getParameter("ingredients");
        service.createNewCocktail(cocktail, (Customer) session.getAttribute(AppConstant.SESSION_ATTRIBUTE));
        responseContent.setRouter(new Router(CommandEnum.COCKTAIL_LIST.useCommand(), Router.Type.FORWARD));

        return responseContent;
    }
}
