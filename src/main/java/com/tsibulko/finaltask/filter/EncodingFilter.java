package com.tsibulko.finaltask.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName = "EncodingFilter",initParams = {
        @WebInitParam( name = "encoding", value = "UTF-8",description = "Encoding Param")
})
public class EncodingFilter implements Filter {
    private static final String ENCODING_PARAMETR = "encoding";

    private String code;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(ENCODING_PARAMETR);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(code);
        request.setCharacterEncoding(code);
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
