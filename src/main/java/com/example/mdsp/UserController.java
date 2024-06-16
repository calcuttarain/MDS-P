package com.example.mdsp;

import com.example.mdsp.exceptions.EmailAlreadyExistsException;
import com.example.mdsp.services.RegisterService;
import com.example.mdsp.services.LoginService;
import com.example.mdsp.exceptions.ElementNotFoundException;
import com.example.mdsp.exceptions.WrongPasswordException;
import com.example.mdsp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MyController {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final UserService userService;

    @Autowired
    public MyController(RegisterService registerService, LoginService loginService, UserService userService) {
        this.registerService = registerService;
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping("/execute")
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
                    response.put("result", "User authenticated successfully.");
                    response.put("user_id", userId);
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
