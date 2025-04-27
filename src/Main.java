import adminModules.Admin;
import auth.AuthFunctions;
import doctorModules.Doctor;
import patientModules.Patient;


import java.util.Scanner;
public class Main {

    public static void main(String[] args){

        // creating a new doctor user {3 user types, admin, patient, doctor}
        // for admins, keep a default admin, which means they will always be approved
        Admin admin1 = new Admin(1, 1, "root", "admin", "0817266158", "01/02/2000", true, "ADMIN", "admin@gmail.com", "admin123", "MALE");
        Admin admin2 = new Admin(2, 2, "superuser", "admin2", "0812345678", "01/05/1990", true, "ADMIN", "admin2@gmail.com", "super@admin", "FEMALE");

        Doctor doctor1 = new Doctor(3, 1, "path/to/medicalCert1.pdf", 5, "Cardiologist", "John", "Smith", "0812340000", "1985/06/15", false, "DOCTOR", "johnsmith@gmail.com", "Pass@123", "MALE");
        Doctor doctor2 = new Doctor(4, 2, "path/to/medicalCert2.pdf", 8, "Dermatologist", "Sarah", "Connor", "0813450001", "1982/03/22", false, "DOCTOR", "sarahconnor@gmail.com", "Skin@123", "FEMALE");
        Doctor doctor3 = new Doctor(5, 3, "path/to/medicalCert3.pdf", 2, "Dentist", "Mike", "Tyson", "0814560002", "1990/01/30", false, "DOCTOR", "miketyson@gmail.com", "Teeth@123", "MALE");

        Patient patient1 = new Patient(6, 1, "AID123456", "Jane", "Doe", "0815670003", "1998/03/12", false, "PATIENT", "janedoe@gmail.com", "Jane@456", "FEMALE");
        Patient patient2 = new Patient(7, 2, "AID654321", "Tom", "Hardy", "0816780004", "1995/07/08", false, "PATIENT", "tomhardy@gmail.com", "Tom@789", "MALE");
        Patient patient3 = new Patient(8, 3, "AID111222", "Emily", "Blunt", "0817890005", "1992/11/22", false, "PATIENT", "emilyblunt@gmail.com", "Emily@123", "FEMALE");







        Scanner scan = new Scanner(System.in);

        System.out.println("Enter email: ");
        String email = scan.next();
        System.out.println("Enter password: ");
        String pass = scan.next();

        boolean login = AuthFunctions.authenticateUser(email, pass);
        if(login){
            System.out.println("Successfully logged in");
        }else{
            System.out.println("login unsuccessful");
        }





    }

}
