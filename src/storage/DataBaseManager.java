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


    public static Doctor getApprovedDoctorById(int doctorID) {
        String query = """
        SELECT u.userID, u.firstName, u.lastName, u.phoneNumber, u.telephone,
               u.dob, u.isApproved, u.userType, u.email, u.password, u.gender,
               d.doctorID, d.yearsOfExperience, d.specialisation
        FROM doctor d
        JOIN user u ON d.userID = u.userID
        WHERE d.doctorID = ? AND u.isApproved = TRUE
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, doctorID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Doctor doctor = new Doctor();
                    doctor.setUserID(rs.getInt("userID"));
                    doctor.setDoctorID(rs.getInt("doctorID"));
                    doctor.setFirstName(rs.getString("firstName"));
                    doctor.setLastName(rs.getString("lastName"));
                    doctor.setPhoneNumber(rs.getString("phoneNumber"));
                    doctor.setTelephone(rs.getString("telephone"));
                    doctor.setDob(rs.getString("dob"));
                    doctor.setApproved(rs.getBoolean("isApproved"));
                    doctor.setUserType(rs.getString("userType"));
                    doctor.setEmail(rs.getString("email"));
                    doctor.setPassword(rs.getString("password"));
                    doctor.setGender(rs.getString("gender"));
                    doctor.setYearsOfXP(rs.getInt("yearsOfExperience"));
                    doctor.setSpecialisation(rs.getString("specialisation"));
                    return doctor;
                } else {
                    System.out.println("No approved doctor found with ID: " + doctorID);
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error when retrieving doctor: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public static boolean updateAppointmentStatus(int appointmentID, String status) {
        // Validate the status input
        if (!status.equals("PENDING") && !status.equals("ACCEPTED") &&
                !status.equals("REJECTED") && !status.equals("COMPLETED")) {
            System.out.println("Invalid appointment status: " + status);
            return false;
        }

        String query = "UPDATE appointment SET status = ? WHERE appointmentID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setInt(2, appointmentID);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment ID " + appointmentID + " status updated to: " + status);
                return true;
            } else {
                System.out.println("No appointment found with ID: " + appointmentID);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("SQL Error when updating appointment status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public static boolean approveDoctorById(int doctorID) {
        String query = "UPDATE user SET isApproved = ? WHERE userID = (SELECT userID FROM doctor WHERE doctorID = ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setBoolean(1, true); // Approve
            ps.setInt(2, doctorID);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Doctor with ID " + doctorID + " has been approved.");
                return true;
            } else {
                System.out.println("No doctor found with ID: " + doctorID);
                return false;
            }

        } catch (SQLException e) {
            System.out.println("SQL Error when approving doctor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
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


            System.out.println("Appointments retrieved from database: " + appointments.size());
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
    public static Appointment getAppointmentById(int appointmentID) {
        String sql = "SELECT * FROM appointment WHERE appointmentID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Appointment(
                        rs.getInt("appointmentID"),
                        rs.getInt("patientID"),
                        rs.getInt("doctorID"),
                        rs.getDate("appointmentDate").toLocalDate(),
                        rs.getTime("appointmentTime").toLocalTime(),
                        rs.getString("reasonForVisit"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error getting appointment by ID:");
            e.printStackTrace();
        }
        return null;
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
        String query2 = "INSERT INTO doctor (yearsOfExperience, specialisation, userID, isBooked) VALUES (?, ?, ?, ?)";
        String query3 = "INSERT INTO office (name, accountBalance, doctorID) VALUES (?, ?, ?)";

        int generatedUserID = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement ps = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
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
                if (affectedRows == 0) {
                    conn.rollback();
                    throw new SQLException("Inserting user failed, no rows affected.");
                }

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedUserID = rs.getInt(1);
                        System.out.println("Generated User ID: " + generatedUserID);
                    } else {
                        conn.rollback();
                        throw new SQLException("Inserting user failed, no ID obtained.");
                    }
                }
            }

            int generatedDoctorID = -1;
            try (PreparedStatement ps2 = conn.prepareStatement(query2)) {
                ps2.setInt(1, doctor.getYearsOfXP());
                ps2.setString(2, doctor.getSpecialisation());
                ps2.setInt(3, generatedUserID);
                ps2.setBoolean(4, false); // isBooked

                int affectedRows2 = ps2.executeUpdate();
                if (affectedRows2 == 0) {
                    conn.rollback();
                    throw new SQLException("Inserting doctor failed, no rows affected.");
                }

                try (PreparedStatement psFetch = conn.prepareStatement("SELECT doctorID FROM doctor WHERE userID = ?")) {
                    psFetch.setInt(1, generatedUserID);
                    try (ResultSet rs2 = psFetch.executeQuery()) {
                        if (rs2.next()) {
                            generatedDoctorID = rs2.getInt("doctorID");
                            System.out.println("Resolved Doctor ID: " + generatedDoctorID);
                        } else {
                            conn.rollback();
                            throw new SQLException("Failed to retrieve doctorID by userID.");
                        }
                    }
                }
            }

            try (PreparedStatement ps3 = conn.prepareStatement(query3)) {
                ps3.setString(1, doctor.getOfficeName());
                ps3.setDouble(2, 1000.00); // Initial balance
                ps3.setInt(3, generatedDoctorID);

                ps3.executeUpdate();
                System.out.println("Office inserted successfully.");
            }
            System.out.println("Inserted: User, Doctor and Office succsufly");

            conn.commit();
            return generatedUserID;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
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
        // First, get the patient ID from the user in the session
        User currentUser = SystemManager.getSession().getFirst();
        int userID = currentUser.getUserID();
        int patientID = getPatientIDFromUserID(userID);

        if (patientID <= 0) {
            System.out.println("Error: Could not find patient record for the current user.");
            return;
        }

        String query = "INSERT INTO appointment (appointmentDate, reasonForVisit, status, appointmentTime, patientID, doctorID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, java.sql.Date.valueOf(appointment.getAppointmentDate()));
            ps.setString(2, appointment.getReasonForVisit());
            ps.setString(3, appointment.getStatus());
            ps.setTime(4, java.sql.Time.valueOf(appointment.getAppointmentTime()));
            ps.setInt(5, patientID); // Use the patientID retrieved from the userID
            ps.setInt(6, appointment.getDoctorID());

            System.out.println("Inserting appointment with patientID: " + patientID);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                // Get the auto-generated appointment ID
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        appointment.setAppointmentID(generatedKeys.getInt(1));
                        System.out.println("Appointment created with ID: " + appointment.getAppointmentID());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting appointment: " + e.getMessage());
        }
    }

    private static int getPatientIDFromUserID(int userID) {
        String query = "SELECT patientID FROM patient WHERE userID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("patientID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error finding patient ID: " + e.getMessage());
        }

        return -1;
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

    public static void testDatabaseConnection() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Database connection successful!");
            System.out.println("Database URL: " + DB_URL);
        } catch (SQLException e) {
            System.err.println("ERROR connecting to database:");
            e.printStackTrace();
        }
    }

}
