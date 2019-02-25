package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.controller.command.exception.CommandRuningException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.MailSender;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import com.tsibulko.finaltask.validation.impl.LoginAndRegistrationValidator;

import javax.servlet.http.HttpServletRequest;

public class RecoverySendMessageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws Exception {
        MailSender sender = new MailSender();
        String s = request.getParameter("email");
        LoginAndRegistrationValidator validator = (LoginAndRegistrationValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.LOGANDREG);
        if (validator.isExistEmail(request.getParameter("email"))) {
            sender.send("barmensupp@gmail.com", "asdfG3421", request.getParameter("email"), "Test", "test recovery message");
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command=show_main_page", "redirect"));
            return responseContent;
        } else {
            throw new CommandRuningException();
        }
    }

}
