package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        HttpSession session = request.getSession();
        String newPassword = request.getParameter(AppConstant.PASSWORD_PARAMETR);
        Customer customer = (Customer) session.getAttribute(AppConstant.SESSION_ATTRIBUTE);
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);

        service.changePassword(customer, newPassword);
        responseContent.setRouter(new Router(CommandEnum.SHOW_CUSTOMER_LIST.useCommand(), Router.Type.REDIRECT));
        return responseContent;
    }
}
