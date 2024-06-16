package com.example.mdsp.repos;

import com.example.mdsp.models.Patient;
import com.example.mdsp.models.Role;
import com.example.mdsp.exceptions.ElementNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PatientRepo extends UserRepo implements GenericRepo<Patient>  {
    public PatientRepo() {}
    @Override
    public void add(Patient patient) {
        super.AbstractAdd(patient);
        int patient_id = AbstractGetId(patient.getEmail());
        String query = "INSERT INTO patient (patient_id, medical_history, allergies, blood_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patient_id);
            statement.setString(2, patient.getMedicalHistory());
            statement.setString(3, patient.getAllergies());
            statement.setString(4, patient.getBloodType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient get(int patient_id) throws ElementNotFoundException {
        String first_name = "";
        String last_name = "";
        String email = "";
        String password_hash = "";
        String role = "";
        String phone = "";
        String medical_history = "";
        String allergies = "";
        String blood_type = "";

        String query = "SELECT first_name, last_name, email, password_hash, role, phone, " +
                "medical_history, allergies, blood_type " +
                "FROM user " +
                "LEFT JOIN patient ON user_id = patient_id " +
                "WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patient_id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                first_name = resultSet.getString("first_name");
                last_name = resultSet.getString("last_name");
                email = resultSet.getString("email");
                password_hash = resultSet.getString("password_hash");
                role = resultSet.getString("role");
                phone = resultSet.getString("phone");
                medical_history = resultSet.getString("medical_history");
                allergies = resultSet.getString("allergies");
                blood_type = resultSet.getString("blood_type");
            }
            else {
                throw new ElementNotFoundException("Pacient ID " + patient_id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Patient(patient_id, first_name, last_name, email, password_hash, Role.PATIENT , phone, medical_history, allergies, blood_type);
    }

    @Override
    public void update(Patient patient) {
        super.AbstractUpdate(patient);

        String query = "UPDATE patient " +
                "SET medical_history = ?, allergies = ? " +
                "WHERE patient_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getMedicalHistory());
            statement.setString(2, patient.getAllergies());
            statement.setInt(3, patient.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int patient_id) {
        AbstractDelete(patient_id);
        String query = "DELETE FROM patient WHERE patient_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patient_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
