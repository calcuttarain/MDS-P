package com.example.mdsp.services;

import com.example.mdsp.models.Appointment;
import com.example.mdsp.models.Status;
import com.example.mdsp.repos.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@Service
public class AppointmentService {

    private static AppointmentRepo appointment_repo = new AppointmentRepo();
    public AppointmentService() {}

    public static void requestAppointment(int patient_id, int doctor_id, String date_string, String notes) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH:mm");
            Date date = formatter.parse(date_string);
            Appointment appointment = new Appointment(patient_id, doctor_id, date, "UNCONFIRMED", notes);
            appointment_repo.add(appointment);
        } catch (ParseException e) {
            System.err.println(STR."DateTime parse error: \{e.getMessage()}");
        }
    }

    public static void approveAppointment(Appointment appointment) throws SQLException {
        appointment.setStatus(Status.CONFIRMED);
        appointment_repo.update(appointment);
    }

    public static void requestRescheduleAppointment(Appointment appointment, String date_string) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH:mm");
        try {
            Date date = formatter.parse(date_string);
            appointment.setStatus(Status.UNCONFIRMED);
            appointment.setAppointmentDate(date);
            appointment_repo.update(appointment);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static void approveRescheduleAppointment(Appointment appointment){
        appointment.setStatus(Status.RESCHEDULED);
        appointment_repo.update(appointment);
    }

    public static void completeAppointment(Appointment appointment){
        appointment.setStatus(Status.COMPLETED);
        appointment_repo.update(appointment);
    }

    public static void cancelAppointment(Appointment appointment) {
        appointment.setStatus(Status.CANCELED);
        appointment_repo.update(appointment);
    }

    public static void changeNotes(Appointment appointment, String new_notes) {
        appointment.setNotes(new_notes);
        appointment_repo.update(appointment);
    }

    public static ArrayList<Appointment> getDoctorAppointments(int doctor_id) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>(appointment_repo.getAppointmentsByDoctorId(doctor_id));
        return appointments;
    }

    public static ArrayList<Appointment> getPacientAppointments(int pacient_id) {
        ArrayList<Appointment> appointments = new ArrayList<Appointment>(appointment_repo.getAppointmentsByPatientId(pacient_id));
        return appointments;
    }
}
