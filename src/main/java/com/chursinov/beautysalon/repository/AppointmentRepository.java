package com.chursinov.beautysalon.repository;

import com.chursinov.beautysalon.entity.appointment.Appointment;
import com.chursinov.beautysalon.entity.appointment.AppointmentDoneStatus;
import com.chursinov.beautysalon.entity.appointment.AppointmentPaidStatus;

import java.util.List;

public interface AppointmentRepository {
    List<Appointment> getAppointmentsByClient(int clientId);
    List<Appointment> getNotDoneAppointmentsForAdmin();
    List<Appointment> getNotPaidAppointmentsForAdmin();
    List<String> getWorkingHours();
    void updateAppointmentPaidStatus(AppointmentPaidStatus appointmentPaidStatus, int appointmentId);
    List<Appointment> getAppointmentsByMasterForAdminUpdate(int masterId, int appointmentId);
    void updateAppointmentTimeForAdmin(String startTime, String endTime, int appointmentId);
    void deleteAppointment(int appointmentDeleteId);
    void addAppointment (String startTime, String endTime, int productId, int clientId);
    List<Appointment> getAppointmentsForMaster(int masterId);
    void updateAppointmentDoneStatus(AppointmentDoneStatus appointmentDoneStatus, int appointmentId);

    void updateWorkingTimeForAdmin(String startTime, String endTime);
}
