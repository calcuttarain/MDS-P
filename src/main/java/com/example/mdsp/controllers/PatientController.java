package com.example.mdsp.controllers;

import com.example.mdsp.exceptions.EmailAlreadyExistsException;
import com.example.mdsp.exceptions.ElementNotFoundException;
import com.example.mdsp.models.Doctor;
import com.example.mdsp.models.Patient;
import com.example.mdsp.services.AppointmentService;
import com.example.mdsp.services.DoctorService;
import com.example.mdsp.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.mdsp.services.AppointmentService.requestAppointment;

@RestController
@RequestMapping("/mds/api")
public class PatientController {

    private final PatientService patientService;
    private int patient_id;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patient")
    public Map<String, Object> executeFunction(@RequestBody Map<String, Object> request) {
        String functionName = (String) request.get("function");
        Map<String, Object> parameters = (Map<String, Object>) request.get("parameters");

        Map<String, Object> response = new HashMap<>();
        try {
            switch (functionName) {
                case "getPatient":
                    String userIdStr = (String) parameters.get("patient_id");
                    patient_id = Integer.parseInt(userIdStr);
                    Patient patient = patientService.getPatient(patient_id);
                    response.put("result", "Patient found.");
                    response.put("patient", patient);
                    break;
                case "updateMedicalHistory":
                    String userIdStr1 = (String) parameters.get("patient_id");
                    patient_id = Integer.parseInt(userIdStr1);
                    String newInfo = (String) parameters.get("new_info");
                    patientService.updateMedicalHistory(patient_id, newInfo);
                    response.put("result", "Medical history updated successfully.");
                    break;
                case "updateAllergies":
                    String userIdStr2 = (String) parameters.get("patient_id");
                    patient_id = Integer.parseInt(userIdStr2);
                    String newAllergies = (String) parameters.get("new_allergies");
                    patientService.updateAllergies(patient_id, newAllergies);
                    response.put("result", "Allergies updated successfully.");
                    break;
                case "deletePatientAccount":
                    String userIdStr3 = (String) parameters.get("patient_id");
                    patient_id = Integer.parseInt(userIdStr3);
                    patientService.deletePatientAccount(patient_id);
                    response.put("result", "Patient account deleted successfully.");
                    break;
                case "getDoctorsBySpecialization":
                    String specialization = (String) parameters.get("specialization");
                    List<Doctor> doctors = DoctorService.getDoctorsBySpecialization(specialization);
                    response.put("result", "Doctors found by specialization.");
                    response.put("doctors", doctors);
                    break;
                case "requestAppointment":
                    String specialization1 = (String) parameters.get("specialization");
                    String userIdStr4 = (String) parameters.get("patient_id");
                    List<Doctor> doctors1 = DoctorService.getDoctorsBySpecialization(specialization1);
                    patient_id = Integer.parseInt(userIdStr4);
                    int doctor_id = doctors1.get(0).getUserId();
                    AppointmentService.requestAppointment(
                            patient_id,
                            doctor_id,
                            (String) parameters.get("date_string"),
                            (String) parameters.get("notes")
                    );
                    response.put("result", "Appointment requested successfully.");
                    response.put("doctor", doctors1.get(0));
                    break;
                default:
                    response.put("error", "Unknown function: " + functionName);
            }
        } catch (ElementNotFoundException e) {
            response.put("error", e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
