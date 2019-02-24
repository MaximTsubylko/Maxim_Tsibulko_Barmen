package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.exception.DaoException;
import com.tsibulko.finaltask.dao.exception.PersistException;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.validation.LoginAndRegistrationValid;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;
import com.tsibulko.finaltask.validation.exception.ServiceDateValidationException;
import com.tsibulko.finaltask.validation.exception.ViewDateValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TryLoginCommand implements Command {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static GenericDAO dao;
    @Override
    public ResponseContent process(HttpServletRequest request) throws ServletException, IOException, SQLException, PersistException, DaoException, InterruptedException, ViewDateValidationException, ServiceDateValidationException {
        dao = daoFactory.getDao(Customer.class);
        ResponseContent responseContent = new ResponseContent();
        LoginAndRegistrationValid validator = (LoginAndRegistrationValid) ValidatorFactory.getInstance().getValidator(ValidatorType.LOGANDREG);
        if (validator.isExistCustomer(request.getParameter("login"))){
            List<Customer> customers = dao.getAll();
            customers.stream().forEach(c -> {
                if (c.getLogin().equals(request.getParameter("login"))){
                    if (c.getPassword().equals(request.getParameter("password"))){
                        responseContent.setRouter(new Router("barman?command=main", "redirect"));
                    }
                }
            } );
        }
        return responseContent;
    }
}
