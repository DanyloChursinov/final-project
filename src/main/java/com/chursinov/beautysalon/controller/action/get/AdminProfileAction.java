package com.chursinov.beautysalon.controller.action.get;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.post.AddAppointmentAction;
import com.chursinov.beautysalon.entity.appointment.Appointment;
import com.chursinov.beautysalon.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminProfileAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        AppointmentService service = (AppointmentService) request.getServletContext().getAttribute("AppointmentService");
        List<Appointment> notDoneAppointments = service.getNotDoneAppointmentsForAdmin();
        request.setAttribute("notDoneAppointments", notDoneAppointments);
        List<Appointment> notPaidAppointments = service.getNotPaidAppointmentsForAdmin();
        request.setAttribute("notPaidAppointments", notPaidAppointments);
        AddAppointmentAction.correctValuesForPicker(request);
        return new ActionResult(Constants.Pages.ADMIN_PROFILE_PAGE);
        }
}
