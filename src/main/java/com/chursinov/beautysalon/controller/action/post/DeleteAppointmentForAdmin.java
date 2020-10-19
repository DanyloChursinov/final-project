package com.chursinov.beautysalon.controller.action.post;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.ResponseType;
import com.chursinov.beautysalon.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAppointmentForAdmin implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        int appointmentDeleteId = Integer.parseInt(request.getParameter("appointmentDeleteId"));
        AppointmentService appointmentService = (AppointmentService) request.getServletContext().getAttribute("AppointmentService");
        appointmentService.deleteAppointment(appointmentDeleteId);
        return new ActionResult(Constants.GetActions.ADMIN_PROFILE, ResponseType.REDIRECT);
    }
}
