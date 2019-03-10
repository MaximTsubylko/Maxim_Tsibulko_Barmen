package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.*;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TryLoginCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);

        service.logIn(request);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(CommandEnum.MAIN.useCommand(), Router.Type.FORWARD));
        return responseContent;
    }
}
