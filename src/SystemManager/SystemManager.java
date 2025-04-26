package SystemManager;

import adminModules.Admin;
import doctorModules.Doctor;
import functions.Notifications;
import patientModules.Patient;

import java.util.ArrayList;

public class SystemManager {

    // this class stores array lists of our data, it also has helper methods to get or add data, like addDoctor()

    private static ArrayList<Doctor> approvedDoctors = new ArrayList<>();
    private static ArrayList<Doctor> pendingDoctors = new ArrayList<>();
    private static ArrayList<Admin> admins= new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Notifications> allNotifications = new ArrayList<>();

    public static void addToPendingDoctorList(Doctor dr){
        pendingDoctors.add(dr);
        System.out.println("added doctor to pending list, awaiting approval");
    }
    public static void addDoctor(Doctor dr){
        approvedDoctors.add(dr);
        System.out.println("added doctor");
    }

    public static ArrayList<Doctor> getApprovedDoctors(){
        return approvedDoctors;
    }

    public static ArrayList<Doctor> getPendingDoctors() {
        return pendingDoctors;
    }

    public static void addPatient(Patient p){
        patients.add(p);
        System.out.println("added patient");
    }



}
