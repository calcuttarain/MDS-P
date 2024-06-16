package teste;
import java.sql.SQLException;
import java.util.List;

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

            RegisterService.createDoctorAccount("Alexandru", "Popescu", "alexandru.popescu@example.com", "parola123", "0722-123-456", "Cardiologie", "Specialist in cardiologie cu peste 10 ani de experienta", 6);
            RegisterService.createDoctorAccount("Bogdan", "Ionescu", "bogdan.ionescu@example.com", "parola123", "0733-234-567", "Dermatologie", "Dermatolog recunoscut pentru tratamente eficiente", 7);
            RegisterService.createDoctorAccount("Catalin", "Georgescu", "catalin.georgescu@example.com", "parola123", "0744-345-678", "Pediatrie", "Medic pediatru dedicat ingrijirii copiilor", 8);
            RegisterService.createDoctorAccount("Daniela", "Marinescu", "daniela.marinescu@example.com", "parola123", "0755-456-789", "Neurologie", "Experienta vasta in tratarea afectiunilor neurologice", 9);
            RegisterService.createDoctorAccount("Elena", "Dumitrescu", "elena.dumitrescu@example.com", "parola123", "0766-567-890", "Oftalmologie", "Oftalmolog expert in corectarea problemelor de vedere", 10);
//            AppointmentService.requestAppointment(15, 20, "2024 07 15 10:00", "Consultatie initiala pentru evaluare generala");
//            AppointmentService.requestAppointment(16, 21, "2024 08 20 14:30", "Control de rutina");
//            AppointmentService.requestAppointment(17, 22, "2024 09 05 09:00", "Consultatie pentru simptome acute");
//            AppointmentService.requestAppointment(18, 23, "2024 10 12 11:15", "Reevaluare dupa tratament");
//            AppointmentService.requestAppointment(19, 24, "2024 11 22 13:45", "Consultatie pentru investigatii suplimentare");
//            AppointmentService.requestAppointment(16, 22, "2024 12 01 15:00", "Verificare dupa operatie");

        RegisterService.createDoctorAccount("Ana", "Popa", "ana.popaaa@example.com", "parola123", "0722000000", "Pediatrie", "Specializat în îngrijirea copiilor și adolescenților.", 8);
        RegisterService.createDoctorAccount("Mihai", "Georgescu", "mihaiii.georgescu@example.com", "parola123", "0722000001", "Pediatrie", "Specializat în îngrijirea copiilor și adolescenților.", 6);
        RegisterService.createDoctorAccount("Elena", "Constantinescu", "elenaaa.constantinescu@example.com", "parola123", "0722000002", "Pediatrie", "Specializat în îngrijirea copiilor și adolescenților.", 10);
        } catch (SQLException e) {
            System.out.println("Nu exista conexiune la baza de date");
        } catch (EmailAlreadyExistsException e) {
            throw new RuntimeException(e);
        }


    }
}

