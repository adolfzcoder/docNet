package AdolfsPackage;

import AdolfsPackage.Admin;
import AdolfsPackage.Doctor;

import java.util.Scanner;

public class Tests {

    public static void main(String[] args){




      /*
                                TESTING ADMIN-DOCTOR FUNCTIONS
       */

        // creating a new doctor user {3 user types, admin, patient, doctor}
        // for admins, keep a default admin, which means they will always be approved
        Admin root = new Admin(1,1,"root", "admin", "0817266158", "01/02/2000", true, "ADMIN", "admin@gmail.com", "admin123", "MALE");
        // get these from the user
        int userID = 2;
        int doctorID = 1;
        String medicalCertificate = "C:/Users/adolf/Downloads/EAP Green Hydrogen report v4.pdf";
        int yearsOfXP = 3;
        String specialisation = "Dentist";
        String firstName = "John";
        String lastName = "Smith";
        String telephone = "123484729";
        String dob = "2005/05/25";
        String email = "doctor@gmail.com";
        String password = "Pass@123";
        String gender = "MALE";

        Doctor doctor1 = new Doctor(userID, doctorID, medicalCertificate, yearsOfXP, specialisation, firstName, lastName, telephone, dob, false, "DOCTOR", email, password, gender);


        int userID2 = 3;
        int doctorID2 = 2;
        String medicalCertificate2= "";
        String fName ="Felix";
        String lName = "Kijelbourg";
        Doctor doctor2 = new Doctor(3, 2, "C:/Users/adolf/Downloads/ISS611S Test 1 2024_ISS611S_2024.pdf", 4, "Dentist", "Jenifer", "Kellogs", "081678875", "1998/01/01", false, "DOCTOR", "doctor2@gmail.com", "123", "FEMALE");
        // once a user creates a doctor account, allow the admin to verify their details

        root.verifyDoctor(doctor1);
        // doctor1.createUser(firstName, lastName, telephone, dob, userType, email);

        // root.displayApprovedDoctors();
        // root.displayNotApprovedDoctors();










    }

}
