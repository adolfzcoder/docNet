package models;

import Storage.DataBaseManager;
import adminModules.Admin;
import doctorModules.Doctor;
import doctorModules.Office;
import doctorModules.Prescription;
import patientModules.Appointment;
import patientModules.Patient;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Optional;

public class SystemManager {

    // this class stores array lists of our data, it also has helper methods to get or add data, like addDoctor()
    private static ArrayList<User> users = fetchUsers(); //fetch all users from the db
    private static ArrayList<Office> offices = fetchOffices(); // goes to db and fetches all the offices
    private static ArrayList<Admin> admins= fetchAdmins();
    private static ArrayList<Patient> patients = fetchPatients();
    private static ArrayList<Doctor> doctors = fetchDoctors();
    private static ArrayList<Appointment> appointments = fetchAppointments();
    private static ArrayList<Prescription> prescriptions = fetchPrescriptions();

    private static ArrayList<Doctor> approvedDoctors = new ArrayList<>();
    private static ArrayList<Doctor> pendingDoctors = new ArrayList<>();
    private static ArrayList<Notifications> allNotifications = new ArrayList<>();
    private static ArrayList<Appointment> pendingAppointments = new ArrayList<>();
    private static ArrayList<Appointment> approvedAppointments = new ArrayList<>();
    private static ArrayList<Appointment> declinedAppointments = new ArrayList<>();
    private static ArrayList<User> session = new ArrayList<>(); // stores the logged in users details
    private static ArrayList<Doctor> officeDoctors = new ArrayList<>();




    public static void startSession(User user){
        session.add(user);
    }
    public static ArrayList<User> getSession(){
        return session;
    }


    public static ArrayList<Prescription> fetchPrescriptions(){
        return DataBaseManager.getPrescriptions();
    }
    public static ArrayList<User> fetchUsers(){
        return DataBaseManager.getUsers();
    }
    public static ArrayList<Office> fetchOffices(){
        return (ArrayList<Office>) DataBaseManager.getOffices();
    }

    public static ArrayList<Admin> fetchAdmins(){
        return DataBaseManager.getAdmins();
    }

    public static ArrayList<Patient> fetchPatients(){
        return DataBaseManager.getPatients();
    }

    public static ArrayList<Doctor> fetchDoctors(){
        return DataBaseManager.getDoctors();
    }
    public static ArrayList<Appointment> fetchAppointments(){
        return DataBaseManager.getAppointments();
    }

    public static ArrayList<Doctor> getOfficeDoctors(int officeID){

        for(Office office: offices){

            if(office.getOfficeID() ==  officeID){
                int doctorID = office.getDoctorID;
                officeDoctors.add(findDoctor(doctorID));


            }else{
                System.out.println("Office id not found");
            }
        }
        return officeDoctors;
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
    public static ArrayList<Office> getOffices(){
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
    public static String addAppointment(Appointment appt){
        appointments.add(appt);

        DataBaseManager.insertAppointment(appt);

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
        DataBaseManager.insertUser(user);
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
        DataBaseManager.insertDoctor(dr);

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
        DataBaseManager.insertAdmin(ad);
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
        DataBaseManager.insertPatient(p);
        String message = "Patient successfully added";

        System.out.println(message);
        return message;

    }



}
