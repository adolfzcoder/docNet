import adminModules.Admin;
import auth.AuthFunctions;
import doctorModules.Doctor;
import models.User;
import patientModules.Patient;


import java.util.Scanner;
public class Main {

    public static void main(String[] args){

        // creating a new doctor user {3 user types, admin, patient, doctor}
        // for admins, keep a default admin, which means they will always be approved
//        Admin admin1 = new Admin(1, 1, "root", "admin", "0816166875", "0817266158", "01/02/2000", true, "ADMIN", "admin@gmail.com", "admin123", "MALE");
//        Admin admin2 = new Admin(2, 2, "superuser", "admin2", "0816166875", "0812345678", "01/05/1990", true, "ADMIN", "admin2@gmail.com", "super@admin", "FEMALE");
//        // AuthFunctions.signUp(admin1);
//        Doctor doctor1 = new Doctor(3, 1, "path/to/medicalCert1.pdf", 5, "Cardiologist", "John", "Smith", "0812340000","0812340000", "1985/06/15", false, "DOCTOR", "johnsmith@gmail.com", "Pass@123", "MALE");
//        Doctor doctor2 = new Doctor(4, 2, "path/to/medicalCert2.pdf", 8, "Dermatologist", "Sarah", "Connor", "0812340000", "0813450001", "1982/03/22", false, "DOCTOR", "sarahconnor@gmail.com", "Skin@123", "FEMALE");
//        Doctor doctor3 = new Doctor(5, 3, "path/to/medicalCert3.pdf", 2, "Dentist", "Mike", "Tyson", "0812340000", "0814560002", "1990/01/30", false, "DOCTOR", "miketyson@gmail.com", "Teeth@123", "MALE");
//
//        Patient patient1 = new Patient(6, 1, "AID123456", "Jane", "Doe", "0815670003", "0815670003", "1998/03/12", false, "PATIENT", "janedoe@gmail.com", "Jane@456", "FEMALE");
//        Patient patient2 = new Patient(7, 2, "AID654321", "Tom", "Hardy", "0815670003", "0816780004", "1995/07/08", false, "PATIENT", "tomhardy@gmail.com", "Tom@789", "MALE");
//        Patient patient3 = new Patient(8, 3, "AID111222", "Emily", "Blunt", "0815670003", "0817890005", "1992/11/22", false, "PATIENT", "emilyblunt@gmail.com", "Emily@123", "FEMALE");







        Scanner scan = new Scanner(System.in);
        System.out.println("WELCOME TO Doc NET, PRESS anything to continure, press q to exit");

        String quit = "2";
        while(!quit.equals("q")){
            quit = scan.next();

            System.out.println("Enter 1 to login: ");
            System.out.println("Enter 2 to register: ");
            System.out.println("Enter 3 to logout: ");
            int choice = scan.nextInt();

            if(choice == 1){
                login();
            } else if (choice == 2) {
                signup();
            }else if(choice == 3){
                AuthFunctions.logout();
            }
            else{
                System.out.println("Wrong input, try again or press q to exit");
            }


        }






    }


    public static void login(){
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
    public static void signup() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Telephone: ");
        String telephone = scanner.nextLine();

        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();

        System.out.print("Enter Gender (MALE/FEMALE): ");
        String gender = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter User Type (DOCTOR / PATIENT / ADMIN): ");
        String userType = scanner.nextLine().trim().toUpperCase();

        int userID = 0;

        switch (userType) {
            case "DOCTOR":
                System.out.print("Enter Medical Certificate Path: ");
                String certificate = scanner.nextLine();

                System.out.print("Enter Years of Experience: ");
                int yearsXP = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter Specialisation: ");
                String specialisation = scanner.nextLine();

                int doctorID = 0;
                new Doctor(userID, doctorID, certificate, yearsXP, specialisation, firstName, lastName,
                        phoneNumber, telephone, dob, false, "DOCTOR", email, password, gender);
                break;

            case "PATIENT":
                System.out.print("Enter Medical Aid Number: ");
                String medAidNumber = scanner.nextLine();
                int patientID = 0;

                new Patient(userID, patientID, medAidNumber, firstName, lastName, phoneNumber, telephone,
                        dob, false, "PATIENT", email, password, gender);
                break;

            case "ADMIN":
                int adminID = 0;

                new Admin(userID, adminID, firstName, lastName, phoneNumber, telephone,
                        dob, true, "ADMIN", email, password, gender);
                break;

            default:
                System.out.println("Invalid user type.");
        }

    }
}


