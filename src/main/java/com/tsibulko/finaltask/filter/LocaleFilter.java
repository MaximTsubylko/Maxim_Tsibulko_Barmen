package com.tsibulko.finaltask.filter;

import com.tsibulko.finaltask.util.CookieFinder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter implements Filter {
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String ENGLISH_LANGUAGE = "en_EN";
    private static final String RUSSIAN_LANGUAGE = "ru_RU";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        Optional<String> locale = CookieFinder.getValueByName(LOCALE_ATTRIBUTE,httpServletRequest.getCookies());

        if (locale.isPresent()){

        } else  {

        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
