package com.chursinov.beautysalon.controller.action.post;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.Action;
import com.chursinov.beautysalon.controller.action.ActionResult;
import com.chursinov.beautysalon.controller.action.ResponseType;
import com.chursinov.beautysalon.entity.appointment.Appointment;
import com.chursinov.beautysalon.entity.product.Product;
import com.chursinov.beautysalon.entity.user.User;
import com.chursinov.beautysalon.service.AppointmentService;
import com.chursinov.beautysalon.service.ProductService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddAppointmentAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) {

        correctValuesForPicker(request);

        String startTimeString = request.getParameter("startTime");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        int masterId = Integer.parseInt(request.getParameter("masterId"));
        User user = (User) request.getSession().getAttribute("user");

        ServletContext context = request.getServletContext();

        ProductService serviceProduct = (ProductService) context.getAttribute("ProductService");
        AppointmentService serviceAppointment = (AppointmentService) context.getAttribute("AppointmentService");

        List<Product> products = serviceProduct.getAllProducts();
        List<Appointment> appointments = serviceAppointment.getAppointmentsForMaster(masterId);
        request.setAttribute("services", products);

        String checkMinutes = startTimeString.substring(startTimeString.length()-2);
        if (!(checkMinutes.equals("00") || checkMinutes.equals("30"))) {
            request.setAttribute(Constants.Errors.ERROR, Constants.Errors.SET_MINUTES_IN_CORRECT);
            return new ActionResult(Constants.Pages.SERVICE_PAGE);
        }

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(startTimeString.replace("T", " "), format);
        LocalDateTime endTime = startTime.plusMinutes(duration);

        List<String> workingHours=serviceAppointment.getWorkingHours();
        if (!checkWorkingHours(startTime, endTime, workingHours, format)){
            request.setAttribute("startWorkingDay", workingHours.get(0));
            request.setAttribute("endWorkingDay", workingHours.get(1));
            request.setAttribute(Constants.Errors.ERROR, Constants.Errors.DO_NOT_WORKING_AT_THIS_TIME);
            return new ActionResult(Constants.Pages.SERVICE_PAGE);
        }

        ArrayList <String> bookedTime = getAllBookedTimeSlotsByDate(appointments, startTimeString);
        for (Appointment appointment : appointments) {
            String checkStartsString = appointment.getStartTime();
            String checkEndsString = appointment.getEndTime();
            LocalDateTime checkStarts = LocalDateTime.parse(checkStartsString, format);
            LocalDateTime checkEnds = LocalDateTime.parse(checkEndsString, format);

            if (startTime.isAfter(checkStarts) && startTime.isBefore(checkEnds)
                    || startTime.isEqual(checkStarts)) {
                request.setAttribute("bookedTime", bookedTime);
                request.setAttribute(Constants.Errors.ERROR, Constants.Errors.TIME_IS_ALREADY_TAKEN);
                return new ActionResult(Constants.Pages.SERVICE_PAGE);

            } else if (endTime.isAfter(checkStarts) && endTime.isBefore(checkEnds)
                    || endTime.isEqual(checkEnds)){
                request.setAttribute("bookedTime", bookedTime);
                request.setAttribute(Constants.Errors.ERROR, Constants.Errors.TIME_IS_ALREADY_TAKEN);
                return new ActionResult(Constants.Pages.SERVICE_PAGE);
            }
        }
        String startTimeToString = startTime.format(format);
        String endTimeToString = endTime.format(format);
        serviceAppointment.addAppointment(startTimeToString, endTimeToString, productId, user.getId());
        return new ActionResult(Constants.GetActions.CLIENT_PROFILE, ResponseType.REDIRECT);
    }


    public static void correctValuesForPicker(HttpServletRequest request) {
        LocalDateTime minTimeForPicker = LocalDateTime.now();
        DateTimeFormatter formatForPicker = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        minTimeForPicker = LocalDateTime.parse(minTimeForPicker.format(formatForPicker), formatForPicker);
        request.setAttribute("minTimeForPicker", minTimeForPicker);

        LocalDateTime maxTimeForPicker = minTimeForPicker.plusMonths(3);
        request.setAttribute("maxTimeForPicker", maxTimeForPicker);
    }

    public static ArrayList<String> getAllBookedTimeSlotsByDate(List<Appointment> appointments, String startTimeString){
        ArrayList <String> bookedTime = new ArrayList<>();
        String checkUserStartDate = startTimeString.substring(0, startTimeString.length()-6);

        for (Appointment appointment: appointments) {
            String startBookedDateTimes = appointment.getStartTime();
            String endBookedDateTimes = appointment.getEndTime();

            String checkStartDates = startBookedDateTimes.substring(0, startBookedDateTimes.length()-6);
            String checkEndDates = endBookedDateTimes.substring(0, endBookedDateTimes.length()-6);

            if (checkStartDates.equals(checkUserStartDate)
                    && checkEndDates.equals(checkUserStartDate)){
                bookedTime.add(startBookedDateTimes.substring(11)+ "-" + (endBookedDateTimes.substring(11)));
            }
        }
        return bookedTime;
    }

    public static boolean checkWorkingHours(LocalDateTime startTime, LocalDateTime endTime, List<String> workingHours, DateTimeFormatter format){

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String startWorkingTimeString = workingHours.get(0).substring(0, workingHours.get(0).length()-3);
        String endWorkingTimeString = workingHours.get(1).substring(0, workingHours.get(1).length()-3);
        String startWorkingDateTimeString = startTime.format(dateFormat).concat(" ").concat(startWorkingTimeString);
        String endWorkingDateTimeString = endTime.format(dateFormat).concat(" ").concat(endWorkingTimeString);

        LocalDateTime startWorkingTime = LocalDateTime.parse(startWorkingDateTimeString, format).plusDays(1);
        LocalDateTime endWorkingTime = LocalDateTime.parse(endWorkingDateTimeString, format);
        if (startTime.isAfter(endWorkingTime) && startTime.isBefore(startWorkingTime)) {
            return false;
        } else if (endTime.isAfter(endWorkingTime) && endTime.isBefore(startWorkingTime)) {
            return false;
        }
        LocalDateTime endWorkingTimeNextDay = endWorkingTime.minusDays(1);
        LocalDateTime startWorkingTimeNextDay = startWorkingTime.minusDays(1);
        if (startTime.isAfter(endWorkingTimeNextDay) && startTime.isBefore(startWorkingTimeNextDay)) {
            return false;
        } else if (endTime.isAfter(endWorkingTimeNextDay) && endTime.isBefore(startWorkingTimeNextDay)) {
            return false;
        }
        return true;
    }
}
