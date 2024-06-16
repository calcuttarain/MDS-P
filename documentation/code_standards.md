

# Standarde de Cod

Proiectul MDS-P respectă următoarele standarde de cod pentru a asigura o implementare consistentă și ușor de întreținut:

### Structură Organizată a Pachetelor

- **Controllers**: Controlerele precum `PatientController`, `UserController` sunt plasate în pachetul `com.example.mdsp.controllers`, pentru gestionarea cererilor HTTP și răspunsurilor corespunzătoare.

- **Exceptions**: Clasele de excepții cum ar fi `ElementNotFoundException`, `EmailAlreadyExistsException` sunt definite în pachetul `com.example.mdsp.exceptions`, pentru gestionarea erorilor specifice aplicației.

- **Models**: Modelele cum ar fi `User`, `Patient`, `Doctor`, `Appointment`, `Office` sunt definite în pachetul `com.example.mdsp.models`, pentru reprezentarea entităților de date și logica asociată acestora.

- **Repositories**: Interfețele și implementările repository-urilor, cum ar fi `UserRepo`, `PatientRepo`, sunt organizate în pachetul `com.example.mdsp.repos`, pentru accesul la date și operațiile CRUD asociate entităților.

- **Services**: Serviciile precum `UserService`, `PatientService`, `DoctorService`, `AppointmentService` sunt definite în pachetul `com.example.mdsp.services`, pentru gestionarea logicii de business și a operațiunilor complexe.

### Utilizarea Corectă a Spring Framework

- Folosirea adecvată a adnotărilor Spring (`@RestController`, `@RequestMapping`, `@Autowired`, `@Service`, `@ComponentScan`) pentru gestionarea injecțiilor de dependențe și configurarea corectă a bean-urilor.

### Tratarea Excepțiilor

- Folosirea excepțiilor specifice (`ElementNotFoundException`, `EmailAlreadyExistsException`, etc.) pentru semnalarea și gestionarea erorilor specifice aplicației într-un mod clar și consistent.

### Respectarea Convențiilor de Denumire

- Denumirea clară și consistentă a claselor, metodelor și variabilelor conform convențiilor Java pentru o citire și înțelegere ușoară a codului.

### Documentarea Corespunzătoare

- Adăugarea de comentarii explicative și documentare adecvată a claselor și metodelor pentru a facilita înțelegerea funcționalităților și interacțiunilor din cadrul proiectului.

