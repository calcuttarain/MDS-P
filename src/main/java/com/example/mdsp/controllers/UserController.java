package com.example.mdsp.controllers;

import com.example.mdsp.exceptions.EmailAlreadyExistsException;
import com.example.mdsp.models.*;
import com.example.mdsp.services.*;
import com.example.mdsp.exceptions.ElementNotFoundException;
import com.example.mdsp.exceptions.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mds/api")
public class UserController {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final UserService userService;
    private User user;
    private int user_id;

    @Autowired
    public UserController(RegisterService registerService, LoginService loginService, UserService userService) {
        this.registerService = registerService;
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping("/user")
    public Map<String, Object> executeFunction(@RequestBody Map<String, Object> request) {
        String functionName = (String) request.get("function");
        Map<String, Object> parameters = (Map<String, Object>) request.get("parameters");

        Map<String, Object> response = new HashMap<>();
        try {
            switch (functionName) {
                case "createPacientAccount":
                    registerService.createPacientAccount(
                            (String) parameters.get("first_name"),
                            (String) parameters.get("last_name"),
                            (String) parameters.get("email"),
                            (String) parameters.get("password"),
                            (String) parameters.get("phone"),
                            (String) parameters.get("medical_history"),
                            (String) parameters.get("allergies"),
                            (String) parameters.get("blood_type")
                    );
                    response.put("result", "Patient account created successfully.");
                    break;
                case "createDoctorAccount":
                    registerService.createDoctorAccount(
                            (String) parameters.get("first_name"),
                            (String) parameters.get("last_name"),
                            (String) parameters.get("email"),
                            (String) parameters.get("password"),
                            (String) parameters.get("phone"),
                            (String) parameters.get("specialization"),
                            (String) parameters.get("description"),
                            (Integer) parameters.get("office_id")
                    );
                    response.put("result", "Doctor account created successfully.");
                    break;

                case "authenticate":
                    int userId = loginService.authenticate(
                            (String) parameters.get("email"),
                            (String) parameters.get("password")
                    );
                    Role role = UserService.getRole(userId);
                    if(role == Role.PATIENT) {
                        Patient patient = PatientService.getPatient(userId);
                        response.put("result", "Patient authenticated successfully.");
                        response.put("patient", patient);
                    }
                    else {
                        Doctor doctor = DoctorService.getDoctor(userId);
                        response.put("result", "Doctor authenticated successfully.");
                        response.put("doctor", doctor);
                    }
                    break;
                case "changePassword":
                    String userIdStr = (String) parameters.get("user_id");
                    user_id = Integer.parseInt(userIdStr);
                    String newPassword = (String) parameters.get("new_password");
                    userService.changePassword(user_id, newPassword);
                    response.put("result", "Password changed successfully.");
                    break;

                case "changeEmail":
                    String userIdStr1 = (String) parameters.get("user_id");
                    user_id = Integer.parseInt(userIdStr1);
                    String newEmail = (String) parameters.get("new_email");
                    userService.changeEmail(user_id, newEmail);
                    response.put("result", "Email changed successfully.");
                    break;
                case "changePhone":
                    String userIdStr2 = (String) parameters.get("user_id");
                    user_id = Integer.parseInt(userIdStr2);
                    user_id = (int) parameters.get("user_id");
                    String newPhone = (String) parameters.get("new_phone");
                    userService.changePhone(user_id, newPhone);
                    response.put("result", "Phone changed successfully.");
                    break;
                default:
                    response.put("error", "Unknown function: " + functionName);
            }
        } catch (SQLException e) {
            response.put("error", "A aparut o eroare: " + e.getMessage());
        } catch (ElementNotFoundException e) {
            response.put("error",e.getMessage());
        } catch (WrongPasswordException e) {
            response.put("error",e.getMessage());
        } catch (EmailAlreadyExistsException e) {
            response.put("error",e.getMessage());
        }

        return response;
    }
}
