package com.tsibulko.finaltask.command.impl;

import com.tsibulko.finaltask.bean.BarmenFeedback;
import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.command.Command;
import com.tsibulko.finaltask.command.Include;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.command.Router;
import com.tsibulko.finaltask.dto.ResponseContent;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CocktailFeedbackServiceImpl;
import com.tsibulko.finaltask.service.impl.CocktailServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerFeedbackServiceImpl;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowProfilePageCommand implements Command {


    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        CustomerFeedbackServiceImpl feedbackService = (CustomerFeedbackServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER_FEEDBACK);
        CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);

        Customer customer;
        if (request.getParameter(AppConstant.ID_PARAMETR) != null) {
            Integer id = Integer.valueOf(request.getParameter(AppConstant.ID_PARAMETR));
            customer = service.getCustomerWithCocktails(id
                    , new Customer());
        } else {
            customer = (Customer) session.getAttribute(AppConstant.SESSION_ATTRIBUTE);
        }
        List<BarmenFeedback> feedbacks = feedbackService.getCustomerFeedbacksByCustomer(customer);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(Page.MAIN_PAGE.getRout(), Router.Type.FORWARD));
        request.setAttribute(AppConstant.FEEDBACK_PARAMETR, feedbacks);
        request.setAttribute(AppConstant.CUSTOMER_PARAMETR, customer);
        request.setAttribute(Include.VIEW_NAME.getName(), Include.PROFILE_INCLUDE.getName());
        return responseContent;
    }
}
