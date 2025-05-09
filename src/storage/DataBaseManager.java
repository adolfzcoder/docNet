package storage;



import models.Admin;
import models.Doctor;
import models.Office;
import models.Prescription;
import models.User;
import models.Appointment;
import models.Patient;
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
                    double balance = patientRs.getDouble("balance");

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

                            Patient patient = new Patient(userID, patientID, medicalAidNumber, firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender, balance);
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
                            rs.getString("medicalCertificatePath"),
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


    public static int insertUser(User user) {
        String query = "INSERT INTO user (firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int generatedUserID = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getTelephone());
            ps.setString(5, user.getDob());
            ps.setBoolean(6, user.getApproved());
            ps.setString(7, user.getUserType());
            ps.setString(8, user.getEmail());
            ps.setString(9, user.getPassword());
            ps.setString(10, user.getGender());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedUserID = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedUserID;
    }



    public static void insertPatient(Patient patient) {
            String query = "INSERT INTO patient (medicalAidNumber, balance, userID) VALUES (?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(query)) {

                int userID = insertUser(new User(patient.getFirstName(), patient.getLastName(), patient.getPhoneNumber(), patient.getTelephone(), patient.getDob(), patient.getApproved(), patient.getUserType(), patient.getEmail(), patient.getPassword(), patient.getGender()));
                if (userID != -1) {
                    patient.setUserID(userID);
                    insertPatient(patient);
                }
                ps.setInt(1, patient.getMedicalAidNumber());
                ps.setDouble(2, patient.getBalance());
                ps.setInt(3, patient.getUserID());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public static int insertDoctor(Doctor doctor) {
        String query = "INSERT INTO doctor (medicalCertificatePath, yearsOfExperience, specialisation, userID) VALUES (?, ?, ?, ?)";
        int generatedDoctorID = -1;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            int userID = insertUser(new User(
                    doctor.getFirstName(),
                    doctor.getLastName(),
                    doctor.getPhoneNumber(),
                    doctor.getTelephone(),
                    doctor.getDob(),
                    doctor.getApproved(),
                    doctor.getUserType(),
                    doctor.getEmail(),
                    doctor.getPassword(),
                    doctor.getGender()
            ));

            if (userID != -1) {
                doctor.setUserID(userID);

                ps.setString(1, doctor.getMedicalCertificate());
                ps.setInt(2, doctor.getYearsOfXP());
                ps.setString(3, doctor.getSpecialisation());
                ps.setInt(4, doctor.getUserID());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    generatedDoctorID = rs.getInt(1);
                    doctor.setDoctorID(generatedDoctorID);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedDoctorID;
    }

    public static void insertOffice(String officeName, int doctorID) {
        String query = "INSERT INTO office (name, doctorID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, officeName);
            ps.setInt(2, doctorID);
             // Set after doctor is inserted

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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


    public static void insertAdmin(Admin ad) {
        String query = "INSERT INTO admin (userID) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, ad.getUserTypeID());


            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
