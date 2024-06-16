package com.example.mdsp.services;

import com.example.mdsp.exceptions.ElementNotFoundException;
import com.example.mdsp.exceptions.WrongPasswordException;
import com.example.mdsp.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class LoginService {
    private static UserRepo repo = new UserRepo();

    public LoginService() {}
    public static int authenticate(String email, String password) throws ElementNotFoundException, WrongPasswordException {
        String real_password_hash = repo.AbstractGetHashedPassword(email);
        String input_password_hash = InputUtils.hashPassword(password);
        if(real_password_hash.compareTo(input_password_hash) == 0)
            return repo.AbstractGetId(email);
        else
            throw new WrongPasswordException("Parola gresita pentru utilizatorul cu emailul " + email + ".");
    }
}
