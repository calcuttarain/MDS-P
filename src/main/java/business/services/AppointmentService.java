package business.services;

import business.models.Appointment;
import business.models.Patient;
import business.models.Status;
import persistance.repos.AbstractRepo;
import persistance.repos.AppointmentRepo;
import persistance.repos.PatientRepo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentService {
    private static AppointmentRepo appointment_repo;
    public void requestAppointment(int patient_id, int doctor_id, String date_string, String notes) throws SQLException {
        try {
            appointment_repo = new AppointmentRepo();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH:mm");
            Date date = formatter.parse(date_string);
            Appointment appointment = new Appointment(-1, patient_id, doctor_id, date, "unconfirmed", notes);
            appointment_repo.add(appointment);
        } catch (ParseException e) {
            System.err.println(STR."DateTime parse error: \{e.getMessage()}");
        }
    }

    public void approveAppointment(Appointment appointment)
    {
        appointment.setStatus(Status.confirmed);
        try {
            appointment_repo = new AppointmentRepo();
            appointment_repo.update(appointment);
        } catch (SQLException e) {
            System.err.println(STR."Database error: \{e.getMessage()}");
        }
    }
}
