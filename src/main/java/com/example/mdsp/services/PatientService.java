package com.example.mdsp.services;

import com.example.mdsp.models.Patient;
import com.example.mdsp.exceptions.ElementNotFoundException;
import com.example.mdsp.repos.PatientRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class PatientService {
    private static PatientRepo repo = new PatientRepo();
    public PatientService() {}

    public static Patient getPatient(int patient_id) throws ElementNotFoundException {
        Patient patient = repo.get(patient_id);
        return patient;
    }
    public static void deletePatientAccount(int patient_id) {
        repo.delete(patient_id);
    }

    public static void updateMedicalHistory(int patient_id, String new_info) throws ElementNotFoundException {
        Patient patient = repo.get(patient_id);
        String updatedHistory = patient.getMedicalHistory() + '\n' + new_info;
        patient.setMedicalHistory(updatedHistory);
        repo.update(patient);
    }

    public static void updateAllergies(int patient_id, String new_allergies) throws ElementNotFoundException {
        Patient patient = repo.get(patient_id);
        patient.setAllergies(patient.getAllergies() + ' ' + new_allergies);
        repo.update(patient);
    }

}
