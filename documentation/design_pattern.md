# Design Pattern: Model-View-Controller (MVC)

Aplicația utilizează design pattern-ul Model-View-Controller (MVC) pentru a organiza și structura componentele sale. Design pattern-ul MVC este împărțit în următoarele componente principale:

### Model

Modelele în aplicația MDS-P reprezintă datele și logica de business asociată entităților definite în sistem. Fiecare model, cum ar fi `User`, `Patient`, `Doctor`, `Appointment`, `Office`, conține atribute specifice și metode pentru a accesa, actualiza sau manipula datele respective. Modelele sunt utilizate în mod obișnuit în servicii și repository-uri pentru a gestiona persistența și operațiunile CRUD.

### Controller

Controller-ele din MDS-P acționează ca puncte de intrare pentru cererile utilizatorilor și asigură rutarea acestora către logica corespunzătoare de business. Fiecare controller, cum ar fi `PatientController`, `UserController`, expune endpoint-uri HTTP pentru diferite operațiuni (de exemplu, crearea unui pacient, autentificarea unui utilizator). Controller-ele pregătesc datele pentru a fi procesate de servicii și modele, apoi returnează răspunsuri adecvate către client.

### Repos și Services

Persistența datelor în MDS-P este gestionată prin intermediul repository-urilor (`UserRepo`, `PatientRepo`, `DoctorRepo`, `AppointmentRepo`, `OfficeRepo`) și serviciilor asociate (`UserService`, `PatientService`, `DoctorService`, `AppointmentService`). Repository-urile sunt responsabile pentru interacțiunea directă cu baza de date sau alt sistem de stocare persistent, asigurând operațiuni de salvare, actualizare, ștergere și interogare a datelor. Serviciile conțin logica de business specifică aplicației și coordonează operațiile între controller-e, modele și repository-uri.

Această structură MVC ajută la separarea clară a responsabilităților în aplicație, facilitând dezvoltarea, testarea și mentenanța pe termen lung a codului.


## Structura Proiectului

```plaintext
MDS-P/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── example/
│   │   │   │   │   ├── mdsp/
│   │   │   │   │   │   ├── controllers/
│   │   │   │   │   │   │   ├── PatientController.java
│   │   │   │   │   │   │   ├── UserController.java
│   │   │   │   │   │   ├── exceptions/
│   │   │   │   │   │   │   ├── ElementNotFoundException.java
│   │   │   │   │   │   │   ├── EmailAlreadyExistsException.java
│   │   │   │   │   │   │   └── WrongPasswordException.java
│   │   │   │   │   │   ├── models/
│   │   │   │   │   │   │   ├── User.java
│   │   │   │   │   │   │   ├── Patient.java
│   │   │   │   │   │   │   ├── Doctor.java
│   │   │   │   │   │   │   ├── Appointment.java
│   │   │   │   │   │   │   ├── Office.java
│   │   │   │   │   │   ├── repos/
│   │   │   │   │   │   │   ├── UserRepo.java
│   │   │   │   │   │   │   ├── PatientRepo.java
│   │   │   │   │   │   │   ├── DoctorRepo.java
│   │   │   │   │   │   │   ├── AppointmentRepo.java
│   │   │   │   │   │   │   ├── OfficeRepo.java
│   │   │   │   │   │   ├── services/
│   │   │   │   │   │   │   ├── UserService.java
│   │   │   │   │   │   │   ├── PatientService.java
│   │   │   │   │   │   │   ├── DoctorService.java
│   │   │   │   │   │   │   ├── AppointmentService.java
│   │   │   │   │   │   │   ├── RegisterService.java
│   │   │   │   │   │   │   └── LoginService.java
```
## Motivația Utilizării MVC

Aplicația a adoptat design pattern-ul MVC pentru a îmbunătăți structura și organizarea codului. Principalele motive includ:

- **Separarea responsabilităților**: MVC permite separarea clară a componentelor aplicației, ceea ce duce la un cod mai ușor de gestionat și de întreținut.

- **Reutilizarea codului**: Modelele și serviciile sunt independente de interfața utilizatorului, facilitând reutilizarea și extensibilitatea codului.

- **Flexibilitate și scalabilitate**: Structura MVC permite adăugarea ușoară de noi funcționalități fără a afecta alte părți ale aplicației.