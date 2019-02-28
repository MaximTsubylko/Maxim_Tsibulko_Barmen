package com.tsibulko.finaltask.controller.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.controller.command.Command;
import com.tsibulko.finaltask.controller.command.Router;
import com.tsibulko.finaltask.controller.command.exception.CommandRuningException;
import com.tsibulko.finaltask.dao.DaoFactory;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.exception.ServiceException;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.validation.LoginAndRegistrationValid;
import com.tsibulko.finaltask.validation.ValidatorFactory;
import com.tsibulko.finaltask.validation.ValidatorType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TryLoginCommand implements Command {
    private static DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
    private static GenericDAO dao;

    @Override
    public ResponseContent process(HttpServletRequest request) throws CommandRuningException {
        HttpSession session = request.getSession();
        LoginAndRegistrationValid validator = (LoginAndRegistrationValid) ValidatorFactory.getInstance().getValidator(ValidatorType.LOGANDREG);
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        Customer customer = new Customer();
        customer.setLogin(request.getParameter("login"));
        customer.setPassword(request.getParameter("password"));
        try {
            service.authenticate(customer, session);
        } catch (ServiceException e) {
            throw new CommandRuningException();
        }
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("barman?command=main", "redirect"));
        return responseContent;
    }
}
