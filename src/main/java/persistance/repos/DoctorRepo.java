package persistance.repos;

import business.models.Doctor;
import business.models.Role;
import exceptions.ElementNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorRepo extends UserRepo implements GenericRepo<Doctor>{
    private static DoctorRepo instance;
    private DoctorRepo() throws SQLException {}

    public static DoctorRepo getInstance() throws SQLException {
        if (instance == null) {
            instance = new DoctorRepo();
        }
        return instance;
    }

    @Override
    public void add(Doctor doctor) {
        super.AbstractAdd(doctor);
        int doctor_id = AbstractGetId(doctor.getEmail());
        String query = "INSERT INTO doctor (doctor_id, specialization, description, office_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctor_id);
            statement.setString(2, doctor.getSpecialization());
            statement.setString(3, doctor.getDescription());
            statement.setInt(4, doctor.getOfficeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor get(int doctor_id) throws ElementNotFoundException {
        String first_name = "";
        String last_name = "";
        String email = "";
        String password_hash = "";
        String role = "";
        String phone = "";
        String specialization = "";
        String description = "";
        int office_id = -1;

        String query = "SELECT u.first_name, u.last_name, u.email, u.password_hash, u.role, u.phone, " +
                "d.specialization, d.description, d.office_id " +
                "FROM user u " +
                "LEFT JOIN doctor d ON u.id = d.user_id " +
                "WHERE u.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, doctor_id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                first_name = resultSet.getString("first_name");
                last_name = resultSet.getString("last_name");
                email = resultSet.getString("email");
                password_hash = resultSet.getString("password_hash");
                role = resultSet.getString("role");
                phone = resultSet.getString("phone");
                specialization = resultSet.getString("specialization");
                description = resultSet.getString("description");
                office_id = resultSet.getInt("office_id");
            }
            else {
                throw new ElementNotFoundException("Doctor ID " + doctor_id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Doctor(doctor_id, first_name, last_name, email, password_hash, Role.DOCTOR, phone, specialization, description, office_id);
    }


    @Override
    public void update(Doctor doctor) {
        super.AbstractUpdate(doctor);

        String query = "UPDATE doctor " +
                "SET specialization = ?, description = ?, office_id = ? " +
                "WHERE doctor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getSpecialization());
            statement.setString(2, doctor.getDescription());
            statement.setInt(3, doctor.getOfficeId());
            statement.setInt(4, doctor.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int doctor_id) {
        String query = "DELETE FROM doctor WHERE doctor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctor_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
