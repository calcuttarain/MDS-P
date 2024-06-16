package com.example.mdsp.services;
import com.example.mdsp.exceptions.EmailAlreadyExistsException;
import com.example.mdsp.models.Doctor;
import com.example.mdsp.models.Patient;
import com.example.mdsp.models.Role;
import com.example.mdsp.repos.DoctorRepo;
import com.example.mdsp.repos.PatientRepo;
import com.example.mdsp.repos.UserRepo;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service
public class RegisterService {
    private static PatientRepo patient_repo = new PatientRepo();
    private static DoctorRepo doctor_repo = new DoctorRepo();
    private static UserRepo user_repo = new UserRepo();
    public RegisterService() {}

    public static void createPacientAccount(String first_name, String last_name, String email, String password, String phone, String medical_history, String allergies, String blood_type) throws SQLException, EmailAlreadyExistsException {
        user_repo.AbstractIdVerification(email);
        String hashedPassword = InputUtils.hashPassword(password);
        Patient patient = new Patient(first_name, last_name, email, hashedPassword, Role.PATIENT, phone, medical_history, allergies, blood_type);
        patient_repo.add(patient);
    }
        public static void createDoctorAccount(String first_name, String last_name, String email, String password, String phone, String specialization, String description, int office_id) throws SQLException, EmailAlreadyExistsException {
        user_repo.AbstractIdVerification(email);
        String hashed_password = InputUtils.hashPassword(password);
        Doctor doctor = new Doctor(first_name, last_name, email, hashed_password, Role.DOCTOR, phone, specialization, description, office_id);
        doctor_repo.add(doctor);
    }
}