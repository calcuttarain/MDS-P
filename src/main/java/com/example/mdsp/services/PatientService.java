package com.example.mdsp.business.services;

import com.example.mdsp.business.models.Patient;
import com.example.mdsp.exceptions.ElementNotFoundException;
import com.example.mdsp.repos.PatientRepo;

import java.sql.SQLException;

public class PatientService {
    private static PatientRepo repo;
    private PatientService() {}

    public static Patient getPatient(int pacient_id) throws SQLException, ElementNotFoundException {
        repo = PatientRepo.getInstance();
        Patient patient = repo.get(pacient_id);
        return patient;
    }

    public static void deletePatientAccount(Patient patient) throws SQLException {
        repo = PatientRepo.getInstance();
        repo.delete(patient.getUserId());
    }

    public static void updateMedicalHistory(Patient patient, String new_info) throws SQLException {
        repo = PatientRepo.getInstance();
        String updatedHistory = patient.getMedicalHistory() + '\n' + new_info;
        patient.setMedicalHistory(updatedHistory);
        repo.update(patient);
    }

    public static void updateAllergies(Patient patient, String new_allergies) throws SQLException {
        repo = PatientRepo.getInstance();
        patient.setAllergies(patient.getAllergies() + ' ' + new_allergies);
        repo.update(patient);
    }

}
