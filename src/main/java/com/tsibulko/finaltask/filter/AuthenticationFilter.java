package com.tsibulko.finaltask.filter;

import com.tsibulko.finaltask.bean.Customer;
import com.tsibulko.finaltask.bean.UserRole;
import com.tsibulko.finaltask.command.AccessLevel;
import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.command.Page;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Customer customer = (Customer) httpServletRequest.getSession().getAttribute(AppConstant.SESSION_ATTRIBUTE);
        String command = request.getParameter(AppConstant.COMMAND_PARAMETER);

        if (command == null && customer != null) {
            request.getRequestDispatcher(CommandEnum.MAIN.useCommand()).forward(request, response);
        } else if (command == null) {
            request.getRequestDispatcher(Page.LOG_IN.getRout()).forward(request, response);
        } else if (customer == null && Arrays.stream(CommandEnum.getByName(command).
                getLevels()).
                noneMatch((ob) -> ob == AccessLevel.VISITOR || ob == AccessLevel.ALL)) {
            request.getRequestDispatcher(Page.LOG_IN.getRout()).forward(request, response);
        } else if (customer != null && Arrays.stream
                (CommandEnum.getByName(command).getLevels()).
                noneMatch((ob) -> UserRole.getRoleById(customer.getRole_id()).toString().equals(ob.toString())
                        || ob == AccessLevel.ALL)) {
            request.getRequestDispatcher(CommandEnum.MAIN.useCommand()).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
