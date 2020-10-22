package com.chursinov.beautysalon.controller.action.post;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.ResponseType;
import com.chursinov.beautysalon.entity.appointment.AppointmentDoneStatus;
import com.chursinov.beautysalon.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoneAppointmentForMaster implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        AppointmentDoneStatus status = AppointmentDoneStatus.valueOf(request.getParameter("doneStatus").toUpperCase().replace(" ", "_"));
        int productId = Integer.parseInt(request.getParameter("appointmentId"));
        AppointmentService appointmentService = (AppointmentService) request.getServletContext().getAttribute("AppointmentService");
        appointmentService.updateAppointmentDoneStatus(status, productId);

        return new ActionResult(Constants.GetActions.MASTER_PROFILE, ResponseType.REDIRECT);
    }
}
