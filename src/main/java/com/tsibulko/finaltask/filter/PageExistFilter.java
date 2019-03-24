package com.tsibulko.finaltask.filter;

import com.tsibulko.finaltask.command.CommandEnum;
import com.tsibulko.finaltask.error.ErrorCode;
import com.tsibulko.finaltask.error.ErrorConstant;
import com.tsibulko.finaltask.util.AppConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(filterName = "PageExistFilter")

public class PageExistFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String command = request.getParameter(AppConstant.COMMAND_PARAMETER);
        if (Arrays.stream(CommandEnum.values())
                .noneMatch((ob) -> ob.getName().equals(command))) {
            ErrorCode.getInstance().setErr_code(ErrorConstant.ERR_PAGE_NOT_FOUDN);
            request.getRequestDispatcher(CommandEnum.SHOW_ERROR_PAGE.useCommand()).forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
