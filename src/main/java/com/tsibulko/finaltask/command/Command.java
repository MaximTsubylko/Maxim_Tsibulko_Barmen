package com.tsibulko.finaltask.command;


import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {
    ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}