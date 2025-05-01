package auth;

import Storage.DataBaseManager;
import SystemManager.SystemManager;
import adminModules.Admin;
import doctorModules.Doctor;
import models.User;
import patientModules.Patient;

import java.util.ArrayList;
import java.util.Optional;

public class AuthFunctions {

    public static void signUp(User registeringUser){


        createUser(registeringUser, checkIfEmailExists( registeringUser.getEmail() ));

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
    public static void createUser(User registeringUser, boolean emailExists){

        if( !emailExists ){
            SystemManager.addUser(registeringUser);
            DataBaseManager.insertUser(registeringUser);

            if(registeringUser.getUserType().equalsIgnoreCase("DOCTOR")){



                SystemManager.addDoctor((Doctor) registeringUser);
                SystemManager.addToPendingDoctorList((Doctor) registeringUser);

            }else if(registeringUser.getUserType().equalsIgnoreCase("PATIENT")){

                SystemManager.addPatient((Patient)registeringUser);

            } else {
                // admin

                SystemManager.addAdmin((Admin) registeringUser);

            }
            // SystemManager.addUser(registeringUser);
            System.out.println(registeringUser.getUserType());
            System.out.println("User has signed up, now login");
        }

    }

    public static void logout(){
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
