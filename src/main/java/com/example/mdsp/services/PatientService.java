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

    public static Patient getPatient(int pacient_id) throws ElementNotFoundException {
        Patient patient = repo.get(pacient_id);
        return patient;
    }

    public static void deletePatientAccount(Patient patient) {
        repo.delete(patient.getUserId());
    }

    public static void updateMedicalHistory(Patient patient, String new_info){
        String updatedHistory = patient.getMedicalHistory() + '\n' + new_info;
        patient.setMedicalHistory(updatedHistory);
        repo.update(patient);
    }

    public static void updateAllergies(Patient patient, String new_allergies){
        patient.setAllergies(patient.getAllergies() + ' ' + new_allergies);
        repo.update(patient);
    }

}
