package com.tsibulko.finaltask.controller.command;


import com.tsibulko.finaltask.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    ResponseContent process(HttpServletRequest request) throws Exception;
}