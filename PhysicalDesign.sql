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
