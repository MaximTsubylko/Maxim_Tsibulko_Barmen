package com.tsibulko.finaltask.filter;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.UserState;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.service.ServiceException;
import com.tsibulko.finaltask.service.ServiceFactory;
import com.tsibulko.finaltask.service.ServiceTypeEnum;
import com.tsibulko.finaltask.service.impl.CustomerServiceImpl;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserStatusFilter")
public class UserStatusFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        String command = request.getParameter(AppConstant.COMMAND_PARAMETER);
        Customer customer = (Customer) session.getAttribute(AppConstant.SESSION_ATTRIBUTE);

        if (customer != null) {
            CustomerServiceImpl service = (CustomerServiceImpl) ServiceFactory.getInstance().getService(ServiceTypeEnum.CUSTOMER);
            try {
                if (command.equals(CommandEnum.LOGOUT.getName())) {
                    chain.doFilter(request, response);
                } else {
                    Customer dbCustomer = service.getByPK(customer.getId());
                    if (dbCustomer.getState() == UserState.WAITING_CONFIRMATION.getId()) {
                        ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_WAITING_CONFORMATION);
                        request.getRequestDispatcher(CommandEnum.SHOW_ERROR_PAGE.useCommand()).forward(request, response);

                    } else if (dbCustomer.getState() == UserState.BLOCKED.getId()) {

                        ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_CODE_BLOCED);
                        request.getRequestDispatcher(CommandEnum.SHOW_ERROR_PAGE.useCommand()).forward(request, response);
                    } else {
                        chain.doFilter(request, response);
                    }
                }
            } catch (ServiceException e) {
                throw new IllegalStateException("Illegal state of program! Problem in UserService", e);
            }
        } else {
            chain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {

    }
}
