package business.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InputUtils {
    private InputUtils() {}
    public static boolean isValidEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public static boolean isValidName(String name) {
        RegexValidator nameValidator = new RegexValidator("^[A-Z][a-zA-Z]{2,49}$");
        return nameValidator.isValid(name);
    }

    public static boolean isValidPassword(String password) {
        RegexValidator passwordValidator = new RegexValidator("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        return passwordValidator.isValid(password);
    }

    public static boolean isValidPhone(String phone) {
        RegexValidator phoneValidator = new RegexValidator("^\\d{10,15}$");
        return phoneValidator.isValid(phone);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing password: " + e.getMessage());
            return null;
        }
    }
}
