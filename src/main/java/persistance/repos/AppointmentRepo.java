package persistance.repos;

import business.models.Appointment;
import exceptions.ElementNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepo implements GenericRepo<Appointment> {
    private static Connection connection;
    private static AppointmentRepo instance;

    private AppointmentRepo() throws SQLException {
        connection = DataBaseConnection.getConnection();
    }

    public static AppointmentRepo getInstance() throws SQLException {
        if (instance == null) {
            instance = new AppointmentRepo();
        }
        return instance;
    }
    @Override
    public void add(Appointment appointment) {
        String query = "INSERT INTO appointment (patient_id, doctor_id, appointment_date, status, notes) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            Timestamp appointment_date = new Timestamp(appointment.getAppointmentDate().getTime());
            statement.setTimestamp(3, appointment_date);
            statement.setString(4, appointment.getStatus());
            statement.setString(5, appointment.getNotes());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Appointment get(int appointment_id) throws ElementNotFoundException {
        int patient_id = -1;
        int doctor_id = -1;
        Date appointment_date = null;
        String status = "";
        String notes = "";

        String query = "SELECT patient_id, doctor_id, appointment_date, status, notes " +
                "FROM appointment " +
                "WHERE appointment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointment_id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                patient_id = resultSet.getInt("patient_id");
                doctor_id = resultSet.getInt("doctor_id");
                Timestamp date = resultSet.getTimestamp("appointment_date");
                appointment_date = new Date(date.getTime());
                status = resultSet.getString("status");
                notes = resultSet.getString("notes");
            }
            else {
                throw new ElementNotFoundException("Appointment ID " + appointment_id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String statusUpperCase = status.toUpperCase();
        return new Appointment(appointment_id, patient_id, doctor_id, appointment_date, statusUpperCase, notes);
    }

    @Override
    public void update(Appointment appointment) {

        String query = "UPDATE appointment " +
                "SET appointment_date = ?, status = ?, notes = ? " +
                "WHERE appointment_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            Timestamp appointment_date = new Timestamp(appointment.getAppointmentDate().getTime());
            statement.setTimestamp(1, appointment_date);
            statement.setString(2, appointment.getStatus());
            statement.setString(3, appointment.getNotes());
            statement.setInt(4, appointment.getAppointmentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int appointment_id) {
        String query = "DELETE FROM appointment WHERE appointment_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointment_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> getAppointmentsByDoctorId(int doctor_id) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT appointment_id, patient_id, appointment_date, status, notes " +
                "FROM appointment WHERE doctor_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctor_id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int appointment_id = resultSet.getInt("appointment_id");
                int patient_id = resultSet.getInt("patient_id");
                Timestamp date = resultSet.getTimestamp("appointment_date");
                Date appointment_date = new Date(date.getTime());
                String status = resultSet.getString("status");
                String notes = resultSet.getString("notes");
                String statusUpperCase = status.toUpperCase();
                appointments.add(new Appointment(appointment_id, patient_id, doctor_id, appointment_date, statusUpperCase, notes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public List<Appointment> getAppointmentsByPatientId(int patient_id) {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT appointment_id, doctor_id, appointment_date, status, notes " +
                "FROM appointment WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patient_id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int appointment_id = resultSet.getInt("appointment_id");
                int doctor_id = resultSet.getInt("doctor_id");
                Timestamp date = resultSet.getTimestamp("appointment_date");
                Date appointment_date = new Date(date.getTime());
                String status = resultSet.getString("status");
                String notes = resultSet.getString("notes");
                String statusUpperCase = status.toUpperCase();
                appointments.add(new Appointment(appointment_id, patient_id, doctor_id, appointment_date, statusUpperCase, notes));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

}
