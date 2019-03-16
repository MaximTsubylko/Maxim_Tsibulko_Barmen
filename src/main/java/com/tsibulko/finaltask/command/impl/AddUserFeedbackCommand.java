package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
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
import com.tsibulko.finaltask.service.impl.CustomerFeedbackServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddUserFeedbackCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        CustomerFeedbackServiceImpl service = (CustomerFeedbackServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER_FEEDBACK);
        Builder<BarmenFeedback> builder = BuilderFactory.getInstance().getBuilder(BarmenFeedback.class);
        BarmenFeedback barmenFeedback = builder.build(request);

        service.create(barmenFeedback);
        responseContent.setRouter(new Router(CommandEnum.SHOW_PROFILE.useCommand(), Router.Type.REDIRECT));

        return responseContent;
    }
}
