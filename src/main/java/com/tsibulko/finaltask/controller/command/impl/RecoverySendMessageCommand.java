package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.controller.command.exception.CommandRuningException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.MailSender;
import com.tsibulko.finaltask.service.message.CustomMessage;
import com.tsibulko.finaltask.service.message.CustomMessageFactory;
import com.tsibulko.finaltask.service.message.CustomMessageType;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.impl.LoginAndRegistrationValidator;

import javax.servlet.http.HttpServletRequest;

public class RecoverySendMessageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws LoginAndRegistrationException, CommandRuningException {
        CustomMessage customMessage = CustomMessageFactory.getInstance().getMessage(CustomMessageType.RECOVERY);
        MailSender sender = new MailSender();
        LoginAndRegistrationValidator validator = (LoginAndRegistrationValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.LOGANDREG);
        if (validator.isExistEmail(request.getParameter("email"))) {
            sender.send(request.getParameter("email"), customMessage);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command=show_main_page", "redirect"));
            return responseContent;
        } else {
            throw new CommandRuningException();
        }
    }

}
