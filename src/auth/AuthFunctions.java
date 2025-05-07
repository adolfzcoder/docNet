package auth;

import adminmodules.AdminDashboard;
import doctormodules.DoctorDashboard;
import patientmodules.PatientDashboard;
import storage.SystemManager;
import adminmodules.Admin;
import doctormodules.Doctor;
import models.User;
import models.Patient;
import utils.AlertHelper;

import java.util.Optional;

public class AuthFunctions {

    /**
     * Registers a new user (Doctor, Patient, or Admin) into the system.
     *
     * This method first checks if the provided email already exists in the system.
     * If it does not, it proceeds to:
     * - Add the user to the system's main user list.
     * - Depending on the userType:
     *   - "DOCTOR": adds to the doctor list and the pending doctor approval list.
     *   - "PATIENT": Patient, adds to the patient list.
     *   - "ADMIN"  : adds to the admin list.
     *
     * Note:
     * - MAKE A USERS CLASS FIRST BEFORE CALLING, BECAUSE YOU PASS IN THE USER THATS REGISTERING INTO THIS
     *  FUNCTION
     * - If the email already exists, the user is not added.
     * Here is how to use it
     * * Doctor newDoctor = new Doctor(userID, doctorID, medicalCertificate, yearsOfXP, specialisation,
     *                                 firstName, lastName, phoneNumber, telephone, dob,
     *                                isApproved, userType, email, password, gender, isBooked);
     *
     *
     *  * AuthFunctions.signUp(newDoctor);
     */
    public static void signUp(User registeringUser){

        if( !checkIfEmailExists(registeringUser.getEmail()) ){
            SystemManager.addUser(registeringUser);

            if(registeringUser.getUserType().equalsIgnoreCase("DOCTOR")){


                // the doctor is inserted into the system manager list, which also inserts into the db

                SystemManager.addDoctor((Doctor) registeringUser);
                SystemManager.addToPendingDoctorList((Doctor) registeringUser);


            }else if(registeringUser.getUserType().equalsIgnoreCase("PATIENT")){
                // the admin is inserted into the system manager list, which also inserts into the db

                SystemManager.addPatient((Patient)registeringUser);

            } else {
                // admin
                // the admin is inserted into the system manager list, which also inserts into the db
                SystemManager.addAdmin((Admin) registeringUser);

            }
            // SystemManager.addUser(registeringUser);
            System.out.println(registeringUser.getUserType());
            System.out.println("User has signed up, now login");
        }

    }

    public static boolean authenticateUser(String email, String pass) {


        try {


            if (!checkIfEmailExists(email)) {
                System.out.println("Email does not exist");
                AlertHelper.showError("Error", "Email does not exist");
                return false;
            }

            Optional<User> userLoggin = SystemManager.findUser(email);
            if (userLoggin.isEmpty()) {
                System.out.println("User not found");
                AlertHelper.showError("Error", "User not found");
                return false;
            }

            User foundUser = userLoggin.get();

            if (!foundUser.getPassword().equalsIgnoreCase(pass)) {
                System.out.println("Passwords do not match");
                AlertHelper.showError("Error", "Passwords do not match");
                return false;
            }

            // Authentication successful
            System.out.println("Logged in user");
            System.out.println("Starting session...");
            System.out.println("User is a: " + foundUser.getUserType());

            decideUserJourney(foundUser);
            return true;
        }
        catch (Exception e) {
            System.err.println("Authentication error: " + e.getMessage());
            e.printStackTrace();
            AlertHelper.showError("Error", "Something went wrong during login.");
            return false;
        }
    }

    public static void decideUserJourney(User foundUser){
        switch(foundUser.getUserType()){
            case "ADMIN":
                new AdminDashboard((Admin) foundUser);
                break;
            case "DOCTOR":
                new DoctorDashboard((Doctor) foundUser);
                break;
            case "PATIENT":
                new PatientDashboard((Patient)foundUser);
                break;
            default:
                System.out.println("User type not found");
        }
    }
    public static void logout(){
        System.out.println("Logging out");
        SystemManager.flushSession();
        System.out.println("User logged out successfully");
    }
    public static String hashPassword(String pass){
        // implement hashing functionality
        return "";
    }

    public static boolean checkIfEmailExists(String email) {
        for (User userDB : SystemManager.getUsers()) {
            if (email.equalsIgnoreCase(userDB.getEmail())) {
                return true;
            }
        }
        return false;
    }

}
