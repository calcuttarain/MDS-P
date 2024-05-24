package teste;
import java.util.Scanner;
import business.services.RegisterService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

//        System.out.print("Enter your med history: ");
//        String history = scanner.nextLine();
//
//        System.out.print("Enter your allergies: ");
//        String alerg = scanner.nextLine();
//
//        System.out.print("Enter your blood type: ");
//        String bt = scanner.nextLine();

        System.out.print("Enter your specialization: ");
        String spec = scanner.nextLine();

        System.out.print("Enter your description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter your office: ");
        int of = Integer.parseInt(scanner.nextLine());

        RegisterService.createDoctorAccount(firstName, lastName, email, password, phone, spec, desc, of);
    }
}

