package com.tsibulko.finaltask.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Localfilter")
public class LocalFilter implements Filter {
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String ENGLISH_LANGUAGE = "en_EN";
    private static final String RUSSIAN_LANGUAGE = "ru_RU";
    @Override
    public void init(FilterConfig filterConfig)   {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        String locale = request.getParameter("change_lang");
        if( locale == null || locale.equals("en")){
            session.setAttribute(LOCALE_ATTRIBUTE, ENGLISH_LANGUAGE);
            Cookie cookie = new Cookie(LOCALE_ATTRIBUTE, "en");

        } else if (locale.equals("ru")){
            session.setAttribute(LOCALE_ATTRIBUTE, RUSSIAN_LANGUAGE);
            Cookie cookie = new Cookie(LOCALE_ATTRIBUTE, "ru");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}