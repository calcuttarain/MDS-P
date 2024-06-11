package business.services;

import business.models.Role;
import business.models.User;
import exceptions.ElementNotFoundException;
import exceptions.EmailAlreadyExistsException;
import persistance.repos.UserRepo;

import java.sql.SQLException;

public abstract class UserService {
    private static UserRepo repo;

    private UserService() {}

    public static Role getRole(int user_id) throws ElementNotFoundException, SQLException {
        repo = UserRepo.getInstance();
        Role role = repo.AbstractGetRole(user_id);
        return role;
    }

    public static void changePassword(User user, String new_password) throws SQLException {
        String new_hashed_password = InputUtils.hashPassword(new_password);
        user.setPassword_hash(new_hashed_password);
        repo = UserRepo.getInstance();
        repo.AbstractUpdate(user);
    }

    public static void changeEmail(User user, String new_email) throws SQLException, EmailAlreadyExistsException {
        repo = UserRepo.getInstance();
        repo.AbstractIdVerification(new_email);
        user.setEmail(new_email);
        repo.AbstractUpdate(user);
    }

    public static void changePhone(User user, String new_phone) throws SQLException {
        repo = UserRepo.getInstance();
        user.setPhone(new_phone);
        repo.AbstractUpdate(user);
    }
}
