package com.chursinov.beautysalon.controller;

import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionFactory;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "/controller",
        urlPatterns = {"/signin", "/signup", "/signout", "/service",
                "/search-products", "/add-appointment", "/review" , "/add-review",
                "/client-profile", "/done-appointment", "/master-profile",
                "/admin-profile", "/paid-appointment", "/delete-appointment",
                "/change-time", "/change-locale", "/change-working-time", "/home-page"}) //TODO question about img
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Action action = ActionFactory.getAction(request);
        ActionResult result = action.execute(request, response);
        Page page = new Page(request,response);
        page.navigate(result);
    }
}
