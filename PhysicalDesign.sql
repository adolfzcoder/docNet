CREATE DATABASE docnet;
       CREATE DATABASE docnet;
USE docnet;


CREATE TABLE user (
                      userID INT PRIMARY KEY AUTO_INCREMENT,
                      firstName VARCHAR(100),
                      lastName VARCHAR(100),
                      phoneNumber VARCHAR(10),
                      telephone VARCHAR(10),
                      dob VARCHAR(15),
                      isApproved BIT,
                      userType ENUM("DOCTOR", "PATIENT", "ADMIN"),
                      email VARCHAR(100) UNIQUE,
                      password VARCHAR(255),
                      gender CHAR(10)
);

CREATE TABLE doctor (
                        doctorID INT PRIMARY KEY AUTO_INCREMENT,
                        yearsOfExperience INT,
                        specialisation VARCHAR(255),
                        userID INT,
                        isBooked BIT,
                        CONSTRAINT fk_doctor_user_id FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE patient (
                         patientID INT PRIMARY KEY AUTO_INCREMENT,
                         medicalAidNumber INT,
                         userID INT,
                         CONSTRAINT fk_patient_user_id FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE admin (
                       adminID INT PRIMARY KEY AUTO_INCREMENT,
                       userID INT,
                       CONSTRAINT fk_admin_user_id FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE office (
                        officeID INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255),
                        location VARCHAR(255),
                        openingHours TIME,
                        closingHours TIME,
                        accountBalance DECIMAL(10, 2),
                        doctorID INT,
                        CONSTRAINT fk_office_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)
);

CREATE TABLE appointment (
                             appointmentID INT PRIMARY KEY AUTO_INCREMENT,
                             appointmentDate DATE,
                             reasonForVisit VARCHAR(255),
                             status ENUM("PENDING", "ACCEPTED", "REJECTED", "COMPLETED"),
                             appointmentTime TIME,
                             patientID INT,
                             doctorID INT,
                             CONSTRAINT fk_appointment_patient_id FOREIGN KEY (patientID) REFERENCES patient(patientID),
                             CONSTRAINT fk_appointment_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)
);


CREATE TABLE prescription (
                              prescriptionID INT PRIMARY KEY AUTO_INCREMENT,
                              instruction VARCHAR(255),
                              issueDate DATE,
                              appointmentID INT,
                              CONSTRAINT fk_prescription_appointment_id FOREIGN KEY (appointmentID) REFERENCES appointment(appointmentID)
);

CREATE TABLE rating (
                        ratingID INT PRIMARY KEY AUTO_INCREMENT,
                        review VARCHAR(255),
                        score ENUM('1', '2', '3', '4', '5'),
                        patientID INT,
                        doctorID INT,
                        CONSTRAINT fk_rating_patient_id FOREIGN KEY (patientID) REFERENCES patient(patientID),
                        CONSTRAINT fk_rating_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)
);
USE docnet;


CREATE TABLE user (
                      userID INT PRIMARY KEY AUTO_INCREMENT,
                      firstName VARCHAR(100),
                      lastName VARCHAR(100),
                      phoneNumber VARCHAR(10),
                      telephone VARCHAR(10),
                      dob VARCHAR(15),
                      isApproved BIT,
                      userType ENUM("DOCTOR", "PATIENT", "ADMIN"),
                      email VARCHAR(100) UNIQUE,
                      password VARCHAR(255),
                      gender CHAR(10)
);

CREATE TABLE doctor (
                        doctorID INT PRIMARY KEY AUTO_INCREMENT,
                        yearsOfExperience INT,
                        specialisation VARCHAR(255),
                        userID INT,
                        isBooked BIT,
                        CONSTRAINT fk_doctor_user_id FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE patient (
                         patientID INT PRIMARY KEY AUTO_INCREMENT,
                         medicalAidNumber INT,
                         userID INT,
                         CONSTRAINT fk_patient_user_id FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE admin (
                       adminID INT PRIMARY KEY AUTO_INCREMENT,
                       userID INT,
                       CONSTRAINT fk_admin_user_id FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE office (
                        officeID INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255),
                        location VARCHAR(255),
                        openingHours TIME,
                        closingHours TIME,
                        accountBalance DECIMAL(10, 2),
                        doctorID INT,
                        CONSTRAINT fk_office_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)
);

CREATE TABLE appointment (
                             appointmentID INT PRIMARY KEY AUTO_INCREMENT,
                             appointmentDate DATE,
                             reasonForVisit VARCHAR(255),
                             status ENUM("PENDING", "ACCEPTED", "REJECTED", "COMPLETED"),
                             appointmentTime TIME,
                             patientID INT,
                             doctorID INT,
                             CONSTRAINT fk_appointment_patient_id FOREIGN KEY (patientID) REFERENCES patient(patientID),
                             CONSTRAINT fk_appointment_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)
);


CREATE TABLE prescription (
                              prescriptionID INT PRIMARY KEY AUTO_INCREMENT,
                              instruction VARCHAR(255),
                              issueDate DATE,
                              appointmentID INT,
                              CONSTRAINT fk_prescription_appointment_id FOREIGN KEY (appointmentID) REFERENCES appointment(appointmentID)
);

CREATE TABLE rating (
                        ratingID INT PRIMARY KEY AUTO_INCREMENT,
                        review VARCHAR(255),
                        score ENUM('1', '2', '3', '4', '5'),
                        patientID INT,
                        doctorID INT,
                        CONSTRAINT fk_rating_patient_id FOREIGN KEY (patientID) REFERENCES patient(patientID),
                        CONSTRAINT fk_rating_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)
);

INSERT INTO user(firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender) VALUES("root", "admin", 0816166875, 06187345, "2005/03/02", 1, "ADMIN", "root@gmail.com", "Pass@123", "MALE");
INSERT INTO admin(userID) VALUES(1);



-- Insert new users (Doctors, Patients, Admins)
INSERT INTO user (firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender) VALUES
                                                                                                                       ('Emily', 'Clark', '0816789012', '064123456', '1990-02-15', b'1', 'DOCTOR', 'emily.clark@gmail.com', 'passwordhash1', 'FEMALE'),
                                                                                                                       ('Michael', 'Brown', '0816457890', '064765432', '1985-09-20', b'1', 'DOCTOR', 'michael.brown@gmail.com', 'passwordhash2', 'MALE'),
                                                                                                                       ('Sarah', 'Jones', '0816009876', '064888888', '2001-11-22', b'0', 'PATIENT', 'sarah.jones@gmail.com', 'passwordhash3', 'FEMALE'),
                                                                                                                       ('Admin', 'User', '0816000000', '064000000', '1995-12-01', b'1', 'ADMIN', 'admin2@gmail.com', 'passwordhash4', 'MALE');

-- Insert doctors (based on newly added userIDs 12 and 13)
INSERT INTO doctor (yearsOfExperience, specialisation, userID) VALUES
                                                                   (10, 'Cardiologist', 12),
                                                                   (7, 'Dermatologist', 13);

-- Insert offices (based on doctorIDs 3 and 4)
INSERT INTO office (name, location, openingHours, closingHours, accountBalance, doctorID) VALUES
                                                                                              ('Healthy Heart Clinic', 'Windhoek Central', '08:00:00', '16:00:00', 1500.00, 3),
                                                                                              ('SkinCare Center', 'Swakopmund', '09:00:00', '17:00:00', 1200.00, 4);

-- Insert patient (Sarah, userID 14)
INSERT INTO patient (medicalAidNumber, userID) VALUES
    (7834521, 14);

-- Insert appointments (using patientIDs 1 and 2, doctorIDs 3 and 4)
INSERT INTO appointment (appointmentDate, reasonForVisit, status, appointmentTime, patientID, doctorID) VALUES
                                                                                                            ('2025-05-12', 'Routine heart checkup', 'PENDING', '10:30:00', 1, 3),
                                                                                                            ('2025-05-13', 'Skin rash consultation', 'ACCEPTED', '11:00:00', 2, 4);

-- Insert prescriptions (linked to appointments 1 and 2)
INSERT INTO prescription (instruction, issueDate, appointmentID) VALUES
                                                                     ('Take one pill every 8 hours for 5 days', '2025-05-12', 1),
                                                                     ('Apply cream twice a day for 7 days', '2025-05-13', 2);

-- Insert ratings (from patients to doctors)
INSERT INTO rating (review, score, patientID, doctorID) VALUES
                                                            ('Excellent consultation and very professional.', '5', 1, 3),
                                                            ('Very friendly and explained everything clearly.', '4', 2, 4);
