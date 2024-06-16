CREATE DATABASE IF NOT EXISTS db_mds;
USE db_mds;

CREATE TABLE IF NOT EXISTS user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password_hash VARCHAR(255),
    role ENUM('doctor', 'patient') NOT NULL,
    phone VARCHAR(255),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS patient (
    patient_id INT PRIMARY KEY,
    medical_history TEXT,
    allergies TEXT,
    blood_type VARCHAR(255),
    FOREIGN KEY (patient_id) REFERENCES user(user_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS feedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    message TEXT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
    );

CREATE TABLE IF NOT EXISTS office (
    office_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS doctor (
    doctor_id INT PRIMARY KEY,
    specialization VARCHAR(255),
    description TEXT,
    office_id INT,
    FOREIGN KEY (doctor_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (office_id) REFERENCES office(office_id)
    );

CREATE TABLE IF NOT EXISTS appointment (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    appointment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('scheduled', 'canceled', 'completed', 'rescheduled', 'confirmed', 'unconfirmed') NOT NULL,
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES user(user_id),
    FOREIGN KEY (doctor_id) REFERENCES user(user_id)
    );

CREATE TABLE IF NOT EXISTS chat_session (
    session_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    start_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    end_timestamp DATETIME,
    session_status VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
    );

CREATE TABLE IF NOT EXISTS chat_message (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    session_id INT,
    direction VARCHAR(255),
    content TEXT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES chat_session(session_id)
    );

CREATE TABLE IF NOT EXISTS security_audit (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255),
    description TEXT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    result VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS analysis_report (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    report_type VARCHAR(255),
    generation_date DATE,
    summary TEXT,
    details TEXT
    );
INSERT INTO office (name, address, phone, email) VALUES
                                                     ('Cabinet Medical Dr. Popescu', 'Str. Mihai Eminescu, nr. 45, Bucuresti', '021-123-4567', 'contact@drpopescu.ro'),
                                                     ('Clinica Sanatatea Ta', 'Bd. Unirii, nr. 23, Cluj-Napoca', '0264-890-123', 'info@sanatateata.ro'),
                                                     ('Policlinica Sf. Maria', 'Str. Alexandru Ioan Cuza, nr. 10, Iasi', '0232-567-890', 'contact@sfmaria.ro'),
                                                     ('Centrul Medical MediPlus', 'Calea Victoriei, nr. 101, Timisoara', '0256-345-678', 'receptie@mediplus.ro'),
                                                     ('Spitalul Municipal Ploiesti', 'Str. Libertatii, nr. 8, Ploiesti', '0244-123-456', 'spital@municipalpl.ro');

