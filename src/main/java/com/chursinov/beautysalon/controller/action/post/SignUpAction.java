package com.chursinov.beautysalon.controller.action.post;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.ResponseType;
import com.chursinov.beautysalon.entity.User;
import com.chursinov.beautysalon.exception.DataAccessException;
import com.chursinov.beautysalon.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignUpAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        ServletContext context = request.getServletContext();
        UserService service = (UserService) context.getAttribute("UserService");
        User user = service.getUserByEmail(email);
        if (user == null) {
            try {
                service.addUser(username, email, password);
            } catch (DataAccessException e) {
                request.setAttribute(Constants.Errors.ERROR, Constants.Errors.SOMETHING_GOES_WRONG);
                return new ActionResult(Constants.Pages.SIGNUP_PAGE);
            }
            User newUser = service.getUserByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("user", newUser);
            return new ActionResult(Constants.GetActions.SERVICES, ResponseType.REDIRECT);
        } else {
            request.setAttribute(Constants.Errors.ERROR, Constants.Errors.USER_IS_ALREADY_EXIST);
            return new ActionResult(Constants.Pages.SIGNUP_PAGE);
        }
    }
}
