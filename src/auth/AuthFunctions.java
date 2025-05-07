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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
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
            registeringUser.setPassword( hash( registeringUser.getPassword() ) ); // hash the password you get froom user before signing up
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
            // System.out.println("User has signed up, now login");
        }

    }

    public static boolean authenticateUser(String email, String pass){
        // first check if the email exists
        //if email exists, check if passwords match
        try {
            if (checkIfEmailExists(email)) {

                // check if passwords match
                Optional<User> userLoggin = SystemManager.findUser(email);


                if (userLoggin.isPresent()) {
                    User foundUser = userLoggin.get();
                    if (foundUser.getPassword().equals( hash(pass) )) {
                        System.out.println("logged in user");
                        System.out.println("Starting session...");
                        // SystemManager.startSession(foundUser);

                        // System.out.println(foundUser.getName());
                        System.out.println("User is a: " + foundUser.getUserType());


                        decideUserJourney(foundUser);
                        return true;
                    } else {
                        System.out.println("Passwords do not match");
                        AlertHelper.showError("Error", "Passwords do not match");

                    }
                } else {
                    System.out.println("User is not found");
                    AlertHelper.showError("User is not found");
                    return false;

                }


            } else {
                AlertHelper.showError("Email does not exist");
                System.out.println("Email does not exist");
                return false;
            }
            return false;
        }
        catch(Exception e){
            e.printStackTrace();
            AlertHelper.showError("Unexpected Error", "An unexpected error occurred during login.");
            return false;
        }
    }


    public static void decideUserJourney(User u){
        switch(u.getUserType()){
            case "ADMIN":
                new AdminDashboard( (Admin) u);
                break;
            case "DOCTOR":
                new DoctorDashboard( (Doctor) u);
                break;
            case "PATIENT":
                new PatientDashboard( (Patient) u);
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




    public static String hash(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Perform hashing
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0'); // Add leading zero
                hexString.append(hex);
            }
            return hexString.toString(); // Return hashed string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e);
        }
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
