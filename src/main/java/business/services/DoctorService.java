package business.services;

import business.models.Appointment;
import business.models.Doctor;
import business.models.Patient;
import exceptions.ElementNotFoundException;
import persistance.repos.AppointmentRepo;
import persistance.repos.DoctorRepo;
import persistance.repos.PatientRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private DoctorService() {}
    private static DoctorRepo repo;

    public static Doctor getDoctor(int doctor_id) throws SQLException, ElementNotFoundException {
        repo = DoctorRepo.getInstance();
        Doctor doctor = repo.get(doctor_id);
        return doctor;
    }

    public static void changeDescription(Doctor doctor, String new_description) throws SQLException {
        repo = DoctorRepo.getInstance();
        doctor.setDescription(new_description);
        repo.update(doctor);
    }

    public static List<Doctor> getDoctorsBySpecialization(String specialization) throws SQLException {
        repo = DoctorRepo.getInstance();
        List<Doctor> doctors = repo.getDoctorsBySpecialization(specialization);
        return doctors;
    }

}
