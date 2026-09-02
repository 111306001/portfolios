CREATE DATABASE IF NOT EXISTS eeg_db_random;
USE eeg_db_random;

CREATE TABLE patients (
    patient_id VARCHAR(50) PRIMARY KEY,
    group_id ENUM('HC', 'MCI', 'AD') NOT NULL
);

CREATE TABLE origin_files (
    file_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id VARCHAR(50),
    filename VARCHAR(255) UNIQUE NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE
);

