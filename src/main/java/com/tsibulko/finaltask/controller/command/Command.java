package com.tsibulko.finaltask.controller.command;


import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@FunctionalInterface
public interface Command {
    ResponseContent process(HttpServletRequest request) throws ServletException, IOException, SQLException, PersistException, DaoException, InterruptedException;
}