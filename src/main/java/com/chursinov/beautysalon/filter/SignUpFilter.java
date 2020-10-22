package com.chursinov.beautysalon.filter;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.util.UserInputValidator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/signup")
public class SignUpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String email = request.getParameter("email");
        String username =  request.getParameter("username");
        String password = request.getParameter("password");
        if (!UserInputValidator.validateEmail(email)) {
            System.out.println("Email Error");
            request.setAttribute(Constants.Errors.ERROR, Constants.Errors.EMAIL_IS_INCORRECT);
            request.getServletContext().getRequestDispatcher(Constants.Pages.SIGNUP_PAGE).forward(servletRequest, servletResponse);
       // } else if (!UserInputValidator.validateUsername(username)) {
       //     System.out.println("UserName Error");
       //     request.setAttribute(Constants.Errors.ERROR, Constants.Errors.USERNAME_IS_INCORRECT);
       //     request.getServletContext().getRequestDispatcher(Constants.Pages.SIGNUP_PAGE).forward(servletRequest, servletResponse);
        } else if (!UserInputValidator.validatePassword(password)) {
            System.out.println("Password Error");
            request.setAttribute(Constants.Errors.ERROR, Constants.Errors.PASSWORD_IS_INCORRECT);
            request.getServletContext().getRequestDispatcher(Constants.Pages.SIGNUP_PAGE).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
