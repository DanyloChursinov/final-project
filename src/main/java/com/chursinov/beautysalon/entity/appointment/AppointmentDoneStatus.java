package com.chursinov.beautysalon.entity.appointment;

public enum AppointmentDoneStatus {
    DONE("Done"),IN_PROGRESS("In progress"), NOT_DONE("Not done");

    private String value;

    AppointmentDoneStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
