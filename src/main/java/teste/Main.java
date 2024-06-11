package teste;
import java.sql.SQLException;
import java.util.Scanner;

import business.services.LoginService;
import business.services.RegisterService;
import exceptions.ElementNotFoundException;
import exceptions.WrongPasswordException;

public class Main {
    public static void main(String[] args) {
//        try {
//            RegisterService.createPacientAccount("Gigi", "Petrica", "gigi@gmail.ro", "gigi123", "09778990", "prost", "prosti", "a5");
//        } catch (SQLException e) {
//            System.out.println("Nu exista conexiune la baza de date");
//        }
        try {
            System.out.println(LoginService.authenticate("gigi@gmail.ro", "gigi1234"));
        } catch (SQLException e) {
            System.out.println("Nu exista conexiune la baza de date");
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
    }
}

