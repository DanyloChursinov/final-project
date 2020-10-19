package com.chursinov.beautysalon.entity;

public enum AppointmentPaidStatus {
    PAID("paid"), NOT_PAID("not_paid");

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
