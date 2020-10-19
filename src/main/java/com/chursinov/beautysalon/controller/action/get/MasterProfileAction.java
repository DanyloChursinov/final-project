package com.chursinov.beautysalon.controller.action.get;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.entity.Appointment;
import com.chursinov.beautysalon.entity.User;
import com.chursinov.beautysalon.service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MasterProfileAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        AppointmentService service = (AppointmentService) request.getServletContext().getAttribute("AppointmentService");
        List<Appointment> appointments = service.getAppointmentsForMaster(user.getId());
        request.setAttribute("appointments", appointments);
        return new ActionResult(Constants.Pages.MASTER_PROFILE_PAGE);
    }
}
