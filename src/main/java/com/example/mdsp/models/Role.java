package com.example.mdsp.models;

public enum Role {
    DOCTOR("doctor"),
    PATIENT("patient");

    private final String roleString;

    Role(String roleString) {
        this.roleString = roleString;
    }

    // Metodă pentru a returna stringul asociat rolului
    public String getRoleString() {
        return roleString;
    }

    // Metodă pentru a returna stringul asociat rolului
    @Override
    public String toString() {
        return roleString;
    }
}
