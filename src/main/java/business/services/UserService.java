package business.services;

import business.models.User;
import exceptions.EmailAlreadyExistsException;
import persistance.repos.UserRepo;

import java.sql.SQLException;

public abstract class UserService {
    private static UserRepo repo;

    private UserService() {}

    public void changePassword(User user, String new_password) throws SQLException {
        String new_hashed_password = InputUtils.hashPassword(new_password);
        user.setPassword_hash(new_hashed_password);
        this.repo = UserRepo.getInstance();
        repo.AbstractUpdate(user);
    }

    public void changeEmail(User user, String new_email) throws SQLException, EmailAlreadyExistsException {
        this.repo = UserRepo.getInstance();
        repo.AbstractIdVerification(new_email);
        user.setEmail(new_email);
        repo.AbstractUpdate(user);
    }

    public void changePhone(User user, String new_phone) throws SQLException {
        this.repo = UserRepo.getInstance();
        user.setPhone(new_phone);
        repo.AbstractUpdate(user);
    }
}
