USE eeg_db_random;

CREATE TABLE Alpha_band (
    feature_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id VARCHAR(50),
    file_id INT,
    average_power DOUBLE NOT NULL,
    PAF DOUBLE,
    CAF DOUBLE,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (file_id) REFERENCES origin_files(file_id) ON DELETE CASCADE
);
