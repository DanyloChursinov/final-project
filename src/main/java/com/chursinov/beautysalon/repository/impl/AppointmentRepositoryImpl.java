package com.chursinov.beautysalon.repository.impl;

import com.chursinov.beautysalon.constants.Query;
import com.chursinov.beautysalon.entity.Appointment;
import com.chursinov.beautysalon.entity.AppointmentDoneStatus;
import com.chursinov.beautysalon.entity.AppointmentPaidStatus;
import com.chursinov.beautysalon.exception.DataAccessException;
import com.chursinov.beautysalon.repository.AppointmentRepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepositoryImpl extends AbstractRepository implements AppointmentRepository {

    private static final Logger logger = Logger.getLogger(AppointmentRepositoryImpl.class);

    private List<Appointment> extractAppointmentForUserFromResultSet(ResultSet resultSet) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        while (resultSet.next()) {

            String master = resultSet.getString("master");
            String client = resultSet.getString("client");
            client = Character.toUpperCase(client.charAt(0)) + client.substring(1);
            master = Character.toUpperCase(master.charAt(0)) + master.substring(1);

            String startTimeString = resultSet.getString("start_time");
            String endTimeString = resultSet.getString("end_time");

            Appointment appointment= new Appointment();
            appointment.setId(resultSet.getInt("appointments.id"));
            appointment.setStartTime(startTimeString.substring(0,startTimeString.length()-5));
            appointment.setEndTime(endTimeString.substring(0,endTimeString.length()-5));
            appointment.setAppointmentDoneStatusForMaster(AppointmentDoneStatus.valueOf(resultSet.getString("appointment_master_status").toUpperCase()));
            appointment.setAppointmentPaidStatusForAdmin(AppointmentPaidStatus.valueOf(resultSet.getString("appointment_admin_status").toUpperCase()));
            appointment.setClientId(resultSet.getInt("client_id"));
            appointment.setClientName(client);
            appointment.setProductId(resultSet.getInt("products.id"));
            appointment.setProductName(resultSet.getString("name"));
            appointment.setProductPrice(resultSet.getBigDecimal("price"));
            appointment.setDuration(resultSet.getInt("duration"));
            appointment.setMasterId(resultSet.getInt("master_id"));
            appointment.setMasterName(master);
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsByClient(int clientId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_APPOINTMENTS_BY_USER)) {
            int counter = 1;
            statement.setInt(counter++, clientId);
            ResultSet resultSet = statement.executeQuery();
            return extractAppointmentForUserFromResultSet(resultSet);
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Appointment> getNotDoneAppointmentsForAdmin() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_APPOINTMENTS_FOR_ADMIN_NOT_DONE)) {
            ResultSet resultSet = statement.executeQuery();
            return extractAppointmentForUserFromResultSet(resultSet);
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    @Override
    public List<Appointment> getNotPaidAppointmentsForAdmin() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_APPOINTMENTS_FOR_ADMIN_NOT_PAID)) {
            ResultSet resultSet = statement.executeQuery();
            return extractAppointmentForUserFromResultSet(resultSet);
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void addAppointment(String startTime, String endTime,int productId, int clientId) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.ADD_APPOINTMENT)) {
            int counter = 1;
            statement.setString(counter++, startTime);
            statement.setString(counter++, endTime);
            statement.setString(counter++, "not_done");
            statement.setString(counter++, "not_paid");
            statement.setInt(counter++, productId);
            statement.setInt(counter++, clientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Appointment> getAppointmentsForMaster(int masterId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_APPOINTMENTS_FOR_MASTER)) {
            int counter = 1;
            statement.setInt(counter++, masterId);
            ResultSet resultSet = statement.executeQuery();
            return extractAppointmentForUserFromResultSet(resultSet);
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Appointment> getAppointmentsByMasterForAdminUpdate(int masterId, int appointmentId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_APPOINTMENTS_BY_MASTER_FOR_ADMIN_UPDATE)) {
            int counter = 1;
            statement.setInt(counter++, masterId);
            statement.setInt(counter++, appointmentId);
            ResultSet resultSet = statement.executeQuery();
            return extractAppointmentForUserFromResultSet(resultSet);
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void updateAppointmentTimeForAdmin(String startTime, String endTime, int appointmentId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.UPDATE_APPOINTMENT_TIME)) {
            int counter = 1;
            statement.setString(counter++, startTime);
            statement.setString(counter++, endTime);
            statement.setInt(counter++, appointmentId);
            statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void updateAppointmentDoneStatus(AppointmentDoneStatus appointmentDoneStatus, int appointmentId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.UPDATE_DONE_STATUS)) {
            int counter = 1;
            statement.setString(counter++, appointmentDoneStatus.getValue());
            statement.setInt(counter++, appointmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void updateAppointmentPaidStatus(AppointmentPaidStatus appointmentPaidStatus, int appointmentId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.UPDATE_PAID_STATUS)) {
            int counter = 1;
            statement.setString(counter++, appointmentPaidStatus.getValue());
            statement.setInt(counter++, appointmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteAppointment(int appointmentDeleteId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.DELETE_APPOINTMENT)) {
            int counter = 1;
            statement.setInt(counter++, appointmentDeleteId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

}
