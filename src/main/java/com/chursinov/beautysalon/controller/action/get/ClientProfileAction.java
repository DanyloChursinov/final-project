package com.chursinov.beautysalon.controller.action.get;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.entity.appointment.Appointment;
import com.chursinov.beautysalon.entity.user.User;
import com.chursinov.beautysalon.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientProfileAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");
        AppointmentService service = (AppointmentService) request.getServletContext().getAttribute("AppointmentService");
        List<Appointment> appointments = service.getAppointmentsByClient(user.getId());
        request.setAttribute("appointments", appointments);

        return new ActionResult(Constants.Pages.CLIENT_PROFILE_PAGE);
    }
}
