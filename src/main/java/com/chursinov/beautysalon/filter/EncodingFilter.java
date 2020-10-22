package com.chursinov.beautysalon.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Sets request charset encoding.
 */
@WebFilter(urlPatterns = {"*"})
public class EncodingFilter implements Filter {

    private static final String DEFAULT_ENCODING = "UTF-8";
    private String encoding;

    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter("encoding");
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }


    public void destroy() {
    }
}