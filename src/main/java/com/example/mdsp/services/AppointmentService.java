package com.example.mdsp.business.services;

import com.example.mdsp.business.models.Appointment;
import com.example.mdsp.business.models.Status;
import com.example.mdsp.repos.AppointmentRepo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AppointmentService {
    private AppointmentService() {}
    private static AppointmentRepo appointment_repo;

    public static void requestAppointment(int patient_id, int doctor_id, String date_string, String notes) throws SQLException {
        try {
            appointment_repo = AppointmentRepo.getInstance();
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
        appointment_repo = AppointmentRepo.getInstance();
        appointment_repo.update(appointment);
    }

    public static void requestRescheduleAppointment(Appointment appointment, String date_string) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH:mm");
        try {
            Date date = formatter.parse(date_string);
            appointment.setStatus(Status.UNCONFIRMED);
            appointment.setAppointmentDate(date);
            appointment_repo = AppointmentRepo.getInstance();
            appointment_repo.update(appointment);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static void approveRescheduleAppointment(Appointment appointment) throws SQLException {
        appointment.setStatus(Status.RESCHEDULED);
        appointment_repo = AppointmentRepo.getInstance();
        appointment_repo.update(appointment);
    }

    public static void completeAppointment(Appointment appointment) throws SQLException {
        appointment.setStatus(Status.COMPLETED);
        appointment_repo = AppointmentRepo.getInstance();
        appointment_repo.update(appointment);
    }

    public static void cancelAppointment(Appointment appointment) throws SQLException {
        appointment.setStatus(Status.CANCELED);
        appointment_repo = AppointmentRepo.getInstance();
        appointment_repo.update(appointment);
    }

    public static void changeNotes(Appointment appointment, String new_notes) throws SQLException {
        appointment.setNotes(new_notes);
        appointment_repo = AppointmentRepo.getInstance();
        appointment_repo.update(appointment);
    }

    public static ArrayList<Appointment> getDoctorAppointments(int doctor_id) throws SQLException {
        appointment_repo = AppointmentRepo.getInstance();
        ArrayList<Appointment> appointments = new ArrayList<Appointment>(appointment_repo.getAppointmentsByDoctorId(doctor_id));
        return appointments;
    }

    public static ArrayList<Appointment> getPacientAppointments(int pacient_id) throws SQLException {
        appointment_repo = AppointmentRepo.getInstance();
        ArrayList<Appointment> appointments = new ArrayList<Appointment>(appointment_repo.getAppointmentsByPatientId(pacient_id));
        return appointments;
    }
}
