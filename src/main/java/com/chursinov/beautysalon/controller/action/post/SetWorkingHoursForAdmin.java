package com.chursinov.beautysalon.controller.action.post;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.ResponseType;
import com.chursinov.beautysalon.service.AppointmentService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetWorkingHoursForAdmin implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        String startTimeString = request.getParameter("newStartHours");
        String endTimeString = request.getParameter("newEndHours");
        ServletContext context = request.getServletContext();
        AppointmentService service = (AppointmentService) context.getAttribute("AppointmentService");

        service.updateWorkingTimeForAdmin(startTimeString, endTimeString);
        return new ActionResult(Constants.GetActions.ADMIN_PROFILE, ResponseType.REDIRECT);
    }
}
