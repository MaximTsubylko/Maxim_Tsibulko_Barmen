package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.MailSender;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.message.CustomMessage;
import com.tsibulko.finaltask.service.message.CustomMessageFactory;
import com.tsibulko.finaltask.service.message.CustomMessageType;

import javax.servlet.http.HttpServletRequest;

public class RecoverySendMessageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServiceException {
        CustomMessage customMessage = CustomMessageFactory.getInstance().getMessage(CustomMessageType.RECOVERY);
        MailSender sender = new MailSender();
        sender.send(request, customMessage);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(CommandEnum.SHOW_LOGIN_PAGE.useCommand(), Router.Type.REDIRECT));
        return responseContent;

    }

}
