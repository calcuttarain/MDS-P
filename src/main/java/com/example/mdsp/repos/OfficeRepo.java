package com.example.mdsp.repos;

import com.example.mdsp.models.Office;
import com.example.mdsp.exceptions.ElementNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Component
public class OfficeRepo implements GenericRepo<Office> {
    private static Connection connection;

    public OfficeRepo() {
        connection = DataBaseConnection.getConnection();
    }

    @Override
    public void add(Office office) {
        String query = "INSERT INTO office (name, address, phone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, office.getName());
            statement.setString(2, office.getAddress());
            statement.setString(3, office.getPhone());
            statement.setString(4, office.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Office get(int office_id) throws ElementNotFoundException {
        String name = "";
        String address = "";
        String phone = "";
        String email = "";

        String query = "SELECT name, address, phone, email " +
                "FROM office " +
                "WHERE office_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, office_id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                phone = resultSet.getString("phone");
                email = resultSet.getString("email");
            }
            else {
                throw new ElementNotFoundException("Office ID " + office_id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Office(office_id, name, address, phone, email);
    }


    @Override
    public void update(Office office) {

        String query = "UPDATE office " +
                "SET name = ?, address = ?, phone = ?, email = ? " +
                "WHERE office_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(2, office.getName());
            statement.setString(2, office.getAddress());
            statement.setString(2, office.getPhone());
            statement.setString(3, office.getEmail());
            statement.setInt(4, office.getOfficeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int office_id) {
        String query = "DELETE FROM office WHERE office_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, office_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
