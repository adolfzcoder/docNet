package storage;



import models.*;
import utils.EnvLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBaseManager {
        static HashMap<String, String> env = EnvLoader.loadEnv(".env");

    // if no internet uncomment code below
    // static String DB_URL = env.get("DB_URL_OFFLINE");
    // static String DB_USER = env.get("DB_USER_OFFLINE");
    // static String DB_PASSWORD = env.get("DB_PASSWORD_OFFLINE");

        static String DB_URL = env.get("DB_URL");
        static String DB_USER = env.get("DB_USER");
        static String DB_PASSWORD = env.get("DB_PASSWORD");

        public static ArrayList<User> getUsers() {
            ArrayList<User> users = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM user")) {

                while (rs.next()) {
                    User user = new User(
                            rs.getInt("userID"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("phoneNumber"),
                            rs.getString("telephone"),
                            rs.getString("dob"),
                            rs.getBoolean("isApproved"),
                            rs.getString("userType"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("gender")
                    );
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return users;
        }

        public static ArrayList<Admin> getAdmins() {
            ArrayList<Admin> admins = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM admin")) {

                while (rs.next()) {
                    Admin admin = new Admin(
                            rs.getInt("adminID"),
                            rs.getInt("userID")
                    );
                    admins.add(admin);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return admins;
        }


        public static ArrayList<Patient> getPatients() {
            ArrayList<Patient> patients = new ArrayList<>();

            String patientQuery = "SELECT * FROM patient";
            String userQuery = "SELECT * FROM user WHERE userID = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet patientRs = stmt.executeQuery(patientQuery)) {

                while (patientRs.next()) {
                    int userID = patientRs.getInt("userID");
                    int patientID = patientRs.getInt("patientID");
                    int medicalAidNumber = patientRs.getInt("medicalAidNumber");


                    try (PreparedStatement userStmt = conn.prepareStatement(userQuery)) {
                        userStmt.setInt(1, userID);
                        ResultSet userRs = userStmt.executeQuery();

                        if (userRs.next()) {
                            String firstName = userRs.getString("firstName");
                            String lastName = userRs.getString("lastName");
                            String phoneNumber = userRs.getString("phoneNumber");
                            String telephone = userRs.getString("telephone");
                            String dob = userRs.getString("dob");
                            boolean isApproved = userRs.getBoolean("isApproved");
                            String userType = userRs.getString("userType");
                            String email = userRs.getString("email");
                            String password = userRs.getString("password");
                            String gender = userRs.getString("gender");

                            Patient patient = new Patient(userID, patientID, medicalAidNumber, firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender);
                            patients.add(patient);
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return patients;
        }


        public static ArrayList<Doctor> getDoctors() {
            ArrayList<Doctor> doctors = new ArrayList<>();
            String query = "SELECT d.*, u.* FROM doctor d JOIN user u ON d.userID = u.userID";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    Doctor doctor = new Doctor(
                            rs.getInt("userID"),
                            rs.getInt("doctorID"),
                            rs.getInt("yearsOfExperience"),
                            rs.getString("specialisation"),

                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("phoneNumber"),
                            rs.getString("telephone"),
                            rs.getString("dob"),
                            rs.getBoolean("isApproved"),
                            rs.getString("userType"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("gender"),
                            rs.getBoolean("isBooked")
                    );
                    doctors.add(doctor);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return doctors;
        }


        public static ArrayList<Appointment> getAppointments() {
            ArrayList<Appointment> appointments = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM appointment")) {

                while (rs.next()) {
                    Appointment appointment = new Appointment(
                            rs.getInt("appointmentID"),
                            rs.getInt("patientID"),
                            rs.getInt("doctorID"),
                            rs.getDate("appointmentDate").toLocalDate(),
                            rs.getTime("appointmentTime").toLocalTime(),
                            rs.getString("reasonForVisit"),
                            rs.getString("status")
                    );
                    appointments.add(appointment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return appointments;
        }

        public static ArrayList<Office> getOffices() {
            ArrayList<Office> offices = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM office")) {

                while (rs.next()) {
                    Office office = new Office(
                            rs.getInt("officeID"),
                            rs.getString("name"),
                            rs.getString("location"),
                            rs.getTime("openingHours").toLocalTime(),
                            rs.getTime("closingHours").toLocalTime(),
                            rs.getDouble("accountBalance"),
                            rs.getInt("doctorID")
                    );
                    offices.add(office);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return offices;
        }


    public static ArrayList<Rating> getRatings() {
        ArrayList<Rating> ratings = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM rating")) {



            while (rs.next()) {
                // int userID, int doctorID, String review,double score, LocalDateTime dateTime )
                Rating rating = new Rating(
                        rs.getInt("ratingID"),
                        rs.getInt("patientID"),
                        rs.getInt("doctorID"),
                        rs.getString("review"),
                        rs.getDouble("score")

                );
                ratings.add(rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }

    public static ArrayList<Prescription> getPrescriptions() {
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM prescription")) {

            while (rs.next()) {
                Prescription prescription = new Prescription(
                        rs.getInt("prescriptionID"),
                        rs.getString("instruction"),
                        rs.getInt("doctorID"),
                        rs.getInt("patientID"),
                        rs.getInt("appointmentID"),
                        rs.getDate("issueDate"));
                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }


    public static int insertUser(Patient patient) {
        String query1 = "INSERT INTO user (firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String query2 = "INSERT INTO patient (medicalAidNumber, userID) VALUES (?, ?)";
        int generatedUserID = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, patient.getFirstName());
            ps.setString(2, patient.getLastName());
            ps.setString(3, patient.getPhoneNumber());
            ps.setString(4, patient.getTelephone());
            ps.setString(5, patient.getDob());
            ps.setBoolean(6, patient.getApproved());
            ps.setString(7, patient.getUserType());
            ps.setString(8, patient.getEmail());
            ps.setString(9, patient.getPassword());
            ps.setString(10, patient.getGender());


            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedUserID = rs.getInt(1);
                    System.out.println("Generated User ID: " + generatedUserID);

                    PreparedStatement ps2 = conn.prepareStatement(query2);
                    ps2.setInt(1, patient.getMedicalAidNumber());
                    ps2.setInt(2, generatedUserID);
                    ps2.executeUpdate();

                }
            } else {
                System.out.println("No rows affected when inserting user!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error when inserting user: " + e.getMessage());
            e.printStackTrace();
        }

        return generatedUserID;
    }

    public static int insertUser(Doctor doctor) {
        String query1 = "INSERT INTO user (firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String query2 = "INSERT INTO doctor (yearsOfExperience, specialisation, userID) VALUES (?, ?, ?)";
        String query3 = "INSERT INTO office (name, accountBalance, doctorID) VALUES (?, ?, ?)";

        int generatedUserID = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {

            // Insert into user table
            ps.setString(1, doctor.getFirstName());
            ps.setString(2, doctor.getLastName());
            ps.setString(3, doctor.getPhoneNumber());
            ps.setString(4, doctor.getTelephone());
            ps.setString(5, doctor.getDob());
            ps.setBoolean(6, doctor.getApproved());
            ps.setString(7, doctor.getUserType());
            ps.setString(8, doctor.getEmail());
            ps.setString(9, doctor.getPassword());
            ps.setString(10, doctor.getGender());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedUserID = rs.getInt(1);
                    System.out.println("Generated User ID: " + generatedUserID);

                    // Insert into doctor table
                    try (PreparedStatement ps2 = conn.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS)) {
                        ps2.setInt(1, doctor.getYearsOfXP());
                        ps2.setString(2, doctor.getSpecialisation());
                        ps2.setInt(3, generatedUserID);

                        int affectedRows2 = ps2.executeUpdate();
                        if (affectedRows2 > 0) {
                            ResultSet rs2 = ps2.getGeneratedKeys();
                            if (rs2.next()) {
                                int doctorID = rs2.getInt(1);
                                System.out.println("Generated Doctor ID: " + doctorID);

                                // Insert into office table
                                try (PreparedStatement ps3 = conn.prepareStatement(query3)) {
                                    ps3.setString(1, doctor.getOfficeName());
                                    ps3.setDouble(2, 1000.00);
                                    ps3.setInt(3, doctorID);
                                    ps3.executeUpdate();
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.println("No rows affected when inserting user!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error when inserting user: " + e.getMessage());
            e.printStackTrace();
        }

        return generatedUserID;
    }
//    public static int insertPatient(Patient patient) {
//        // First, insert the user
//        int userID = insertUser((Patient)patient);
//        if (userID == -1) {
//            System.out.println("Failed to insert user record for patient!");
//            return -1;
//        }
//
//        // Set the generated userID to the patient object
//        patient.setUserID(userID);
//
//        // Now insert the patient record
//        String query = "INSERT INTO patient (medicalAidNumber, balance, userID) VALUES (?, ?, ?)";
//        int generatedPatientID = -1;
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//
//            ps.setInt(1, patient.getMedicalAidNumber());
//            ps.setDouble(2, patient.getBalance());
//            ps.setInt(3, userID);
//
//            int affectedRows = ps.executeUpdate();
//
//            if (affectedRows > 0) {
//                ResultSet rs = ps.getGeneratedKeys();
//                if (rs.next()) {
//                    generatedPatientID = rs.getInt(1);
//                    patient.setPatientID(generatedPatientID);
//                    System.out.println("Generated Patient ID: " + generatedPatientID);
//                }
//            } else {
//                System.out.println("No rows affected when inserting patient!");
//            }
//        } catch (SQLException e) {
//            System.out.println("SQL Error when inserting patient: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return generatedPatientID;
//    }
//    public static int insertDoctor(Doctor doctor) {
//        // First, insert the user
//        int userID = insertUser(doctor);
//        if (userID == -1) {
//            System.out.println("Failed to insert user record for doctor!");
//            return -1;
//        }
//
//        // Set the generated userID to the doctor object
//        doctor.setUserID(userID);
//
//        // Now insert the doctor record
//        String query = "INSERT INTO doctor (medicalCertificatePath, yearsOfExperience, specialisation, userID) VALUES (?, ?, ?, ?)";
//        int generatedDoctorID = -1;
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
//
//            ps.setString(1, doctor.getMedicalCertificate());
//            ps.setInt(2, doctor.getYearsOfXP());
//            ps.setString(3, doctor.getSpecialisation());
//            ps.setInt(4, userID);
//
//            int affectedRows = ps.executeUpdate();
//
//            if (affectedRows > 0) {
//                ResultSet rs = ps.getGeneratedKeys();
//                if (rs.next()) {
//                    generatedDoctorID = rs.getInt(1);
//                    doctor.setDoctorID(generatedDoctorID);
//                    System.out.println("Generated Doctor ID: " + generatedDoctorID);
//                }
//            } else {
//                System.out.println("No rows affected when inserting doctor!");
//            }
//        } catch (SQLException e) {
//            System.out.println("SQL Error when inserting doctor: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return generatedDoctorID;
//    }

    public static int insertOffice(String officeName, String location, Time openingHours, Time closingHours, double accountBalance, int doctorID) {
        String query = "INSERT INTO office (name, location, openingHours, closingHours, accountBalance, doctorID) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedOfficeID = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, officeName);
            ps.setString(2, location != null ? location : "Default Location");
            ps.setTime(3, openingHours != null ? openingHours : Time.valueOf("09:00:00"));
            ps.setTime(4, closingHours != null ? closingHours : Time.valueOf("17:00:00"));
            ps.setDouble(5, accountBalance);
            ps.setInt(6, doctorID);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedOfficeID = rs.getInt(1);
                    System.out.println("Generated Office ID: " + generatedOfficeID);
                }
            } else {
                System.out.println("No rows affected when inserting office!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error when inserting office: " + e.getMessage());
            e.printStackTrace();
        }

        return generatedOfficeID;
    }
    public static int insertOffice(String officeName, int doctorID) {
        return insertOffice(officeName, null, null, null, 1000.0, doctorID);
    }

    public static void insertAppointment(Appointment appointment) {
            String query = "INSERT INTO appointment (appointmentDate, reasonForVisit, status, appointmentTime, patientID, doctorID) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setDate(1, java.sql.Date.valueOf(appointment.getAppointmentDate()));
                ps.setString(2, appointment.getReasonForVisit());
                ps.setString(3, appointment.getStatus());
                ps.setTime(4, java.sql.Time.valueOf(appointment.getAppointmentTime()));
                ps.setInt(5, appointment.getPatientID());
                ps.setInt(6, appointment.getDoctorID());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public static int insertAdmin(Admin ad) {
        String query = "INSERT INTO admin (userID) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, ad.getUserTypeID());


            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void insertRating(Rating rating) {

            /*
            ratingID INT PRIMARY KEY AUTO_INCREMENT,
                       review VARCHAR(255),
                       score ENUM('1', '2', '3', '4', '5'),

                       patientID INT,
                       doctorID INT,
             */
        String query = "INSERT INTO rating (review, score, patientID, doctorID) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, rating.getReview());
            ps.setDouble(2, rating.getScore());
            ps.setInt(3, rating.getPatientID());
            ps.setInt(4, rating.getDoctorID());


            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
