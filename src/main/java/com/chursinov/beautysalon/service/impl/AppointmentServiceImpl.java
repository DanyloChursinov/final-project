package com.chursinov.beautysalon.service.impl;

import com.chursinov.beautysalon.entity.appointment.Appointment;
import com.chursinov.beautysalon.entity.appointment.AppointmentDoneStatus;
import com.chursinov.beautysalon.entity.appointment.AppointmentPaidStatus;
import com.chursinov.beautysalon.repository.AppointmentRepository;
import com.chursinov.beautysalon.service.AppointmentService;

import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

    AppointmentRepository repository;

    public AppointmentServiceImpl(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Appointment> getAppointmentsByClient(int clientId) {
        return repository.getAppointmentsByClient(clientId);
    }

    @Override
    public List<Appointment> getNotDoneAppointmentsForAdmin() {
        return repository.getNotDoneAppointmentsForAdmin();
    }

    @Override
    public List<Appointment> getNotPaidAppointmentsForAdmin() {
        return repository.getNotPaidAppointmentsForAdmin();
    }

    @Override
    public List<String> getWorkingHours() {
        return repository.getWorkingHours();
    }

    @Override
    public List<Appointment> getAppointmentsByMasterForAdminUpdate(int masterId, int appointmentId) {
        return repository.getAppointmentsByMasterForAdminUpdate(masterId, appointmentId);
    }

    @Override
    public void updateWorkingTimeForAdmin(String startTime, String endTime) {
        repository.updateWorkingTimeForAdmin(startTime, endTime);
    }


    @Override
    public void updateAppointmentTimeForAdmin(String startTime, String endTime, int appointmentId) {
        repository.updateAppointmentTimeForAdmin(startTime,endTime,appointmentId);
    }

    @Override
    public void updateAppointmentPaidStatus(AppointmentPaidStatus appointmentPaidStatus, int appointmentId) {
        repository.updateAppointmentPaidStatus(appointmentPaidStatus, appointmentId);
    }

    @Override
    public void deleteAppointment(int appointmentDeleteId) {
        repository.deleteAppointment(appointmentDeleteId);
    }


    @Override
    public void addAppointment(String startTime, String endTime, int productId, int clientId) {
        repository.addAppointment(startTime,endTime,productId,clientId);
    }

    @Override
    public List<Appointment> getAppointmentsForMaster(int masterId) {
        return repository.getAppointmentsForMaster(masterId);
    }

    @Override
    public void updateAppointmentDoneStatus(AppointmentDoneStatus appointmentDoneStatus, int appointmentId) {
        repository.updateAppointmentDoneStatus(appointmentDoneStatus,appointmentId);
    }
}
