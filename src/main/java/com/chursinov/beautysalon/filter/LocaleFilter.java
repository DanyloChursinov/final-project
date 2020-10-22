package com.chursinov.beautysalon.filter;

import com.chursinov.beautysalon.constants.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Defines and sets current locale
 */
@WebFilter(urlPatterns = {"*"})
public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String currentLocale = (String) servletRequest.getSession().getAttribute(Constants.Attributes.CURRENT_LOCALE);
        if (currentLocale == null) {
            currentLocale = "en";
            servletRequest.getSession().setAttribute(Constants.Attributes.CURRENT_LOCALE, currentLocale);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

