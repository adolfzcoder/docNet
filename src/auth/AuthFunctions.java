package auth;

import Models.SystemManager;
import Models.Admin;
import Models.Doctor;
import Models.User;
import Models.Patient;

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

    public static boolean authenticateUser(String email, String pass){
        // first check if the email exists
        //if email exists, check if passwords match

        if(checkIfEmailExists(email)){

            // check if passwords match
            Optional<User> userLoggin = SystemManager.findUser(email);


            if(userLoggin.isPresent()){
                User foundUser = userLoggin.get();
                if(foundUser.getPassword().equalsIgnoreCase(pass)){
                    System.out.println("logged in user");
                    System.out.println("Starting session...");
                    SystemManager.startSession(foundUser);

                    System.out.println(foundUser.getName());
                    System.out.println("User is a: "+ foundUser.getUserType());
                    return true;
                }else{
                    System.out.println("Passwords do not match");
                }
            }else{
                System.out.println("User is not found");
            }



        }else{
            System.out.println("Email does not exist, signup");
        }
        return false;
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
