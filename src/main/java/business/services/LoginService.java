package business.services;

import exceptions.ElementNotFoundException;
import exceptions.WrongPasswordException;
import persistance.repos.UserRepo;

import java.sql.SQLException;

public final class LoginService {
    private LoginService() {}
    public static int authenticate(String email, String password) throws SQLException, ElementNotFoundException, WrongPasswordException {
        UserRepo repo = UserRepo.getInstance();
        String real_password_hash = repo.AbstractGetHashedPassword(email);
        String input_password_hash = InputUtils.hashPassword(password);
        if(real_password_hash.compareTo(input_password_hash) == 0)
            return repo.AbstractGetId(email);
        else
            throw new WrongPasswordException("Parola gresita pentru utilizatorul cu emailul " + email + ".");
    }
}
