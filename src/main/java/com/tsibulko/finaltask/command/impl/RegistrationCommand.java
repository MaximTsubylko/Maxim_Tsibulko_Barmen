package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.builder.Builder;
import com.tsibulko.finaltask.builder.BuilderFactory;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.impl.RegistrationServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.util.SendMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RegistrationServiceImpl service = new RegistrationServiceImpl();
        Builder<Customer> builder = BuilderFactory.getInstance().getBuilder(Customer.class);
        SendMessages sendMessages = new SendMessages();
        Customer customer = builder.build(request);
        Integer port = request.getLocalPort();
        String contextPath = request.getContextPath();

        service.signUp(customer);
        sendMessages.sendActivationLinkEmail(customer, port, contextPath,
                AppConstant.ACTIV_TITLE, AppConstant.ACTIV_LINK);

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(CommandEnum.SHOW_SUCCESS_PAGE.useCommand(), Router.Type.REDIRECT));
        return responseContent;
    }
}
