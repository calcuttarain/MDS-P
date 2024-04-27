CREATE DATABASE IF NOT EXISTS DB_MDS;
USE DB_MDS;

CREATE TABLE IF NOT EXISTS USER (
                                    User_ID INT AUTO_INCREMENT PRIMARY KEY,
                                    First_Name VARCHAR(255),
                                    Last_Name VARCHAR(255),
                                    Email VARCHAR(255) UNIQUE,
                                    Password_Hash VARCHAR(255),
                                    Role ENUM('doctor', 'patient') NOT NULL,
                                    Phone VARCHAR(255),
                                    Creation_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    Last_Update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS PATIENT (
                                       Patient_ID INT,
                                       Medical_History TEXT,
                                       Allergies TEXT,
                                       Blood_Type VARCHAR(255),
                                       FOREIGN KEY (Patient_ID) REFERENCES USER(User_ID)
);

CREATE TABLE IF NOT EXISTS FEEDBACK (
                                        Feedback_ID INT AUTO_INCREMENT PRIMARY KEY,
                                        User_ID INT,
                                        Message TEXT,
                                        Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                                        Status VARCHAR(255),
                                        FOREIGN KEY (User_ID) REFERENCES USER(User_ID)
);

CREATE TABLE IF NOT EXISTS OFFICE (
                                      Office_ID INT AUTO_INCREMENT PRIMARY KEY,
                                      Name VARCHAR(255),
                                      Address VARCHAR(255),
                                      Phone VARCHAR(255),
                                      Email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS DOCTOR (
                                      Doctor_ID INT,
                                      Specialization VARCHAR(255),
                                      Description TEXT,
                                      Office_ID INT,
                                      FOREIGN KEY (Doctor_ID) REFERENCES USER(User_ID),
                                      FOREIGN KEY (Office_ID) REFERENCES OFFICE(Office_ID)
);

CREATE TABLE IF NOT EXISTS APPOINTMENT (
                                           Appointment_ID INT AUTO_INCREMENT PRIMARY KEY,
                                           Patient_ID INT,
                                           Doctor_ID INT,
                                           Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                                           Status VARCHAR(255),
                                           Notes TEXT,
                                           FOREIGN KEY (Patient_ID) REFERENCES USER(User_ID),
                                           FOREIGN KEY (Doctor_ID) REFERENCES USER(User_ID)
);

CREATE TABLE IF NOT EXISTS CHAT_SESSION (
                                            Session_ID INT AUTO_INCREMENT PRIMARY KEY,
                                            User_ID INT,
                                            Start_Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                                            End_Timestamp DATETIME,
                                            Session_Status VARCHAR(255),
                                            FOREIGN KEY (User_ID) REFERENCES USER(User_ID)
);

CREATE TABLE IF NOT EXISTS CHAT_MESSAGE (
                                            Message_ID INT AUTO_INCREMENT PRIMARY KEY,
                                            Session_ID INT,
                                            Direction VARCHAR(255),
                                            Content TEXT,
                                            Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                                            FOREIGN KEY (Session_ID) REFERENCES CHAT_SESSION(Session_ID)
);

CREATE TABLE IF NOT EXISTS SECURITY_AUDIT (
                                              Audit_ID INT AUTO_INCREMENT PRIMARY KEY,
                                              Type VARCHAR(255),
                                              Description TEXT,
                                              Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                                              Result VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ANALYSIS_REPORT (
                                               Report_ID INT AUTO_INCREMENT PRIMARY KEY,
                                               Report_Type VARCHAR(255),
                                               Generation_Date DATE,
                                               Summary TEXT,
                                               Details TEXT
);
