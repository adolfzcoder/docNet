package auth;

import adminmodules.AdminDashboard;
import doctormodules.DoctorDashboardController;
import models.*;
import storage.SystemManager;
import utils.AlertHelper;
import utils.NavigatorHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

            System.out.println("Password before: "+ registeringUser.getPassword());
            registeringUser.setPassword( hash( registeringUser.getPassword() ) ); // hash the password you get froom user before signing up
            System.out.println("Password after hashing: "+registeringUser.getPassword());

            SystemManager.startSession(registeringUser);
            SystemManager.addUser(registeringUser);

            if(registeringUser.getUserType().equalsIgnoreCase("DOCTOR")){

                // the doctor is inserted into the system manager list, which also inserts into the db
                System.out.println("Doctor ID:"+SystemManager.getSession().get(0).getUserTypeID());
                System.out.println("First name doctor:"+SystemManager.getSession().get(0).getFirstName());
                SystemManager.addDoctor((Doctor) registeringUser);
                SystemManager.addOffice((Doctor) registeringUser);
                SystemManager.addToPendingDoctorList((Doctor) registeringUser);


                System.out.println("Inserted Doctor Successfully");
                AlertHelper.showSuccess("Successfully registered");
            }else if(registeringUser.getUserType().equalsIgnoreCase("PATIENT")){
                // the admin is inserted into the system manager list, which also inserts into the db

                SystemManager.addPatient((Patient)registeringUser);

                System.out.println("Inserted Patient Successfully");
                AlertHelper.showSuccess("Successfully registered");

            } else {
                // admin
                // the admin is inserted into the system manager list, which also inserts into the db
                SystemManager.addAdmin((Admin) registeringUser);
                System.out.println("Inserted Admin Succesfully");
                AlertHelper.showSuccess("Successfully registered");

            }

        }else{
            System.out.println("Email exists");
            AlertHelper.showError("Email already exists");
        }

    }


    public static boolean authenticateUser(String email, String password) {
        if (!checkIfEmailExists(email)) {
            AlertHelper.showError("Email does not exist");
            System.out.println("Email does not exist");
            return false;
        }

        Optional<User> userOptional = SystemManager.findUser(email);
        if (userOptional.isEmpty()) {
            AlertHelper.showError("User is not found");
            System.out.println("User is not found");
            return false;
        }

        User user = userOptional.get();
        boolean passwordMatches = user.getPassword().equals(hash(password));

        if (!passwordMatches) {
            AlertHelper.showError("Error", "Passwords do not match");
            System.out.println("Passwords do not match");
            return false;
        }
        SystemManager.startSession(user);

        // Successful login
        System.out.println("Found user");
        System.out.println("Logged in user");
        System.out.println("Starting session...");
        System.out.println("User is a: " + user.getUserType());
        AlertHelper.showSuccess("Successfully logged in");


        decideUserJourney(user);
        return true;
    }


    public static void decideUserJourney(User u){
        switch(u.getUserType()){
            case "ADMIN":
                new AdminDashboard( (Admin) u);
                break;
            case "DOCTOR":

                NavigatorHelper.loadScene("doctormodules/DoctorDashboard.fxml", "Doctor Dashboard");


                break;
            case "PATIENT":
                NavigatorHelper.loadScene("patientmodules/patientDashboard.fxml", "Doctor Dashboard");

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
