package com.chursinov.beautysalon.controller.action.post;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.ResponseType;
import com.chursinov.beautysalon.entity.user.User;
import com.chursinov.beautysalon.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        ServletContext context = request.getServletContext();
        UserService service = (UserService) context.getAttribute("UserService");
        User user = service.getUserByEmailAndPassword(email, password);
        if (user != null ) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return new ActionResult(Constants.GetActions.SERVICES, ResponseType.REDIRECT);
        } else {
            request.setAttribute(Constants.Errors.ERROR, Constants.Errors.INCORRECT_EMAIL_OR_PASSWORD);
        }
        return new ActionResult(Constants.Pages.SIGNIN_PAGE);
    }
}
