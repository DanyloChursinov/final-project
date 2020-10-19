package com.chursinov.beautysalon.controller.action.get;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOutAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new ActionResult(Constants.Pages.INDEX_PAGE);
    }
}
