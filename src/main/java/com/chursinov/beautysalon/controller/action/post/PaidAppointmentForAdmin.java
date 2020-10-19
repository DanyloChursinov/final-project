package com.chursinov.beautysalon.controller.action.post;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.ResponseType;
import com.chursinov.beautysalon.entity.AppointmentPaidStatus;
import com.chursinov.beautysalon.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaidAppointmentForAdmin implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        AppointmentPaidStatus status = AppointmentPaidStatus.valueOf(request.getParameter("paidStatus").toUpperCase());
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        AppointmentService appointmentService = (AppointmentService) request.getServletContext().getAttribute("AppointmentService");
        appointmentService.updateAppointmentPaidStatus(status, appointmentId);
        return new ActionResult(Constants.GetActions.ADMIN_PROFILE, ResponseType.REDIRECT);
    }
}
