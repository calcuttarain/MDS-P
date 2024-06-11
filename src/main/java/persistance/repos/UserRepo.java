package persistance.repos;

import business.models.Role;
import business.models.User;
import exceptions.ElementNotFoundException;
import exceptions.EmailAlreadyExistsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class UserRepo implements AbstractRepo<User>
{
    protected static Connection connection;
    protected static UserRepo instance;

    protected UserRepo() throws SQLException {
        connection = DataBaseConnection.getConnection();
    }

    public static UserRepo getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserRepo();
        }
        return instance;
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
                Role role = Role.valueOf(resultSet.getString("role"));
                return role;
            }
            else
                throw new ElementNotFoundException("User ID " + user_id + " inexistent.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}