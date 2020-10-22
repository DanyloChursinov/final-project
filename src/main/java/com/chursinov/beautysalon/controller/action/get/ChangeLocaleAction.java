package com.chursinov.beautysalon.controller.action.get;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocaleAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        String currentLocale = (String) request.getSession().getAttribute(Constants.Attributes.CURRENT_LOCALE);
        if (currentLocale == null || "en".equals(currentLocale)) {
            request.getSession().setAttribute(Constants.Attributes.CURRENT_LOCALE, "ru");
        } else {
            request.getSession().setAttribute(Constants.Attributes.CURRENT_LOCALE, "en");
        }
        return new ActionResult (Constants.Pages.INDEX_PAGE);

    }
}