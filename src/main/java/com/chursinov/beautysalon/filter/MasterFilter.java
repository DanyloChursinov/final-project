package com.chursinov.beautysalon.filter;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.entity.user.Role;
import com.chursinov.beautysalon.entity.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/master-profile", "/done-appointment"})
public class MasterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        User user = (User) servletRequest.getSession().getAttribute("user");
        if (user != null && user.getRole().equalsTo(Role.MASTER)) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute("error", Constants.Errors.ACCESS_DENIED);
            servletRequest.getServletContext().getRequestDispatcher(Constants.Pages.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
