package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class UserActivationCommand implements Command {
    private static final String ACTIVE_STATE = "active";
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException {
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        service.setNewState(request, ACTIVE_STATE);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(Page.ACTIVATE_USER.getRout(), Router.Type.FORWARD));
        return responseContent;
    }
}
