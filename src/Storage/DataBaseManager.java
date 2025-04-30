package Storage;



import adminModules.Admin;
import doctorModules.Doctor;
import doctorModules.Office;
import models.User;
import patientModules.Appointment;
import patientModules.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class DataBaseManager {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/docnet";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "";

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

        /*
        public List<Patient> getPatients() {
            List<Patient> patients = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM patient")) {

                while (rs.next()) {
                    Patient patient = new Patient(
                            rs.getInt("patientID"),
                            rs.getInt("medicalAidNumber"),
                            rs.getDouble("balance"),
                            rs.getInt("userID")
                    );
                    patients.add(patient);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return patients;
        }

        public List<Doctor> getDoctors() {
            List<Doctor> doctors = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM doctor")) {

                while (rs.next()) {
                    Doctor doctor = new Doctor(
                            rs.getInt("doctorID"),
                            rs.getString("medicalCertificatePath"),
                            rs.getInt("yearsOfExperience"),
                            rs.getString("specialisation"),
                            rs.getInt("userID")
                    );
                    doctors.add(doctor);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return doctors;
        }

        public List<Appointment> getAppointments() {
            List<Appointment> appointments = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM appointment")) {

                while (rs.next()) {
                    Appointment appointment = new Appointment(
                            rs.getInt("appointmentID"),
                            rs.getDate("appointmentDate"),
                            rs.getString("reasonForVisit"),
                            rs.getString("status"),
                            rs.getTime("appointmentTime"),
                            rs.getInt("patientID"),
                            rs.getInt("doctorID")
                    );
                    appointments.add(appointment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return appointments;
        }

        public List<Office> getOffices() {
            List<Office> offices = new ArrayList<>();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM office")) {

                while (rs.next()) {
                    doctorModules.Office office = new doctorModules.Office(
                            rs.getInt("officeID"),
                            rs.getString("name"),
                            rs.getString("location"),
                            rs.getTime("openingHours"),
                            rs.getTime("closingHours"),
                            rs.getDouble("accountBalance")
                    );
                    offices.add(office);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return offices;
        }


        public void insertUser(User user) {
            String query = "INSERT INTO user (firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getPhoneNumber());
                ps.setString(4, user.getTelephone());
                ps.setDate(5, new java.sql.Date(user.getDob().getTime()));
                ps.setBoolean(6, user.getIsApproved());
                ps.setString(7, user.getUserType());
                ps.setString(8, user.getEmail());
                ps.setString(9, user.getPassword());
                ps.setString(10, user.getGender());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public void insertPatient(Patient patient) {
            String query = "INSERT INTO patient (medicalAidNumber, balance, userID) VALUES (?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, patient.getMedicalAidNumber());
                ps.setDouble(2, patient.getBalance());
                ps.setInt(3, patient.getUserID());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public void insertDoctor(Doctor doctor) {
            String query = "INSERT INTO doctor (medicalCertificatePath, yearsOfExperience, specialisation, userID) VALUES (?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, doctor.getMedicalCertificatePath());
                ps.setInt(2, doctor.getYearsOfExperience());
                ps.setString(3, doctor.getSpecialisation());
                ps.setInt(4, doctor.getUserID());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void insertAppointment(Appointment appointment) {
            String query = "INSERT INTO appointment (appointmentDate, reasonForVisit, status, appointmentTime, patientID, doctorID) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setDate(1, new java.sql.Date(appointment.getAppointmentDate().getTime()));
                ps.setString(2, appointment.getReasonForVisit());
                ps.setString(3, appointment.getStatus());
                ps.setTime(4, appointment.getAppointmentTime());
                ps.setInt(5, appointment.getPatientID());
                ps.setInt(6, appointment.getDoctorID());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }
         */

}
