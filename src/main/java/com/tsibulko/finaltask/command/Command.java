package com.tsibulko.finaltask.command;


import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    ResponseContent process(HttpServletRequest request) throws ServiceException;
}