package com.chursinov.beautysalon.entity;

public enum AppointmentDoneStatus {
    DONE("done"),IN_PROGRESS("in_progress"), NOT_DONE("not_done");

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
