package business.services;
import business.models.Doctor;
import business.models.Patient;
import business.models.Role;
import persistance.repos.DoctorRepo;
import persistance.repos.PatientRepo;

import java.sql.SQLException;

public final class RegisterService {
    private RegisterService() {}

    public static void createPacientAccount(String first_name, String last_name, String email, String password, String phone, String medical_history, String allergies, String blood_type) throws SQLException {
        String hashedPassword = InputUtils.hashPassword(password);
        Patient patient = new Patient(first_name, last_name, email, hashedPassword, Role.PATIENT, phone, medical_history, allergies, blood_type);
        PatientRepo repo = PatientRepo.getInstance();
        repo.add(patient);
    }
        public static void createDoctorAccount(String first_name, String last_name, String email, String password, String phone, String specialization, String description, int office_id) throws SQLException {
        String hashed_password = InputUtils.hashPassword(password);
        Doctor doctor = new Doctor(first_name, last_name, email, hashed_password, Role.DOCTOR, phone, specialization, description, office_id);
        DoctorRepo repo = DoctorRepo.getInstance();
        repo.add(doctor);
    }
}