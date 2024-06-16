package com.example.mdsp.services;

import com.example.mdsp.models.Patient;
import com.example.mdsp.models.Role;
import com.example.mdsp.models.User;
import com.example.mdsp.exceptions.ElementNotFoundException;
import com.example.mdsp.exceptions.EmailAlreadyExistsException;
import com.example.mdsp.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static UserRepo repo = new UserRepo();

    public UserService() {}

    public static Role getRole(int user_id) throws ElementNotFoundException {
        Role role = repo.AbstractGetRole(user_id);
        return role;
    }

    public static void changePassword(int userId, String new_password) throws ElementNotFoundException {
        User user;
        if(repo.AbstractGetRole(userId) == Role.PATIENT)
            user = PatientService.getPatient(userId);
        else
            user = DoctorService.getDoctor(userId);
        String new_hashed_password = InputUtils.hashPassword(new_password);
        user.setPassword_hash(new_hashed_password);
        repo.AbstractUpdate(user);
    }

    public static void changeEmail(int userId, String new_email) throws EmailAlreadyExistsException, ElementNotFoundException {
        User user;
        if(repo.AbstractGetRole(userId) == Role.PATIENT)
            user = PatientService.getPatient(userId);
        else
            user = DoctorService.getDoctor(userId);
        repo.AbstractIdVerification(new_email);
        user.setEmail(new_email);
        repo.AbstractUpdate(user);
    }

    public static void changePhone(int userId, String new_phone) throws ElementNotFoundException {
        User user;
        if(repo.AbstractGetRole(userId) == Role.PATIENT)
            user = PatientService.getPatient(userId);
        else
            user = DoctorService.getDoctor(userId);
        user.setPhone(new_phone);
        repo.AbstractUpdate(user);
    }
}
