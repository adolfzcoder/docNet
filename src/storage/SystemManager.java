package storage;
import storage.DataBaseManager;
import models.Admin;
import models.Doctor;
import models.*;
import utils.AlertHelper;

import javax.print.Doc;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SystemManager {

    // this class stores array lists of our data, it also has helper methods to get or add data, like addDoctor()
    private static ArrayList<User> users = fetchUsers(); //fetch all users from the db


    private static ArrayList<Admin> admins= fetchAdmins();
    private static ArrayList<Patient> patients = fetchPatients();
    private static ArrayList<Doctor> doctors = fetchDoctors();
    private static ArrayList<Appointment> appointments = fetchAppointments();
    private static ArrayList<Prescription> prescriptions = fetchPrescriptions();
    private static ArrayList<Rating> ratings = fetchRatings();
    private static ArrayList<Office> offices = fetchOffices(); // goes to db and fetches all the offices


    private static ArrayList<Doctor> approvedDoctors = fetchApprovedDOctors();

    private static ArrayList<Doctor> fetchApprovedDOctors() {
        ArrayList<Doctor> approved = new ArrayList<>();
        for (Doctor dr: doctors){
            if(dr.getApproved()){
                approved.add(dr);
            }
        }

        return approved;
    }

    private static ArrayList<Doctor> pendingDoctors = new ArrayList<>();
    // private static ArrayList<Notification> allNotifications = new ArrayList<>();
    private static ArrayList<Appointment> pendingAppointments = new ArrayList<>();
    private static ArrayList<Appointment> approvedAppointments = new ArrayList<>();
    private static ArrayList<Appointment> declinedAppointments = new ArrayList<>();
    private static ArrayList<User> session = new ArrayList<>(); // stores the logged in users details
    private static ArrayList<Doctor> officeDoctors = new ArrayList<>();





    public static void initializeLists() {
        doctors = getDoctors();
        patients = getPatients();
        admins = getAdmins();
        users = getUsers();

        appointments = getAppointments();
        offices = getOffices();
        prescriptions = getPrescriptions();


    }


    public static void startSession(User user){
        session.add(user);
    }
    public static ArrayList<User> getSession(){
        return session;
    }

    public static int findOfficeIdByName(String officeName) {
        for (Office office : offices) {
            if (office.getOfficeName().equalsIgnoreCase(officeName)) {
                return office.getOfficeID();
            }
        }
        return -1;
    }

    public static ArrayList<Rating> fetchRatings(){
        try {
            ArrayList<Rating> ratings = DataBaseManager.getRatings();
            if (ratings == null || ratings.isEmpty()) {
                ratings = new ArrayList<>();
            }
            return ratings;
        } catch (Exception e) {
          //   AlertHelper.showError("Error", "Error fetching Ratings: " + e.getMessage());

            System.err.println("Error fetching Ratins: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public static ArrayList<Prescription> fetchPrescriptions(){
        try {
            ArrayList<Prescription> prescriptions = DataBaseManager.getPrescriptions();
            if (prescriptions == null || prescriptions.isEmpty()) {
                prescriptions = new ArrayList<>(); // Initialize to avoid null issues
            }
            return prescriptions;
        } catch (Exception e) {
            // AlertHelper.showError("Error", "Error fetching patients: " + e.getMessage());

            System.err.println("Error fetching users: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of an error
        }
    }
    public static ArrayList<User> fetchUsers() {
        try {

            ArrayList<User> users = DataBaseManager.getUsers();
            if (users == null || users.isEmpty()) {
                users = new ArrayList<>();
            }
            return users;
        } catch (Exception e) {
            //AlertHelper.showError("Error", "Error fetching patients: " + e.getMessage());

            System.err.println("Error fetching users: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of an error
        }
    }

    public static ArrayList<Office> fetchOffices() {
        try {
            ArrayList<Office> offices =  DataBaseManager.getOffices();
            if (offices.isEmpty()) {
                offices = new ArrayList<>();
            }
            return offices;
        } catch (Exception e) {
            // AlertHelper.showError("Error", "Error fetching Offices: " + e.getMessage());

            System.err.println("Error fetching offices: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static ArrayList<Admin> fetchAdmins() {
        try {
            ArrayList<Admin> admins = DataBaseManager.getAdmins();
            if (admins == null || admins.isEmpty()) {
                admins = new ArrayList<>();
            }
            return admins;
        } catch (Exception e) {
            // AlertHelper.showError("Error", "Error fetching patients: " + e.getMessage());

            System.err.println("Error fetching admins: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    public static ArrayList<Patient> fetchPatients() {
        try {
            ArrayList<Patient> patients = DataBaseManager.getPatients();
            if (patients == null || patients.isEmpty()) {
                patients = new ArrayList<>();
            }

            return patients;
        } catch (Exception e) {
            // AlertHelper.showError("Error", "Error fetching patients: " + e.getMessage());
            // System.err.println("Error fetching patients: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static ArrayList<Doctor> fetchDoctors() {
        try {
            ArrayList<Doctor> doctors = DataBaseManager.getDoctors();
            for (Doctor doctor: doctors){
                System.out.println("Doctor:" + doctor.getFirstName());
            }
            return doctors;
        } catch (Exception e) {
            // AlertHelper.showError("Error", "Error fetching patients: " + e.getMessage());

            // System.err.println("Error fetching doctors: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static ArrayList<Appointment> fetchAppointments() {
        try {
            ArrayList<Appointment> appointments = DataBaseManager.getAppointments();
            if (appointments == null || appointments.isEmpty()) {
                appointments = new ArrayList<>();
            }
            return appointments;
        } catch (Exception e) {
            // AlertHelper.showError("Error", "Error fetching patients: " + e.getMessage());

            // System.err.println("Error fetching appointments: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void addOffice(Doctor doctor) {
        if (doctor.getDoctorID() <= 0) {
            System.out.println("Cannot add office - doctor ID is invalid: " + doctor.getDoctorID());
            return;
        }

        int officeID = DataBaseManager.insertOffice(
                doctor.getOfficeName(),
                doctor.getLocation(),
                doctor.getOpeningHours(),
                doctor.getClosingHours(),
                1000.0,
                doctor.getDoctorID()
        );

        if (officeID != -1) {

            Office newOffice = new Office(
                    officeID,
                    doctor.getOfficeName(),
                    "Default Location",
                    java.time.LocalTime.of(9, 0), // default times, can be cahnged later
                    java.time.LocalTime.of(15, 0),
                    1000.0,
                    doctor.getDoctorID()
            );
            offices.add(newOffice);

            System.out.println("Office successfully added with ID: " + officeID);
        } else {
            System.out.println("Failed to insert Office in database");
        }
    }


    public int findOfficeIDByDoctorID(int doctorID){
        int officeID = 0;
        for(Doctor dr: doctors){

            if(dr.getDoctorID() == doctorID){
                // officeID = dr.getOfficeID();
            }
        }
        return officeID;
}

    public static Optional<Doctor> findDoctorByEmail(String email) {
        for (Doctor doctor : doctors) {
            if (doctor.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(doctor);
            }
        }
        return Optional.empty();
    }

    public static Optional<Patient> findPatientByEmail(String email) {
        for (Patient patient : patients) {
            if (patient.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(patient);
            }
        }
        return Optional.empty();
    }

    public static Optional<Admin> findAdminByEmail(String email) {
        for (Admin admin : admins) {
            if (admin.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(admin);
            }
        }
        return Optional.empty();
    }





    public static ArrayList<Doctor> getOfficeDoctors(int officeID) {
        officeDoctors = new ArrayList<>();

        if (offices == null) {
            offices = fetchOffices();
        }

        for (Office office : offices) {
            if (office.getOfficeID() == officeID) {
                int doctorID = office.getDoctorID();
                Doctor doctor = findDoctor(doctorID);
                if (doctor != null) {
                    officeDoctors.add(doctor);
                }
            }
        }

        if (officeDoctors.isEmpty()) {
            System.out.println("No doctors found for office ID: " + officeID);
        }

        return officeDoctors;
    }
    public static Patient findPatient(int patientID){
        for(Patient pt: patients){
            if(pt.getPatientID() == patientID){
                return pt;
            }
            else{
                System.out.println("Doctor ID not found");
            }
        }
        return null;
    }
    public static Appointment findAppointment(int appointmentID) {
        for (Appointment appt : appointments) {
            if (appt.getAppointmentID() == appointmentID) {
                return appt;
            }
        }
        System.out.println("Appointment ID not found");
        return null;
    }

    public static ArrayList<Appointment> returnAppointmentsByPatientID(int patientID){
        ArrayList<Appointment> a = new ArrayList<>();
        for(Appointment appt: appointments){
            if(appt.getPatientID() == patientID){
                a.add(appt);
            }
        }
        return a;
    }


    public static Doctor findApprovedDoctor(int doctorID){
        for(Doctor dr: approvedDoctors){
            for(Doctor dra: approvedDoctors){
                System.out.println("Approved Doctor: "+dra.getFirstName());
            }
            if(dr.getDoctorID() == doctorID){
                return dr;
            }
            else{
                System.out.println("Doctor ID not found");;
            }
        }
        return null;
    }
    public static Doctor findDoctor(int doctorID){
        for(Doctor dr: doctors){
            if(dr.getDoctorID() == doctorID){
                return dr;
            }
            else{
                System.out.println("Doctor ID not found");;
            }
        }
        return null;
    }
    public static ArrayList<Office> getOffices() {
        if (offices == null) {
            offices = fetchOffices();
        }
        return offices;
    }

    public static ArrayList<Prescription> getPrescriptions(){
        return prescriptions;
    }

    public static void addPrescription(Prescription pst){
        prescriptions.add(pst);
        System.out.println("Prescription has been added. ");
    }
    public static ArrayList<Appointment> getPendingAppointments(int doctorID) {
        ArrayList<Appointment> appts = new ArrayList<>();

        for(Appointment appt: pendingAppointments){
            if(appt.getDoctorID() == doctorID){
                appts.add(appt);
            }
        }
        return appts;
    }

    public static void addPendingAppointments(ArrayList<Appointment> pendingAppointments) {
        SystemManager.pendingAppointments = pendingAppointments;
    }

    public static ArrayList<Appointment> getApprovedAppointments() {
        return approvedAppointments;
    }

    public static void addApprovedAppointments(ArrayList<Appointment> approvedAppointments) {
        SystemManager.approvedAppointments = approvedAppointments;
    }

    public static ArrayList<Appointment> getDeclinedAppointments() {
        return declinedAppointments;
    }

    public static void addDeclinedAppointments(ArrayList<Appointment> declinedAppointments) {
        SystemManager.declinedAppointments = declinedAppointments;
    }


    public static ArrayList<Appointment> getAppointments(){
        return appointments;
    }


    public static String flushSession(){
        session.clear();
        System.out.println("Session has been flushed (user logged out)");
        return "Session has been flushed (user logged out)";
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<Patient> getPatients() {
        return patients;
    }

    public static ArrayList<Doctor> getDoctors() {
        return doctors;
    }

//    public static ArrayList<Notification> getAllNotifications() {
//        return allNotifications;
//    }

    public static boolean addUser(Patient patient) {
        int generatedId = DataBaseManager.insertUser(patient);

        if (generatedId != -1) {
            patients.add(patient);
            // users.add(patient);
            return true;
        } else {
            // Insertion failed
            return false;
        }
    }

    public static boolean addUser(Doctor doctor) {
        int generatedId = DataBaseManager.insertUser(doctor);

        if (generatedId != -1) {
            doctors.add(doctor);
            //users.add(doctor);
            return true;
        } else {
            // Insertion failed
            return false;
        }
    }


    public static String addToPendingDoctorList(Doctor dr){
        pendingDoctors.add(dr);
        String message = "added doctor to pending list, awaiting approval";
        System.out.println(message);
        return message;
    }
    public static String addApprovedDoctor(Doctor dr){
        approvedDoctors.add(dr);
        String message = "Approved doctor was added successfully";
        System.out.println(message);
        return message;
    }
    public static void addDoctor(Doctor doctor) {
        int doctorID = DataBaseManager.insertUser(doctor);

        if (doctorID != -1) {
            // doctor created, now set the generated doctor id
            doctor.setDoctorID(doctorID);

            doctors.add(doctor);

            addOffice(doctor);

            System.out.println("Doctor successfully added with ID: " + doctorID);
        } else {
            System.out.println("Failed to insert Doctor in database");
        }
    }

    public static ArrayList<Doctor> getApprovedDoctors(){
        return approvedDoctors;
    }



    public static Optional<User> findUser(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public static void addAdmin(Admin admin) {
        int adminID = DataBaseManager.insertAdmin(admin);
        if (adminID != -1) {
            admins.add(admin);
            System.out.println("Admin successfully added with ID: " + adminID);
        } else {
            System.out.println("Failed to insert Admin in database");
        }
    }

//    public static String addNotification(Notification notif){
//        String message = "Notification was added succesfully";
//        allNotifications.add(notif);
//
//        System.out.println(message);
//        return message;
//
//    }



    public static ArrayList<Doctor> getPendingDoctors() {
        return pendingDoctors;
    }

    public static String addPatient(Patient patient) {
        int patientID = DataBaseManager.insertUser(patient);

        if (patientID != -1) {
            patient.setPatientID(patientID);

            patients.add(patient);

            String message = "Patient successfully added with ID: " + patientID;
            System.out.println(message);
            return message;
        } else {
            String message = "Failed to insert Patient in database";
            System.out.println(message);
            return message;
        }
    }

    public static void addRating(Rating rating){


        DataBaseManager.insertRating(rating);

        ratings.add(rating);
        System.out.println("Rating added successfully");



    }

    public static ArrayList<Rating> getRatings(){
        return ratings;
    }

    public static void addAppointment(Appointment appt){
        DataBaseManager.insertAppointment(appt);
        appointments.add(appt);
        System.out.println("Appointment added successfully");
    }

    public static double calculateDoctorAvgRating(int doctorID){
        int ratingCount =0;
        double ratingCountTotal = 0;
        for(Rating rating: ratings){
            if(rating.getDoctorID() == doctorID){
                ratingCount++;
                ratingCountTotal += rating.getScore();
            }
        }
        if(ratingCount == 0){
            return 0;
        }
        return ratingCountTotal/ratingCount;

    }



}
