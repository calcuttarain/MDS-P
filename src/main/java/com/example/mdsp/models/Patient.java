package com.example.mdsp.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
@EntityScan
public class Patient extends User {
    private String medical_history;
    private String allergies;
    private String blood_type;

    public Patient(int user_id, String first_name, String last_name, String email, String password_hash, Role role, String phone, String medical_history, String allergies, String blood_type) {
        super(user_id, first_name, last_name, email, password_hash, role, phone);
        this.medical_history = medical_history;
        this.allergies = allergies;
        this.blood_type = blood_type;
    }

    public Patient(String first_name, String last_name, String email, String password_hash, Role role, String phone, String medical_history, String allergies, String blood_type) {
        super(first_name, last_name, email, password_hash, role, phone);
        this.medical_history = medical_history;
        this.allergies = allergies;
        this.blood_type = blood_type;
    }

    //setters getters
    public String getMedicalHistory() {
        return medical_history;
    }

    public void setMedicalHistory(String medical_history) {
        this.medical_history = medical_history;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getBloodType() {
        return blood_type;
    }

    public void setBloodType(String blood_type) {
        this.blood_type = blood_type;
    }

    @Override
    public String getRole() {
        return Role.PATIENT.toString();
    }
}
