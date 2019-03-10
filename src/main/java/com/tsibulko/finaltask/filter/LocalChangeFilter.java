package com.tsibulko.finaltask.filter;

import com.tsibulko.finaltask.util.CookieFinder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

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
        Optional<String> locale = CookieFinder.getValueByName(LOCALE_ATTRIBUTE, httpServletRequest.getCookies());
        Optional<String> change = Optional.ofNullable(httpServletRequest.getParameter(CHANGE_LANG_PARAMETR));

        if (change.isPresent()) {
            if (change.get().equals("en")) {
                Cookie cookie = new Cookie(LOCALE_ATTRIBUTE, "en");
                httpServletRequest.setAttribute("lang","en");
                httpServletResponse.addCookie(cookie);
                httpServletResponse.setLocale(new Locale("en"));
            } else if (change.get().equals("ru")) {
                Cookie cookie = new Cookie(LOCALE_ATTRIBUTE, "ru");
                httpServletResponse.addCookie(cookie);
                httpServletRequest.setAttribute("lang","ru");
                httpServletResponse.setLocale(new Locale("ru"));
            }

        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

}