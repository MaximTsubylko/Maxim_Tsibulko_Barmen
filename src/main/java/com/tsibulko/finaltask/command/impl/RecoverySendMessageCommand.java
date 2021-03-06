package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.util.AppConstant;
import com.tsibulko.finaltask.util.SendMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecoverySendMessageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        SendMessages sendMessages = new SendMessages();
        Customer customer = new Customer();
        customer.setEmail(request.getParameter(AppConstant.EMAIL_PARAMETR));
        Integer port = request.getLocalPort();
        String contextPath = request.getContextPath();

        sendMessages.sendRestoreEmail(customer, port, contextPath);

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(CommandEnum.SHOW_LOGIN_PAGE.useCommand(), Router.Type.FORWARD));
        return responseContent;

    }

}
