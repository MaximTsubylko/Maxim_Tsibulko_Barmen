package com.tsibulko.finaltask.filter;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.UserState;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "UserStatusFilter")
public class UserStatusFilter implements Filter {
    private static final String USER_SESSION_ATTRIBUTE = "user";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute(USER_SESSION_ATTRIBUTE);
        if (customer != null) {
            CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
            try {
                Customer DbCustomer = service.getByPK(customer.getId());
                if (DbCustomer.getState() == UserState.WAITING_CONFIRMATION.getId()) {
                    request.getRequestDispatcher(Page.WAITING_CONFIRMATION.getRout()).forward(request, response);
                    httpServletRequest.getSession().setAttribute(USER_SESSION_ATTRIBUTE, null);
                } else if (DbCustomer.getState() == UserState.BLOCKED.getId()) {
                    request.getRequestDispatcher(Page.BLOCKED_ERROR_PAGE.getRout()).forward(request, response);
                    httpServletRequest.getSession().setAttribute(USER_SESSION_ATTRIBUTE, null);
                }
            } catch (ServiceException e) {
                throw new IllegalStateException("Illegal state of program! Problem in UserService", e);
            }
        }


        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
