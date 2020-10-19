package com.chursinov.beautysalon.controller.action.post;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.ResponseType;
import com.chursinov.beautysalon.entity.Appointment;
import com.chursinov.beautysalon.service.AppointmentService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChangeTimeForAdmin implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        AddAppointmentAction.correctValuesForPicker(request);

        String startTimeString = request.getParameter("newStartTime");
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int masterId = Integer.parseInt(request.getParameter("masterId"));

        ServletContext context = request.getServletContext();
        AppointmentService service = (AppointmentService) context.getAttribute("AppointmentService");
        List<Appointment> appointments = service.getAppointmentsByMasterForAdminUpdate(masterId,appointmentId);

        String checkMinutes = startTimeString.substring(startTimeString.length()-2);
        if (!(checkMinutes.equals("00") || checkMinutes.equals("30"))) {
            request.setAttribute(Constants.Errors.ERROR, Constants.Errors.SET_MINUTES_IN_CORRECT);
            return new ActionResult (Constants.Pages.ADMIN_PROFILE_PAGE);
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(startTimeString.replace("T", " "), format);
        LocalDateTime endTime = startTime.plusMinutes(duration);

        ArrayList <String> bookedTime = AddAppointmentAction.getAllBookedTimeSlotsByDate(appointments, startTimeString);

        for (Appointment appointment : appointments) {
            String checkStartsString = appointment.getStartTime();
            String checkEndsString = appointment.getEndTime();
            LocalDateTime checkStarts = LocalDateTime.parse(checkStartsString, format);
            LocalDateTime checkEnds = LocalDateTime.parse(checkEndsString, format);

            if (startTime.isAfter(checkStarts) && startTime.isBefore(checkEnds)
                    || startTime.isEqual(checkStarts)) {
                request.setAttribute("bookedTime", bookedTime);
                request.setAttribute(Constants.Errors.ERROR, Constants.Errors.TIME_IS_ALREADY_TAKEN);
                return new ActionResult(Constants.Pages.ADMIN_PROFILE_PAGE);

            } else if (endTime.isAfter(checkStarts) && endTime.isBefore(checkEnds)
                    || endTime.isEqual(checkEnds)){
                request.setAttribute("bookedTime", bookedTime);
                request.setAttribute(Constants.Errors.ERROR, Constants.Errors.TIME_IS_ALREADY_TAKEN);
                return new ActionResult(Constants.Pages.ADMIN_PROFILE_PAGE);
            }
        }
        String startTimeToString = startTime.format(format);
        String endTimeToString = endTime.format(format);
        service.updateAppointmentTimeForAdmin(startTimeToString, endTimeToString, appointmentId);
        return new ActionResult(Constants.GetActions.ADMIN_PROFILE, ResponseType.REDIRECT);
    }
}
