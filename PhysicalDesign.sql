CREATE DATABASE docnet;
USE docnet;

CREATE TABLE user(
    userID INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    phoneNumber VARCHAR(10),
    telephone VARCHAR(10),
    dob DATE,
    isApproved BIT,
    userType ENUM("doctor", "patient", "admin"),
    
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    gender CHAR(10)
     
);

CREATE TABLE patient(
    patientID INT PRIMARY KEY AUTO_INCREMENT,
    medicalAidNumber INT,
    balance DECIMAL(10, 2),
    
    userID INT,
    CONSTRAINT fk_patient_user_id FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE doctor(
    doctorID INT PRIMARY KEY AUTO_INCREMENT,
    medicalCertificatePath VARCHAR(255),
    yearsOfExperience INT,
    specialisation VARCHAR(255),

    userID INT,
    CONSTRAINT fk_doctor_user_id FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE admin(
    adminID INT PRIMARY KEY AUTO_INCREMENT,

    userID INT,
    CONSTRAINT fk_admin_user_id FOREIGN KEY (userID) REFERENCES user(userID)

)

CREATE TABLE medicine(
    medicineID INT PRIMARY KEY AUTO_INCREMENT,
    medicineName VARCHAR(255),
    medicineType ENUM("capsule", "liquid", "tablet")
);


CREATE TABLE doctorsSchedule(
    doctorsScheduleID INT PRIMARY KEY AUTO_INCREMENT,
    availableTimeOnDay TIME,
    availableDay VARCHAR(20),

    doctorID INT,
    CONSTRAINT fk_doctors_schedule_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)

);

CREATE TABLE waitingList(
    waitingListID INT PRIMARY KEY AUTO_INCREMENT,
    dateAdded DATE,
    status ENUM("waiting", "completed"),

    patientID INT,
    CONSTRAINT fk_waiting_list_patient_id FOREIGN KEY (patientID) REFERENCES patient(patientID)
);


CREATE TABLE rating(
    ratingID INT PRIMARY KEY AUTO_INCREMENT,
    review VARCHAR(255),
    score ENUM(1, 2, 3, 4, 5),

    patientID INT,
    doctorID INT,

    CONSTRAINT fk_rating_patient_id FOREIGN KEY (patientID) REFERENCES patient(patientID),
    CONSTRAINT fk_rating_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)
);

CREATE TABLE notification(
    notificationID INT PRIMARY KEY AUTO_INCREMENT,
    notificationDate DATE,
    status ENUM ("read", "unread"),
    userType ENUM("doctor", "patient", "admin"),

    patientID INT,
    doctorID INT,
    CONSTRAINT fk_notification_patient_id FOREIGN KEY (patientID) REFERENCES patient(patientID),
    CONSTRAINT fk_notification_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)
);


CREATE TABLE appointment(
    appointmentID INT PRIMARY KEY AUTO_INCREMENT,
    appointmentDate DATE,
    reasonForVisit VARCHAR(255),
    status ENUM("pending", "accepted", "rejected", "completed"),
    appointmentTime TIME,

    patientID INT,
    doctorID INT,
    CONSTRAINT fk_appointment_patient_id FOREIGN KEY (patientID) REFERENCES patient(patientID),
    CONSTRAINT fk_appointment_doctor_id FOREIGN KEY (doctorID) REFERENCES doctor(doctorID)
);

-- modeled the payment to have the appointment ID 
CREATE TABLE payment(
    paymentID INT PRIMARY KEY AUTO_INCREMENT,
    paymentStatus ENUM("completed", "rejected"),
    amount DECIMAL(10, 2),

    appointmentID INT,
    CONSTRAINT fk_payment_appointment_id FOREIGN KEY (appointmentID) REFERENCES appointment(appointmentID)
);

CREATE TABLE prescription(
    prescriptionID INT PRIMARY KEY AUTO_INCREMENT,
    instruction VARCHAR(255),
    issueDate DATE,

    medicineID INT,
    appointmentID INT,
    patientID INT,

    CONSTRAINT fk_prescription_medicine_id FOREIGN KEY (medicineID) REFERENCES medicine(medicineID),
    CONSTRAINT fk_prescription_appointment_id FOREIGN KEY (appointmentID) REFERENCES appointment(appointmentID),
    CONSTRAINT fk_prescription_patient_id FOREIGN KEY (patientID) REFERENCES patient(patientID)
);
