package com.chursinov.beautysalon.entity.appointment;

public enum AppointmentPaidStatus {
    PAID("Paid"), NOT_PAID("Not paid");

    private String value;

    AppointmentPaidStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
