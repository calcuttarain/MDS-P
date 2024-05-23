package persistance.repos;

import business.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo implements AbstractRepo<User>
{
    protected final Connection connection;

    public UserRepo() throws SQLException {
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
            statement.setString(5, user.getRole());
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
}