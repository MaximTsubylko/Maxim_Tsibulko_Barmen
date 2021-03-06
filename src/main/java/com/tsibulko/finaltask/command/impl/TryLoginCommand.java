package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.builder.BuilderFactory;
import com.tsibulko.finaltask.builder.CustomerExtendedBuilder;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.impl.LoginServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TryLoginCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        LoginServiceImpl service = new LoginServiceImpl();
        CustomerExtendedBuilder builder = (CustomerExtendedBuilder) BuilderFactory.getInstance().getBuilder(Customer.class);
        Customer customer = builder.buildForEnter(request);
        customer = service.logIn(customer);
        session.setAttribute(AppConstant.SESSION_ATTRIBUTE, customer);

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(CommandEnum.MAIN.useCommand(), Router.Type.FORWARD));
        return responseContent;
    }
}
