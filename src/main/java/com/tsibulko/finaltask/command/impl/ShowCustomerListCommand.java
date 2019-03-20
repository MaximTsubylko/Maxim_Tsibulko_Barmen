package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Include;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerFeedbackServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCustomerListCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
        CustomerFeedbackServiceImpl feedbackService = (CustomerFeedbackServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER_FEEDBACK);
        List<Customer> customers = service.getList();
        feedbackService.setAverageMarkToCustomer(customers);

        request.setAttribute(AppConstant.CUSTOMER_LIST_PARAMETR, customers);
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute(Include.VIEW_NAME.getName(), Include.CUSTOMER_LIST_INCLUDE.getName());

        return responseContent;
    }
}
