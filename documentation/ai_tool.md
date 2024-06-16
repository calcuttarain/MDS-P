# Utilizarea ChatGPT în Proiectul MDS-P

## Contribuția ChatGPT

Pentru dezvoltarea proiectului MDS-P, am utilizat ChatGPT în mai multe aspecte importante:

### Crearea Bazei de Date

ChatGPT a fost folosit pentru:
- Designul și structura bazei de date.
- Generarea scripturilor SQL pentru crearea tabelelor.
- Crearea mai multor inserari de date (insert statements) pentru popularea inițială a bazei de date.

### Generarea Inserțiilor de Date

Utilizarea ChatGPT a facilitat generarea unor inserari în baza de date, asigurând o populare rapidă și corectă a tabelelor cu date necesare pentru testare și dezvoltare.

```sql
INSERT INTO office (name, address, phone, email) VALUES
                                                     ('Cabinet Medical Dr. Popescu', 'Str. Mihai Eminescu, nr. 45, Bucuresti', '021-123-4567', 'contact@drpopescu.ro'),
                                                     ('Clinica Sanatatea Ta', 'Bd. Unirii, nr. 23, Cluj-Napoca', '0264-890-123', 'info@sanatateata.ro'),
                                                     ('Policlinica Sf. Maria', 'Str. Alexandru Ioan Cuza, nr. 10, Iasi', '0232-567-890', 'contact@sfmaria.ro'),
                                                     ('Centrul Medical MediPlus', 'Calea Victoriei, nr. 101, Timisoara', '0256-345-678', 'receptie@mediplus.ro'),
                                                     ('Spitalul Municipal Ploiesti', 'Str. Libertatii, nr. 8, Ploiesti', '0244-123-456', 'spital@municipalpl.ro');

INSERT INTO user (first_name, last_name, email, password_hash, role, phone) VALUES
                                                                                ('Ion', 'Popescu', 'ion.popescu@example.com', 'parola123', 'patient', '0721-123-456'),
                                                                                ('Maria', 'Ionescu', 'maria.ionescu@example.com', 'parola123', 'patient', '0742-234-567'),
                                                                                ('Andrei', 'Georgescu', 'andrei.georgescu@example.com', 'parola123', 'patient', '0731-345-678'),
                                                                                ('Elena', 'Marinescu', 'elena.marinescu@example.com', 'parola123', 'patient', '0753-456-789'),
                                                                                ('Cristina', 'Dumitrescu', 'cristina.dumitrescu@example.com', 'parola123', 'patient', '0764-567-890'),
                                                                                ('Alexandru', 'Popescu', 'alexandru.popescu@example.com', 'parola123', 'doctor', '0722-123-456'),
                                                                                ('Bogdan', 'Ionescu', 'bogdan.ionescu@example.com', 'parola123', 'doctor', '0733-234-567'),
                                                                                ('Catalin', 'Georgescu', 'catalin.georgescu@example.com', 'parola123', 'doctor', '0744-345-678'),
                                                                                ('Daniela', 'Marinescu', 'daniela.marinescu@example.com', 'parola123', 'doctor', '0755-456-789'),
                                                                                ('Elena', 'Dumitrescu', 'elena.dumitrescu@example.com', 'parola123', 'doctor', '0766-567-890'),
                                                                                ('Ana', 'Popa', 'ana.popaaa@example.com', 'parola123', 'doctor', '0722000000'),
                                                                                ('Mihai', 'Georgescu', 'mihaiii.georgescu@example.com', 'parola123', 'doctor', '0722000001'),
                                                                                ('Elena', 'Constantinescu', 'elenaaa.constantinescu@example.com', 'parola123', 'doctor', '0722000002');

-- Populate patient table
INSERT INTO patient (patient_id, medical_history, allergies, blood_type) VALUES
                                                                             ((SELECT user_id FROM user WHERE email = 'ion.popescu@example.com'), 'Istoric de boli cardiace', 'Polen, praf', 'A+'),
                                                                             ((SELECT user_id FROM user WHERE email = 'maria.ionescu@example.com'), 'Diabet', 'Lapte', 'B+'),
                                                                             ((SELECT user_id FROM user WHERE email = 'andrei.georgescu@example.com'), 'Astm', 'Nuci', 'O-'),
                                                                             ((SELECT user_id FROM user WHERE email = 'elena.marinescu@example.com'), 'Hipertensiune', 'Pisici', 'AB+'),
                                                                             ((SELECT user_id FROM user WHERE email = 'cristina.dumitrescu@example.com'), 'Probleme tiroidiene', 'Penicilina', 'O+');

-- Populate doctor table
INSERT INTO doctor (doctor_id, specialization, description, office_id) VALUES
                                                                           ((SELECT user_id FROM user WHERE email = 'alexandru.popescu@example.com'), 'Cardiologie', 'Specialist in cardiologie cu peste 10 ani de experienta', 1),
                                                                           ((SELECT user_id FROM user WHERE email = 'bogdan.ionescu@example.com'), 'Dermatologie', 'Dermatolog recunoscut pentru tratamente eficiente', 2),
                                                                           ((SELECT user_id FROM user WHERE email = 'catalin.georgescu@example.com'), 'Pediatrie', 'Medic pediatru dedicat ingrijirii copiilor', 3),
                                                                           ((SELECT user_id FROM user WHERE email = 'daniela.marinescu@example.com'), 'Neurologie', 'Experienta vasta in tratarea afectiunilor neurologice', 4),
                                                                           ((SELECT user_id FROM user WHERE email = 'elena.dumitrescu@example.com'), 'Oftalmologie', 'Oftalmolog expert in corectarea problemelor de vedere', 5),
                                                                           ((SELECT user_id FROM user WHERE email = 'ana.popaaa@example.com'), 'Pediatrie', 'Specializat în îngrijirea copiilor și adolescenților.', 1),
                                                                           ((SELECT user_id FROM user WHERE email = 'mihaiii.georgescu@example.com'), 'Pediatrie', 'Specializat în îngrijirea copiilor și adolescenților.', 3),
                                                                           ((SELECT user_id FROM user WHERE email = 'elenaaa.constantinescu@example.com'), 'Pediatrie', 'Specializat în îngrijirea copiilor și adolescenților.', 5);
```
### Crearea Metodelor PostMapping în Controllere

ChatGPT a fost de ajutor în:
- Scrierea metodelor `@PostMapping` în controllere, care gestionează cererile HTTP POST.
- Implementarea logicii de business pentru diferite funcționalități ale aplicației, cum ar fi autentificarea utilizatorilor, crearea conturilor de pacient și doctor, gestionarea istoricului medical și programarea consultațiilor.

### Beneficii

Utilizarea ChatGPT a adus multiple beneficii proiectului:
- Reducerea timpului de dezvoltare prin automatizarea generării codului.
- Asigurarea unei structuri coerente și consistente a codului.
- Minimizarea erorilor umane în scrierea scripturilor SQL și a logicii de business.

## Concluzie

Integrarea ChatGPT în procesul de dezvoltare a proiectului MDS-P a fost esențială pentru realizarea rapidă și eficientă a unor componente critice ale aplicației. Aceasta a permis echipei de dezvoltare să se concentreze pe aspectele mai complexe și inovative ale proiectului, beneficiind de suportul automatizat al ChatGPT pentru sarcinile repetitive și consumatoare de timp.
