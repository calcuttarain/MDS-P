package com.example.mdsp.services;

import com.example.mdsp.models.Doctor;
import com.example.mdsp.exceptions.ElementNotFoundException;
import com.example.mdsp.repos.DoctorRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
@Service
public class DoctorService {
    public DoctorService() {}
    private static DoctorRepo repo = new DoctorRepo();

    public static Doctor getDoctor(int doctor_id) throws ElementNotFoundException {
        Doctor doctor = repo.get(doctor_id);
        return doctor;
    }

    public static void changeDescription(Doctor doctor, String new_description){
        doctor.setDescription(new_description);
        repo.update(doctor);
    }

    public static List<Doctor> getDoctorsBySpecialization(String specialization) throws SQLException {
        List<Doctor> doctors = repo.getDoctorsBySpecialization(specialization);
        return doctors;
    }

}
