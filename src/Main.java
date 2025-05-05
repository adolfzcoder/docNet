import Models.SystemManager;
import Models.Admin;
import auth.AuthFunctions;
import Models.Doctor;
import Models.User;
import Models.Appointment;
import Models.Patient;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // public static User sessionUser = SystemManager.getSession().getFirst();
    // public static String loggedInUserType = sessionUser.getUserType();

    // Centralized error handling function
    private static void displayError(String errorMessage, String errorType) {
        System.err.println("[" + errorType + "] " + errorMessage);
        // In a real application, you could replace this with your custom error display mechanism
        // For example: ErrorDialog.show(errorMessage, errorType);
    }

    public static void main(String[] args) {
        try {
            User sessionUser = SystemManager.getSession().getFirst();
            String loggedInUserType = sessionUser.getUserType();
        } catch (Exception e) {
            displayError("Error: " + e.getMessage(), "Start up error");
        }

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
        System.out.println("WELCOME TO Doc NET, PRESS anything to continue, press q to exit");

        String quit = "";

        while (!quit.equalsIgnoreCase("q")) {

            // if user isn't logged in, then ask whether they want to login or reg
            if (SystemManager.getSession().isEmpty()) {
                System.out.println("Enter 1 to login: ");
                System.out.println("Enter 2 to register: ");
                System.out.println("Enter 3 to logout: ");
                System.out.println("Press 'q' to exit");

                String input = scan.next();

                if (input.equalsIgnoreCase("q")) {
                    quit = "q";
                    System.out.println("Exiting...");
                } else {
                    try {
                        int choice = Integer.parseInt(input);
                        switch (choice) {
                            case 1:
                                login();
                                break;
                            case 2:
                                signup();
                                break;
                            case 3:
                                AuthFunctions.logout();
                                break;
                            default:
                                displayError("Invalid option. Try again.", "Input Error");
                        }
                    } catch (NumberFormatException e) {
                        displayError("Please enter a valid number or 'q' to quit.", "Input Format Error");
                    } catch (Exception e) {
                        displayError("An unexpected error occurred: " + e.getMessage(), "General Error");
                        e.printStackTrace();
                    }
                }

            } else {
                switch (SystemManager.getSession().getFirst().getUserType()) {
                    case "DOCTOR":
                        break;
                    case "ADMIN":
                        adminChoices();
                        break;
                    case "PATIENT":
                        patientChoices();
                        break;
                    default:
                        System.out.println("UNKNOWN user type");
                }
            }
        }
    }

    public static void patientChoices() {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Welcome Patient!");
            System.out.println("What would you like to do?");

            System.out.println("1 Make appointment. ");
            System.out.println("2 View all appointments. ");
            System.out.println("3 View Prescriptions. ");
            System.out.println("4 View Prescription. ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    makeAppointment(scan);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("invalid choice, please try again. ");
            }
        } catch (Exception e) {
            displayError("Error in patientChoices(): " + e.getMessage(), "Patient error");
        }
    }

    public static void makeAppointment(Scanner scanner) {
        try {
            int patientID = SystemManager.getSession().getFirst().getUserTypeID();

            System.out.println("Enter Doctor ID: ");
            int doctorID = scanner.nextInt();

            System.out.println("Enter Appointment Date (YYYY-MM-DD): ");
            String dateString = scanner.next();
            LocalDate appointmentDate = LocalDate.parse(dateString);

            System.out.println("Enter Appointment Time (HH:mm): ");
            String timeString = scanner.next();
            LocalTime appointmentTime = LocalTime.parse(timeString);

            scanner.nextLine();

            System.out.println("Enter Reason for Visit: ");
            String reason = scanner.nextLine();

            Appointment newAppointment = new Appointment(0, patientID, doctorID, appointmentDate, appointmentTime, reason, "PENDING");

            System.out.println("Appointment Created Successfully!");

            System.out.println("Appointment ID: " + newAppointment.getAppointmentID());
            System.out.println("Patient ID: " + newAppointment.getPatientID());
            System.out.println("Doctor ID: " + newAppointment.getDoctorID());
            System.out.println("Appointment Date: " + newAppointment.getAppointmentDate());
            System.out.println("Appointment Time: " + newAppointment.getAppointmentTime());
            System.out.println("Reason for Visit: " + newAppointment.getReasonForVisit());
            System.out.println("Status: " + newAppointment.getStatus());
        } catch (Exception e) {
            displayError("Error in makeAppointment: " + e.getMessage(), "Appointment error");
        }
    }

    public static void adminChoices() {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.println("Welcome Admin!");
            System.out.println("What would you like to do?");

            System.out.println("1 Approve appointments. ");
            System.out.println("2 Approve Doctors. ");
            System.out.println("3 Add an office. ");
            System.out.println("4 View office balance. ");
            System.out.println("5 View all appointments. ");
            System.out.println("6 Make prescription");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    ArrayList<Appointment> pendingAppts = SystemManager.getPendingAppointments(SystemManager.getSession().getFirst().getUserTypeID());
                    for (Appointment appt : pendingAppts) {
                        System.out.println(appt);
                    }
                    System.out.println("Pending appointments displayed");
                    break;
                case 2:
                    System.out.println("Doctor has been displayed");
                    break;
                case 3:
                    System.out.println("Office has been added");
                    break;
                case 4:
                    System.out.println("Office balance displayed");
                    break;
                case 5:
                    System.out.println("All appointments displayed");
                    break;
                default:
                    System.out.println("invalid entry try again");
            }
        } catch (RuntimeException e) {
            displayError("Error in adminChoices(): " + e.getMessage(), "Admin Error");
        }
    }

    public static void login() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter email: ");
            String email = scan.next();
            System.out.println("Enter password: ");
            String pass = scan.next();

            boolean login = AuthFunctions.authenticateUser(email, pass);
            if (login) {
                System.out.println("Successfully logged in");
            } else {
                System.out.println("login unsuccessful");
            }
        } catch (Exception e) {
            displayError("Error in login(): " + e.getMessage(), "Login Error");
        }
    }

    public static void signup() {
        try {
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

            // make method do get the last inserted id, then incremenet (generateID)
            int userID = 0;

            switch (userType) {
                case "DOCTOR":
                    System.out.print("Enter Medical Certificate Path: ");
                    String certificate = scanner.nextLine();

                    System.out.print("Enter Years of Experience: ");
                    int yearsXP = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Specialisation: ");
                    String specialisation = scanner.nextLine();
                    boolean isBooked = false;

                    int doctorID = 0;
                    new Doctor(userID, doctorID, certificate, yearsXP, specialisation, firstName, lastName,
                            phoneNumber, telephone, dob, false, "DOCTOR", email, password, gender, isBooked);
                    break;

                case "PATIENT":
                    System.out.print("Enter Medical Aid Number: ");
                    int medAidNumber = scanner.nextInt();
                    int patientID = 0;
                    System.out.println("Enter balance: ");
                    double balance = scanner.nextDouble();

                    new Patient(userID, patientID, medAidNumber, firstName, lastName, phoneNumber, telephone,
                            dob, false, "PATIENT", email, password, gender, balance);
                    break;

                case "ADMIN":
                    int adminID = 0;
                    new Admin(userID, adminID, firstName, lastName, phoneNumber, telephone,
                            dob, true, "ADMIN", email, password, gender);
                    break;

                default:
                    System.out.println("Invalid user type.");
            }
        } catch (Exception e) {
            displayError("Error in signup(): " + e.getMessage(), "Signup Error");
        }
    }
}