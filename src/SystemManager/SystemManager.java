package SystemManager;

import adminModules.Admin;
import doctorModules.Doctor;
import models.Notifications;
import models.User;
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

    private static ArrayList<User> session = new ArrayList<>();


    public static void startSession(User user){
        session.add(user);
    }
    public static ArrayList<User> getSession(){
        return session;
    }

    public static void deleteSession(){
        session.clear();
        System.out.println("Session has been flushed (user logged out)");
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

    public static void addUser(User user) {
        users.add(user);
    }
    public static void addToPendingDoctorList(Doctor dr){
        pendingDoctors.add(dr);
        System.out.println("added doctor to pending list, awaiting approval");
    }
    public static void addApprovedDoctor(Doctor dr){
        approvedDoctors.add(dr);
        System.out.println("added doctor");
    }
    public static void addDoctor(Doctor dr) {
        doctors.add(dr);
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

    public static void addNotification(Notifications notif){
        allNotifications.add(notif);

    }



    public static ArrayList<Doctor> getPendingDoctors() {
        return pendingDoctors;
    }

    public static void addPatient(Patient p){
        patients.add(p);

        System.out.println("added patient");
    }



}
