package com.example.mdsp.repos;

import com.example.mdsp.models.Role;
import com.example.mdsp.models.User;
import com.example.mdsp.exceptions.ElementNotFoundException;
import com.example.mdsp.exceptions.EmailAlreadyExistsException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class UserRepo implements AbstractRepo<User>
{
    protected static Connection connection;

    public UserRepo() {
        connection = DataBaseConnection.getConnection();
    }

    @Override
    public void AbstractAdd(User user) {
        String query = "INSERT INTO user (first_name, last_name, email, password_hash, role, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword_hash());
            statement.setString(5, user.getRole().toString());
            statement.setString(6, user.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int AbstractGetId(String email) {
        int user_id = -1;
        String query = "SELECT user_id FROM user WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user_id = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_id;
    }

    public String AbstractGetHashedPassword(String email) throws ElementNotFoundException {
        String query = "SELECT password_hash FROM user WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String password_hash = resultSet.getString("password_hash");
                return password_hash;
            }
            else {
                throw new ElementNotFoundException("Nu exista niciun cont creat pe " + email + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void AbstractUpdate(User user) {
        String query = "UPDATE user " +
                "SET email = ?, password_hash = ?, phone = ? " +
                "WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword_hash());
            statement.setString(3, user.getPhone());
            statement.setInt(4, user.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void AbstractDelete(int id) {
        String query = "DELETE FROM user WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AbstractIdVerification(String email) throws EmailAlreadyExistsException {
        String query = "SELECT user_id FROM user WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                throw new EmailAlreadyExistsException("Exista deja un cont creat cu emailul " + email + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Role AbstractGetRole(int user_id) throws ElementNotFoundException {
        String query = "SELECT role FROM user WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                if(role.equals("patient"))
                    return Role.PATIENT;
                else
                    return Role.DOCTOR;
            }
            else
                throw new ElementNotFoundException("User ID " + user_id + " inexistent.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}