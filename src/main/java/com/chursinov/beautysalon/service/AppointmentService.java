package com.chursinov.beautysalon.service;

import com.chursinov.beautysalon.entity.Appointment;
import com.chursinov.beautysalon.entity.AppointmentDoneStatus;
import com.chursinov.beautysalon.entity.AppointmentPaidStatus;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointmentsByClient(int clientId);
    List<Appointment> getNotDoneAppointmentsForAdmin();
    List<Appointment> getNotPaidAppointmentsForAdmin();
    List<Appointment> getAppointmentsByMasterForAdminUpdate(int masterId, int appointmentId);
    void updateAppointmentTimeForAdmin(String startTime, String endTime, int appointmentId);
    void updateAppointmentPaidStatus(AppointmentPaidStatus appointmentPaidStatus, int appointmentId);
    void deleteAppointment(int appointmentDeleteId);
    void addAppointment (String startTime, String endTime, int productId, int clientId);
    List<Appointment> getAppointmentsForMaster(int masterId);
    void updateAppointmentDoneStatus(AppointmentDoneStatus appointmentDoneStatus, int appointmentId);
}
