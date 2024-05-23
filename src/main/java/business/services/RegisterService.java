package business.services;

import business.models.Doctor;
import business.models.Patient;
import business.models.Role;
import persistance.repos.DoctorRepo;
import persistance.repos.PatientRepo;

import java.sql.SQLException;

public class RegisterService {
    private static PatientRepo patient_repo;
    private static DoctorRepo doctor_repo;
    public static void createPatientAccount(String first_name, String last_name, String email, String password, String phone, String medical_history, String allergies, String blood_type)
    {
        try {
            patient_repo = new PatientRepo();
            String password_hash = InputUtils.hashPassword(password);
            Patient patient = new Patient(-1, first_name, last_name, email, password_hash, "patient", phone, medical_history, allergies, blood_type);
            patient_repo.add(patient);
        } catch (SQLException e) {
            System.err.println(STR."Database error: \{e.getMessage()}");
        }
    }

    public static void createDoctorAccount(String first_name, String last_name, String email, String password, String phone, String specialization, String description, int office_id)
    {
        try {
            doctor_repo = new DoctorRepo();
            String password_hash = InputUtils.hashPassword(password);
            Doctor doctor = new Doctor(-1, first_name, last_name, email, password_hash, "doctor", phone, specialization, description, office_id);
            doctor_repo.add(doctor);
        } catch (SQLException e) {
            System.err.println(STR."Database error: \{e.getMessage()}");
        }
    }


}
