USE eeg_db_random;

CREATE TABLE HFD (
    hfd_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id VARCHAR(50),
    file_id INT,

    Fp1_F3 DOUBLE, 
    F3_C3 DOUBLE, 
    C3_P3 DOUBLE, 
    P3_O1 DOUBLE, 
    Fp2_F4 DOUBLE, 
    F4_C4 DOUBLE, 
    C4_P4 DOUBLE, 
    P4_O2 DOUBLE, 
    Fp1_F7 DOUBLE, 
    F7_T3 DOUBLE, 
    T3_T5 DOUBLE, 
    T5_O1 DOUBLE, 
    Fp2_F8 DOUBLE, 
    F8_T4 DOUBLE, 
    T4_T6 DOUBLE, 
    T6_O2 DOUBLE, 
    Fz_Cz DOUBLE, 
    Cz_Pz DOUBLE,
    
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (file_id) REFERENCES origin_files(file_id) ON DELETE CASCADE
);