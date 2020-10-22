package com.chursinov.beautysalon.service;

import com.chursinov.beautysalon.entity.appointment.Appointment;
import com.chursinov.beautysalon.entity.appointment.AppointmentDoneStatus;
import com.chursinov.beautysalon.entity.appointment.AppointmentPaidStatus;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointmentsByClient(int clientId);
    List<Appointment> getNotDoneAppointmentsForAdmin();
    List<Appointment> getNotPaidAppointmentsForAdmin();
    List<String> getWorkingHours();
    List<Appointment> getAppointmentsByMasterForAdminUpdate(int masterId, int appointmentId);

    void updateWorkingTimeForAdmin(String startTime, String endTime);
    void updateAppointmentTimeForAdmin(String startTime, String endTime, int appointmentId);
    void updateAppointmentPaidStatus(AppointmentPaidStatus appointmentPaidStatus, int appointmentId);
    void deleteAppointment(int appointmentDeleteId);
    void addAppointment (String startTime, String endTime, int productId, int clientId);

    List<Appointment> getAppointmentsForMaster(int masterId);
    void updateAppointmentDoneStatus(AppointmentDoneStatus appointmentDoneStatus, int appointmentId);
}
