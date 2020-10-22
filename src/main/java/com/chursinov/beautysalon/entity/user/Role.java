package com.chursinov.beautysalon.entity.user;

public enum Role {
    ADMIN(1, "admin"), MASTER(2, "master"), CLIENT(3, "client");

    int id;
    String value;

    Role(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public int getId() {
        return id;
    }

    public boolean equalsTo(Role otherRole) {
        return value.equals(otherRole.value);
    }
}
