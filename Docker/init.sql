-- Crearea bazei de date
CREATE DATABASE IF NOT EXISTS BD_MDS;
USE BD_MDS;

-- Tabelul pentru utilizatori
CREATE TABLE UTILIZATOR (
                            ID_Utilizator INT AUTO_INCREMENT PRIMARY KEY,
                            Nume VARCHAR(255),
                            Prenume VARCHAR(255),
                            Email VARCHAR(255) UNIQUE,
                            Hash_Parola VARCHAR(255),
                            Rol VARCHAR(255),
                            Telefon VARCHAR(255),
                            Data_Crearii DATE,
                            Ultima_Actualizare DATE
);

-- Tabelul pentru profilul pacientului
CREATE TABLE PACIENT (
                                ID_Pacient INT,
                                Istoric_Medical TEXT,
                                Alergii TEXT,
                                Grupa_Sange VARCHAR(255),
                                FOREIGN KEY (ID_Pacient) REFERENCES UTILIZATOR(ID_Utilizator)
);

-- Tabelul pentru feedback
CREATE TABLE FEEDBACK (
                          ID_Feedback INT AUTO_INCREMENT PRIMARY KEY,
                          ID_Utilizator INT,
                          Mesaj TEXT,
                          Data_Si_Ora DATETIME,
                          Stare VARCHAR(255),
                          FOREIGN KEY (ID_Utilizator) REFERENCES UTILIZATOR(ID_Utilizator)
);

-- Tabelul pentru profilul doctorului
CREATE TABLE DOCTOR (
                               ID_Doctor INT,
                               Specializare VARCHAR(255),
                               Descriere TEXT,
                               ID_Cabinet INT,
                               FOREIGN KEY (ID_Doctor) REFERENCES UTILIZATOR(ID_Utilizator),
                               FOREIGN KEY (ID_Cabinet) REFERENCES CABINET(ID_Cabinet)
);

-- Tabelul pentru cabinet
CREATE TABLE CABINET (
                         ID_Cabinet INT AUTO_INCREMENT PRIMARY KEY,
                         Nume VARCHAR(255),
                         Adresa VARCHAR(255),
                         Telefon VARCHAR(255),
                         Email VARCHAR(255)
);

-- Tabelul pentru programări
CREATE TABLE PROGRAMARE (
                            ID_Programare INT AUTO_INCREMENT PRIMARY KEY,
                            ID_Pacient INT,
                            ID_Doctor INT,
                            Data_Si_Ora DATETIME,
                            Stare VARCHAR(255),
                            Note TEXT,
                            FOREIGN KEY (ID_Pacient) REFERENCES UTILIZATOR(ID_Utilizator),
                            FOREIGN KEY (ID_Doctor) REFERENCES UTILIZATOR(ID_Utilizator)
);

-- Tabelul pentru sesiunea de chat
CREATE TABLE SESIUNE_CHAT (
                              ID_Sesiune INT AUTO_INCREMENT PRIMARY KEY,
                              ID_Utilizator INT,
                              Data_Inceperii DATETIME,
                              Data_Incheierii DATETIME,
                              Stare_Sesiune VARCHAR(255),
                              FOREIGN KEY (ID_Utilizator) REFERENCES UTILIZATOR(ID_Utilizator)
);

-- Tabelul pentru mesajele din chat
CREATE TABLE MESAJ_CHAT (
                            ID_Mesaj INT AUTO_INCREMENT PRIMARY KEY,
                            ID_Sesiune INT,
                            Directie VARCHAR(255),
                            Continut TEXT,
                            Data_Si_Ora DATETIME DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (ID_Sesiune) REFERENCES SESIUNE_CHAT(ID_Sesiune)
);

-- Tabelul pentru auditul de securitate
CREATE TABLE AUDIT_SECURITATE (
                                  ID_Audit INT AUTO_INCREMENT PRIMARY KEY,
                                  Tip VARCHAR(255),
                                  Descriere TEXT,
                                  Data DATETIME DEFAULT CURRENT_TIMESTAMP,
                                  Rezultat VARCHAR(255)
);

-- Tabelul pentru raportul de analiză
CREATE TABLE RAPORT_ANALIZA (
                                ID_Raport INT AUTO_INCREMENT PRIMARY KEY,
                                Tip_Raport VARCHAR(255),
                                Data_Generarii DATE,
                                Sumar TEXT,
                                Detalii TEXT
);
