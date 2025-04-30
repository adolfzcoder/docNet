package SystemManager;

import adminModules.Admin;
import doctorModules.Doctor;
import models.Notifications;
import models.User;
import patientModules.Appointment;
import patientModules.Patient;

import java.util.ArrayList;
import java.util.Optional;

public class SystemManager {

    // this class stores array lists of our data, it also has helper methods to get or add data, like addDoctor()

    private static ArrayList<Doctor> approvedDoctors = new ArrayList<>();
    private static ArrayList<Doctor> pendingDoctors = new ArrayList<>();
    private static ArrayList<Admin> admins= new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Notifications> allNotifications = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Appointment> pendingAppointments = new ArrayList<>();
    private static ArrayList<Appointment> approvedAppointments = new ArrayList<>();
    private static ArrayList<Appointment> declinedAppointments = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static ArrayList<User> session = new ArrayList<>(); // stores the logged in users details


    public static void startSession(User user){
        session.add(user);
    }
    public static ArrayList<User> getSession(){
        return session;
    }

    public static ArrayList<Appointment> getPendingAppointments() {
        return pendingAppointments;
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
    public static String addAppointment(Appointment appt){
        appointments.add(appt);
        String message = "Successfully added appointment";
        System.out.println(message);
        return message;
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

    public static ArrayList<Notifications> getAllNotifications() {
        return allNotifications;
    }

    public static String addUser(User user) {
        users.add(user);
        String message = "User has been added to user list";
        return message;
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
    public static String addDoctor(Doctor dr) {
        doctors.add(dr);
        String message = "Doctor was added successfully";
        System.out.println(message);
        return message;
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

    public static void addAdmin(Admin ad){
        admins.add(ad);
    }

    public static String addNotification(Notifications notif){
        allNotifications.add(notif);
        String message = "Notification was added succesfully";

        System.out.println(message);
        return message;

    }



    public static ArrayList<Doctor> getPendingDoctors() {
        return pendingDoctors;
    }

    public static String addPatient(Patient p){
        patients.add(p);
        String message = "Patient successfully added";

        System.out.println(message);
        return message;

    }



}
