package com.tsibulko.finaltask.command;


import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.validation.LoginAndRegistrationException;
import com.tsibulko.finaltask.validation.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.ViewDateValidationException;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    ResponseContent process(HttpServletRequest request) throws ServiceException;
}