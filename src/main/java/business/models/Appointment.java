package business.models;

import java.util.Date;

public final class Appointment {
    private final int appointment_id;
    private final int patient_id;
    private final int doctor_id;
    private Date appointment_date;
    private Status status;
    private String notes;

    public Appointment(int appointment_id, int patient_id, int doctor_id, Date appointment_date, String status, String notes) {
        this.appointment_id = appointment_id;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.appointment_date = appointment_date;
        try {
            this.status = Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        this.notes = notes;
    }

    public int getAppointmentId() {
        return appointment_id;
    }

    public int getPatientId() {
        return patient_id;
    }

    public int getDoctorId() {
        return doctor_id;
    }

    public Date getAppointmentDate() {
        return appointment_date;
    }

    public void setAppointmentDate(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
