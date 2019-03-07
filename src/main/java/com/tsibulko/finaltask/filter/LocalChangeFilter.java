package com.tsibulko.finaltask.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LocalChangeFilter")
public class LocalChangeFilter implements Filter {
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String ENGLISH_LANGUAGE = "en_EN";
    private static final String RUSSIAN_LANGUAGE = "ru_RU";
    private static final String CHANGE_LANG_PARAMETR = "change_lang";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String locale = (String) session.getAttribute(LOCALE_ATTRIBUTE);
//        String locale = httpServletRequest.getParameter(CHANGE_LANG_PARAMETR);
        if (locale == null || locale.equals("en")) {
            session.setAttribute(LOCALE_ATTRIBUTE, ENGLISH_LANGUAGE);
            Cookie cookie = new Cookie(LOCALE_ATTRIBUTE, "en");
            httpServletRequest.removeAttribute(CHANGE_LANG_PARAMETR);
            httpServletResponse.addCookie(cookie);

        } else if (locale.equals("ru")) {
            session.setAttribute(LOCALE_ATTRIBUTE, RUSSIAN_LANGUAGE);
            Cookie cookie = new Cookie(LOCALE_ATTRIBUTE, "ru");
            httpServletRequest.removeAttribute(CHANGE_LANG_PARAMETR);
            httpServletResponse.addCookie(cookie);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}