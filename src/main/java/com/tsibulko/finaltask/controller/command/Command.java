package com.tsibulko.finaltask.controller.command;


import com.tsibulko.finaltask.controller.command.exception.CommandRuningException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.validation.exception.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.exception.ViewDateValidationException;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    ResponseContent process(HttpServletRequest request) throws ServiceException, CommandRuningException, ServiceDateValidationException, LoginAndRegistrationException, ViewDateValidationException;
}