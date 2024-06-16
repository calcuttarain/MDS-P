package teste;
import java.sql.SQLException;

import com.example.mdsp.exceptions.EmailAlreadyExistsException;
import com.example.mdsp.services.RegisterService;

public class Main {
    public static void main(String[] args) {
        try {
            RegisterService.createPacientAccount("Ion", "Popescu", "ion.popescu@example.com", "parola123", "0721-123-456", "Istoric de boli cardiace", "Polen, praf", "A+");
            RegisterService.createPacientAccount("Maria", "Ionescu", "maria.ionescu@example.com", "parola123", "0742-234-567", "Diabet", "Lapte", "B+");
            RegisterService.createPacientAccount("Andrei", "Georgescu", "andrei.georgescu@example.com", "parola123", "0731-345-678", "Astm", "Nuci", "O-");
            RegisterService.createPacientAccount("Elena", "Marinescu", "elena.marinescu@example.com", "parola123", "0753-456-789", "Hipertensiune", "Pisici", "AB+");
            RegisterService.createPacientAccount("Cristina", "Dumitrescu", "cristina.dumitrescu@example.com", "parola123", "0764-567-890", "Probleme tiroidiene", "Penicilina", "O+");

            RegisterService.createDoctorAccount("Alexandru", "Popescu", "alexandru.popescu@example.com", "parola123", "0722-123-456", "Cardiologie", "Specialist in cardiologie cu peste 10 ani de experienta", 1);
            RegisterService.createDoctorAccount("Bogdan", "Ionescu", "bogdan.ionescu@example.com", "parola123", "0733-234-567", "Dermatologie", "Dermatolog recunoscut pentru tratamente eficiente", 2);
            RegisterService.createDoctorAccount("Catalin", "Georgescu", "catalin.georgescu@example.com", "parola123", "0744-345-678", "Pediatrie", "Medic pediatru dedicat ingrijirii copiilor", 3);
            RegisterService.createDoctorAccount("Daniela", "Marinescu", "daniela.marinescu@example.com", "parola123", "0755-456-789", "Neurologie", "Experienta vasta in tratarea afectiunilor neurologice", 4);
            RegisterService.createDoctorAccount("Elena", "Dumitrescu", "elena.dumitrescu@example.com", "parola123", "0766-567-890", "Oftalmologie", "Oftalmolog expert in corectarea problemelor de vedere", 5);

        RegisterService.createDoctorAccount("Ana", "Popa", "ana.popaaa@example.com", "parola123", "0722000000", "Pediatrie", "Specializat în îngrijirea copiilor și adolescenților.", 1);
        RegisterService.createDoctorAccount("Mihai", "Georgescu", "mihaiii.georgescu@example.com", "parola123", "0722000001", "Pediatrie", "Specializat în îngrijirea copiilor și adolescenților.", 3);
        RegisterService.createDoctorAccount("Elena", "Constantinescu", "elenaaa.constantinescu@example.com", "parola123", "0722000002", "Pediatrie", "Specializat în îngrijirea copiilor și adolescenților.", 5);
        } catch (SQLException e) {
            System.out.println("Nu exista conexiune la baza de date");
        } catch (EmailAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
    }
}

