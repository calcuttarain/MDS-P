package com.example.mdsp.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Doctor extends User {
    private String specialization;
    private String description;
    private int office_id;

    public Doctor(int user_id, String first_name, String last_name, String email, String password_hash, Role role, String phone, String specialization, String description, int office_id) {
        super(user_id, first_name, last_name, email, password_hash, role, phone);
        this.specialization = specialization;
        this.description = description;
        this.office_id = office_id;
    }

    public Doctor(String first_name, String last_name, String email, String password_hash, Role role, String phone, String specialization, String description, int office_id) {
        super(first_name, last_name, email, password_hash, role, phone);
        this.specialization = specialization;
        this.description = description;
        this.office_id = office_id;
    }

    //setters getters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOfficeId() {
        return office_id;
    }

    public void setOfficeId(int office_id) {
        this.office_id = office_id;
    }

    @Override
    public String getRole() {
        return Role.DOCTOR.toString();
    }

    @Override
    public String toString() {
        return "Doctor\n" +
                "->first_name: " + first_name + '\n' +
                "->last_name: " + last_name + '\n' +
                "->email: " + email + '\n' +
                "->password_hash: " + password_hash + '\n' +
                "->role: " + role + '\n' +
                "->phone: " + phone + '\n' +
                "->specialization: " + specialization + '\n' +
                "->description: " + description + '\n';
    }

}

