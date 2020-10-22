package com.chursinov.beautysalon.entity.appointment;

import java.math.BigDecimal;

public class Appointment {

    private int id;
    private String startTime;
    private String endTime;
    private String startWorkingHours;
    private String endWorkingHours;
    private AppointmentDoneStatus appointmentDoneStatusForMaster;
    private AppointmentPaidStatus appointmentPaidStatusForAdmin;
    private int clientId;
    private String clientName;
    private int productId;
    private String productName;
    private BigDecimal productPrice;
    private int masterId;
    private String masterName;
    private int duration;

    public String getStartWorkingHours() {
        return startWorkingHours;
    }

    public void setStartWorkingHours(String startWorkingHours) {
        this.startWorkingHours = startWorkingHours;
    }

    public String getEndWorkingHours() {
        return endWorkingHours;
    }

    public void setEndWorkingHours(String endWorkingHours) {
        this.endWorkingHours = endWorkingHours;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public AppointmentDoneStatus getAppointmentDoneStatusForMaster() {
        return appointmentDoneStatusForMaster;
    }

    public void setAppointmentDoneStatusForMaster(AppointmentDoneStatus appointmentDoneStatusForMaster) {
        this.appointmentDoneStatusForMaster = appointmentDoneStatusForMaster;
    }

    public AppointmentPaidStatus getAppointmentPaidStatusForAdmin() {
        return appointmentPaidStatusForAdmin;
    }

    public void setAppointmentPaidStatusForAdmin(AppointmentPaidStatus appointmentPaidStatusForAdmin) {
        this.appointmentPaidStatusForAdmin = appointmentPaidStatusForAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }
}
