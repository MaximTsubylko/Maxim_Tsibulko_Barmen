package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.MailSender;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.service.message.CustomMessage;
import com.tsibulko.finaltask.service.message.CustomMessageFactory;
import com.tsibulko.finaltask.service.message.CustomMessageType;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceDateValidationException, ServiceException, LoginAndRegistrationException {
        CustomMessage customMessage = CustomMessageFactory.getInstance().getMessage(CustomMessageType.CONFIRM);
        Customer customer = new Customer();
        MailSender sender = new MailSender();
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        customer.setLogin(request.getParameter("login"));
        customer.setPassword(request.getParameter("password"));
        customer.setEmail(request.getParameter("email"));
        service.create(customer);
        sender.send(request.getParameter("email"), customMessage);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("jsp/success_registration.jsp", "redirect"));
        return responseContent;
    }
}
