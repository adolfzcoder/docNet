import auth.AuthFunctions;
import models.*;
import storage.DataBaseManager;
import storage.StorageFunctions;
import storage.SystemManager;
import validations.Validations;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static storage.DataBaseManager.updateAppointmentStatus;

public class Main {

    private Main() {
        // prevent instantiation, kept giving error that duplicate class, since all methods were static
    }


    public static void displayError(String errorMessage, String errorType) {
        System.err.println("[" + errorType + "] " + errorMessage);

    }

    public static void main(String[] args) {
        try {
            // this takes some time, so to reduce latency, we initialise our lists at the begining of the app
            System.out.println("Initialising lists, may take up to 10 seconds");
            SystemManager.initializeLists();
            System.out.println("lists initilised");
            // checking if session exists ie there is a user already logged in
            if (!SystemManager.getSession().isEmpty()) {
                System.out.println("User is logged in ");
                User sessionUser = SystemManager.getSession().getFirst();
                String loggedInUserType = sessionUser.getUserType();
                System.out.println("Current user: " + sessionUser.getFirstName() + " " + sessionUser.getLastName());
            }
            System.out.println("Initialising lists complete");
        } catch (Exception e) {
            displayError("Error: " + e.getMessage(), "Start up error");
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("WELCOME TO Doc NET, PRESS anything to continue, press q to exit");

        String quit = "";

        while (!quit.equalsIgnoreCase("q")) {

            // if user isn't logged in, then ask whether they want to login or reg
            if (SystemManager.getSession().isEmpty()) {
                System.out.println("Enter 1 to login: ");
                System.out.println("Enter 2 to register: ");
                System.out.println("Enter 3 to logout: ");
                System.out.println("Enter 4 to reload database lists");
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
                            case 4:
                                SystemManager.initializeLists();
                                System.out.println("Reloading lists");
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
                        doctorChoices();
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


    public  static void login() {
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

    public  static void signup() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            if(!Validations.validateUserName(firstName)){
                System.out.println("Name not in right format, please try again, Name cannot be empty");

                return;
            }

            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            if(!Validations.validateUserName(lastName)){
                System.out.println("Name not in right format, please try again, cannot be empty");
                return;
            }
            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine();
            if(!Validations.validatePhoneNumber(phoneNumber) || phoneNumber.isEmpty()){
                System.out.println("Phone number not in right format or is empty");
            }


            System.out.print("Enter Telephone: ");
            String telephone = scanner.nextLine();

            System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();
            if(!Validations.validateDateOfBirth(dob) || dob.isEmpty()){
                System.out.println("Date of birth not in right format or is empty");
                return;
            }

            System.out.print("Enter Gender (MALE/FEMALE): ");
            String gender = scanner.nextLine();
            if(!Validations.validateEnumGender(gender) || gender.isEmpty()){
                System.out.println("Gender not in right format");
                return;
            }

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            if(!Validations.validateEmail(email) || email.isEmpty()){
                System.out.println("Email not in right format or is empty");
                return;
            }

            if(!Validations.validateEmail(email)){
                return;
            }
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            System.out.print("Enter User Type (DOCTOR / PATIENT / ADMIN): ");
            String userType = scanner.nextLine().trim().toUpperCase();



            // make method do get the last inserted id, then incremenet (generateID)
            int userID = 0;

            switch (userType) {
                case "DOCTOR":
//                    System.out.print("Enter Medical Certificate Path: ");
//                    String certificate = scanner.nextLine();

                    System.out.print("Enter Years of Experience: ");
                    int yearsXP = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Specialisation: ");
                    String specialisation = scanner.nextLine();

                    System.out.print("Enter office name");
                    String officeName = scanner.nextLine();

                    System.out.println("Enter office Location");
                    String location = scanner.nextLine();

                    System.out.println("Enter opening hours");
                    String openingHours = scanner.nextLine();

                    System.out.println("Enter closing hours");
                    String closingHours = scanner.nextLine();



                    boolean isBooked = false;

                    int doctorID = 0;


                    AuthFunctions.signUp(new Doctor(userID, doctorID, yearsXP, specialisation, firstName, lastName,
                            phoneNumber, telephone, dob, false, "DOCTOR", email, password, gender, isBooked, officeName, location, Time.valueOf(openingHours).toLocalTime(), Time.valueOf(closingHours).toLocalTime()));
                    break;

                case "PATIENT":
                    System.out.print("Enter Medical Aid Number: ");
                    int medAidNumber = scanner.nextInt();
                    int patientID = 0;
                    System.out.println("Enter balance: ");
                    double balance = scanner.nextDouble();

                    AuthFunctions.signUp(new Patient(userID, patientID, medAidNumber, firstName, lastName, phoneNumber, telephone,
                            dob, false, "PATIENT", email, password, gender, balance));
                    break;

                case "ADMIN":
                    int adminID = 0;
                    AuthFunctions.signUp(new Admin(userID, adminID, firstName, lastName, phoneNumber, telephone,
                            dob, true, "ADMIN", email, password, gender));
                    break;

                default:
                    System.out.println("Invalid user type.");
            }
        } catch (Exception e) {
            displayError("Error in signup(): " + e.getMessage(), "Signup Error");
        }
    }

    public  static void patientChoices() {
        try {
            Scanner scan = new Scanner(System.in);
            User currentUser = SystemManager.getSession().getFirst();
            int patientID = currentUser.getUserTypeID();

            System.out.println("Welcome Patient " + currentUser.getFirstName() + "!");
            System.out.println("What would you like to do?");

            System.out.println("1. Make appointment");
            System.out.println("2. View all appointments");
            System.out.println("3. View prescriptions");
            System.out.println("4. Rate a doctor");
            System.out.println("5. Search for a doctor");
            System.out.println("6. Edit your profile");
            System.out.println("7. Return to main menu");
            System.out.println("8. Logout");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    makeAppointment(scan);
                    break;
                case 2:
                    viewAllPatientAppointments(patientID);
                    break;
                case 3:
                    viewPatientPrescriptions(patientID);
                    break;
                case 4:
                    rateDoctor(scan, patientID);
                    break;
                case 5:
                    searchDoctor(scan);
                    break;
                case 6:
                    editPatientProfile();
                    break;
                case 7:
                    AuthFunctions.logout();

                    break;
                case 8:
                    System.out.println("Returning to main menu...");

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } catch (Exception e) {
            displayError("Error in patientChoices(): " + e.getMessage(), "Patient error");
        }
    }

    public  static void makeAppointment(Scanner scanner) {
        try {
            int patientID = SystemManager.getSession().getFirst().getUserTypeID();

            ArrayList<Doctor> doctors = DataBaseManager.getDoctors();
            System.out.println("\nAvailable (Approved) Doctors:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Name | Specialization | Rating");
            System.out.println("----------------------------------------");

            for (Doctor doctor : doctors) {
                if (doctor.getApproved()) {
                    double avgRating = SystemManager.calculateDoctorAvgRating(doctor.getDoctorID());
                    System.out.printf("%d | Dr. %s %s | %s | %.1f/5.0\n",
                            doctor.getDoctorID(),
                            doctor.getFirstName(),
                            doctor.getLastName(),
                            doctor.getSpecialisation(),
                            avgRating);
                }
            }
            System.out.println("----------------------------------------");

            System.out.println("Enter Doctor ID: ");
            int doctorID = scanner.nextInt();

            Doctor selectedDoctor = DataBaseManager.getApprovedDoctorById(doctorID);
            if (selectedDoctor == null) {
                System.out.println("Doctor not found. Please try again.");
                return;
            }

            LocalDate appointmentDate = null;
            while (appointmentDate == null) {
                try {
                    System.out.println("Enter Appointment Date (YYYY-MM-DD): ");
                    String dateString = scanner.next();
                    appointmentDate = LocalDate.parse(dateString);

                    if (appointmentDate.isBefore(LocalDate.now())) {
                        System.out.println("Appointment date must be in the future.");
                        appointmentDate = null;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }

            LocalTime appointmentTime = null;
            while (appointmentTime == null) {
                try {
                    System.out.println("Enter Appointment Time (HH:mm): ");
                    String timeString = scanner.next();
                    appointmentTime = LocalTime.parse(timeString);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format. Please use HH:mm (24-hour format).");
                }
            }

            scanner.nextLine();

            System.out.println("Enter Reason for Visit: ");
            String reason = scanner.nextLine();

            Appointment newAppointment = new Appointment(0, patientID, doctorID, appointmentDate, appointmentTime, reason, "PENDING");
            SystemManager.addAppointment(newAppointment);

            System.out.println("\nAppointment Created Successfully!");
            System.out.println("Appointment Details:");
            System.out.println("----------------------------------------");
            System.out.println("Doctor: Dr. " + selectedDoctor.getFirstName() + " " + selectedDoctor.getLastName());
            System.out.println("Date: " + appointmentDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));
            System.out.println("Time: " + appointmentTime.format(DateTimeFormatter.ofPattern("h:mm a")));
            System.out.println("Reason: " + reason);
            System.out.println("Status: PENDING");
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error in makeAppointment: " + e.getMessage(), "Appointment error");
        }
    }

    public  static void viewAllPatientAppointments(int patientID) {
        try {
            ArrayList<Appointment> patientAppointments = SystemManager.returnAppointmentsByPatientID(patientID);

            if (patientAppointments.isEmpty()) {
                System.out.println("You have no appointments scheduled.");
                return;
            }

            System.out.println("\nYour Appointments:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Date | Time | Doctor | Status");
            System.out.println("----------------------------------------");

            for (Appointment appt : patientAppointments) {
                Doctor doctor = SystemManager.findDoctor(appt.getDoctorID());
                String doctorName = "Unknown";
                if (doctor != null) {
                    doctorName = "Dr. " + doctor.getFirstName() + " " + doctor.getLastName();
                }

                System.out.printf("%d | %s | %s | %s | %s\n",
                        appt.getAppointmentID(),
                        appt.getAppointmentDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                        appt.getAppointmentTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                        doctorName,
                        appt.getStatus());
            }
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error viewing appointments: " + e.getMessage(), "View Error");
        }
    }

    public static void viewPatientPrescriptions(int patientID) {
        try {
            ArrayList<Prescription> allPrescriptions = SystemManager.getPrescriptions();
            ArrayList<Prescription> patientPrescriptions = new ArrayList<>();

            // Filter prescriptions for this patient
            for (Prescription prescription : allPrescriptions) {
                if (prescription.getPatientID() == patientID) {
                    patientPrescriptions.add(prescription);
                }
            }

            if (patientPrescriptions.isEmpty()) {
                System.out.println("You have no prescriptions.");
                return;
            }

            System.out.println("\nYour Prescriptions:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Date | Doctor | Instructions");
            System.out.println("----------------------------------------");

            for (Prescription prescription : patientPrescriptions) {
                Doctor doctor = SystemManager.findDoctor(prescription.getDoctorID());
                String doctorName = "Unknown";
                if (doctor != null) {
                    doctorName = "Dr. " + doctor.getFirstName() + " " + doctor.getLastName();
                }

                System.out.printf("%d | %s | %s | %s\n",
                        prescription.getPrescriptionID(),
                        prescription.getIssueDate(),
                        doctorName,
                        prescription.getInstruction());
            }
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error viewing prescriptions: " + e.getMessage(), "View Error");
        }
    }

    public static void rateDoctor(Scanner scanner, int patientID) {
        try {
            ArrayList<Appointment> patientAppointments = SystemManager.returnAppointmentsByPatientID(patientID);
            ArrayList<Doctor> seenDoctors = new ArrayList<>();

            for (Appointment appt : patientAppointments) {
                if (appt.getStatus().equals("COMPLETED")) {
                    Doctor doctor = SystemManager.findDoctor(appt.getDoctorID());
                    if (doctor != null && !seenDoctors.contains(doctor)) {
                        seenDoctors.add(doctor);
                    }
                }
            }

            if (seenDoctors.isEmpty()) {
                System.out.println("You haven't had any completed appointments with doctors to rate.");
                return;
            }

            System.out.println("\nDoctors you can rate:");
            System.out.println("----------------------------------------");
            for (Doctor doctor : seenDoctors) {
                System.out.printf("%d - Dr. %s %s (%s)\n",
                        doctor.getDoctorID(),
                        doctor.getFirstName(),
                        doctor.getLastName(),
                        doctor.getSpecialisation());
            }
            System.out.println("----------------------------------------");

            System.out.println("Enter Doctor ID to rate: ");
            int doctorID = scanner.nextInt();

            Doctor selectedDoctor = null;
            for (Doctor doctor : seenDoctors) {
                if (doctor.getDoctorID() == doctorID) {
                    selectedDoctor = doctor;
                    break;
                }
            }

            if (selectedDoctor == null) {
                System.out.println("Invalid doctor selection or you haven't seen this doctor.");
                return;
            }

            System.out.println("Enter rating (1-5): ");
            double score = scanner.nextDouble();
            if (score < 1 || score > 5) {
                System.out.println("Rating must be between 1 and 5.");
                return;
            }

            scanner.nextLine();

            System.out.println("Enter review comment: ");
            String review = scanner.nextLine();

            Rating rating = new Rating(0, patientID, doctorID, review, score);
            SystemManager.addRating(rating);

            System.out.println("Thank you for rating Dr. " + selectedDoctor.getFirstName() + " " + selectedDoctor.getLastName());

        } catch (Exception e) {
            displayError("Error rating doctor: " + e.getMessage(), "Rating Error");
        }
    }

    public static void searchDoctor(Scanner scanner) {
        try {
            System.out.println("\nSearch for Doctors");
            System.out.println("1. Search by specialization");
            System.out.println("2. Search by name");

            int searchOption = scanner.nextInt();
            scanner.nextLine();

            ArrayList<Doctor> doctors = SystemManager.getDoctors();
            ArrayList<Doctor> searchResults = new ArrayList<>();

            switch (searchOption) {
                case 1:
                    System.out.println("Enter specialization: ");
                    String specialization = scanner.nextLine().toLowerCase();

                    for (Doctor doctor : doctors) {
                        if (doctor.getApproved() && doctor.getSpecialisation().toLowerCase().contains(specialization)) {
                            searchResults.add(doctor);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter doctor name: ");
                    String name = scanner.nextLine().toLowerCase();

                    for (Doctor doctor : doctors) {
                        if (doctor.getApproved() &&
                                (doctor.getFirstName().toLowerCase().contains(name) ||
                                        doctor.getLastName().toLowerCase().contains(name))) {
                            searchResults.add(doctor);
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid option.");
                    return;
            }

            if (searchResults.isEmpty()) {
                System.out.println("No doctors found matching your search criteria.");
                return;
            }

            System.out.println("\nSearch Results:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Doctor Name | Specialization | Rating");
            System.out.println("----------------------------------------");

            for (Doctor doctor : searchResults) {
                double avgRating = SystemManager.calculateDoctorAvgRating(doctor.getDoctorID());
                System.out.printf("%d | Dr. %s %s | %s | %.1f/5.0\n",
                        doctor.getDoctorID(),
                        doctor.getFirstName(),
                        doctor.getLastName(),
                        doctor.getSpecialisation(),
                        avgRating);
            }
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error searching for doctors: " + e.getMessage(), "Search Error");
        }
    }

    public static void adminChoices() {
        try {
            Scanner scan = new Scanner(System.in);
            User currentUser = SystemManager.getSession().getFirst();

            System.out.println("Welcome Admin " + currentUser.getFirstName() + "!");
            System.out.println("What would you like to do?");

            System.out.println("1. Approve doctors");
            System.out.println("2. View pending appointments");
            System.out.println("3. View all appointments");
            System.out.println("4. View all patients");
            System.out.println("5. View all doctors");
            System.out.println("6. Edit your profile");
            System.out.println("7. Return to main menu");
            System.out.println("8. Logout");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    approveDoctors(scan);
                    break;
                case 2:
                    viewPendingAppointments();
                    break;
                case 3:
                    viewAllAppointments();
                    break;
                case 4:
                    viewAllPatients();
                    break;
                case 5:
                    viewAllDoctors();
                    break;
                case 6:
                    editAdminProfile();
                    break;
                case 7:
                    break;
                case 8:
                    AuthFunctions.logout();

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } catch (Exception e) {
            displayError("Error in adminChoices(): " + e.getMessage(), "Admin Error");
        }
    }

    public static void approveDoctors(Scanner scanner) {
        try {
            ArrayList<Doctor> doctors = SystemManager.getDoctors();
            ArrayList<Doctor> pendingDoctors = new ArrayList<>();

            for (Doctor doctor : doctors) {
                if (!doctor.getApproved()) {
                    pendingDoctors.add(doctor);
                }
            }

            if (pendingDoctors.isEmpty()) {
                System.out.println("No pending doctor applications to approve.");
                return;
            }

            System.out.println("\nPending Doctor Applications:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Name | Specialization | Years of Experience");
            System.out.println("----------------------------------------");

            for (Doctor doctor : pendingDoctors) {
                System.out.printf("%d | Dr. %s %s | %s | %d years\n",
                        doctor.getDoctorID(),
                        doctor.getFirstName(),
                        doctor.getLastName(),
                        doctor.getSpecialisation(),
                        doctor.getYearsOfXP());
            }
            System.out.println("----------------------------------------");

            System.out.println("Enter Doctor ID to approve (0 to cancel): ");
            int doctorID = scanner.nextInt();

            if (doctorID == 0) {
                return;
            }

            for (Doctor doctor : pendingDoctors) {
                if (doctor.getDoctorID() == doctorID) {
                    doctor.setApproved(true);
                    DataBaseManager.approveDoctorById(doctorID);
                    System.out.println("Dr. " + doctor.getFirstName() + " " + doctor.getLastName() + " has been approved.");
                    return;
                }
            }

            System.out.println("Doctor with ID " + doctorID + " not found in pending applications.");

        } catch (Exception e) {
            displayError("Error approving doctors: " + e.getMessage(), "Approval Error");
        }
    }

    public static void viewPendingAppointments() {
        try {
            ArrayList<Appointment> allAppointments = SystemManager.getAppointments();
            ArrayList<Appointment> pendingAppointments = new ArrayList<>();

            for (Appointment appt : allAppointments) {
                if (appt.getStatus().equals("PENDING")) {
                    pendingAppointments.add(appt);
                }
            }

            if (pendingAppointments.isEmpty()) {
                System.out.println("No pending appointments.");
                return;
            }

            System.out.println("\nPending Appointments:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Date | Time | Patient | Doctor");
            System.out.println("----------------------------------------");

            for (Appointment appt : pendingAppointments) {
                Patient patient = SystemManager.findPatient(appt.getPatientID());
                Doctor doctor = SystemManager.findDoctor(appt.getDoctorID());

                String patientName = (patient != null) ? patient.getFirstName() + " " + patient.getLastName() : "Unknown";
                String doctorName = (doctor != null) ? "Dr. " + doctor.getFirstName() + " " + doctor.getLastName() : "Unknown";

                System.out.printf("%d | %s | %s | %s | %s\n",
                        appt.getAppointmentID(),
                        appt.getAppointmentDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                        appt.getAppointmentTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                        patientName,
                        doctorName);
            }
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error viewing pending appointments: " + e.getMessage(), "View Error");
        }
    }

    public static void viewAllAppointments() {
        try {
            ArrayList<Appointment> allAppointments = SystemManager.getAppointments();

            if (allAppointments.isEmpty()) {
                System.out.println("No appointments found.");
                return;
            }

            System.out.println("\nAll Appointments:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Date | Time | Patient | Doctor | Status");
            System.out.println("----------------------------------------");

            for (Appointment appt : allAppointments) {
                Patient patient = SystemManager.findPatient(appt.getPatientID());
                Doctor doctor = SystemManager.findDoctor(appt.getDoctorID());

                String patientName = (patient != null) ? patient.getFirstName() + " " + patient.getLastName() : "Unknown";
                String doctorName = (doctor != null) ? "Dr. " + doctor.getFirstName() + " " + doctor.getLastName() : "Unknown";

                System.out.printf("%d | %s | %s | %s | %s | %s\n",
                        appt.getAppointmentID(),
                        appt.getAppointmentDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                        appt.getAppointmentTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                        patientName,
                        doctorName,
                        appt.getStatus());
            }
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error viewing all appointments: " + e.getMessage(), "View Error");
        }
    }

    public static void viewAllPatients() {
        try {
            ArrayList<Patient> patients = SystemManager.getPatients();

            if (patients.isEmpty()) {
                System.out.println("No patients found.");
                return;
            }

            System.out.println("\nAll Patients:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Name | Email | Medical Aid");
            System.out.println("----------------------------------------");

            for (Patient patient : patients) {
                System.out.printf("%d | %s %s | %s | %d\n",
                        patient.getPatientID(),
                        patient.getFirstName(),
                        patient.getLastName(),
                        patient.getEmail(),
                        patient.getMedicalAidNumber());
            }
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error viewing all patients: " + e.getMessage(), "View Error");
        }
    }

    public static void viewAllDoctors() {
        try {
            ArrayList<Doctor> doctors = SystemManager.getDoctors();

            if (doctors.isEmpty()) {
                System.out.println("No doctors found.");
                return;
            }

            System.out.println("\nAll Doctors:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Name | Specialization | Approved | Rating");
            System.out.println("----------------------------------------");

            for (Doctor doctor : doctors) {
                double avgRating = SystemManager.calculateDoctorAvgRating(doctor.getDoctorID());

                System.out.printf("%d | Dr. %s %s | %s | %s | %.1f/5.0\n",
                        doctor.getDoctorID(),
                        doctor.getFirstName(),
                        doctor.getLastName(),
                        doctor.getSpecialisation(),
                        doctor.getApproved() ? "Yes" : "No",
                        avgRating);
            }
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error viewing all doctors: " + e.getMessage(), "View Error");
        }
    }
    public static void editDoctorProfile() {
        User currentUser = SystemManager.getSession().getFirst();
        Doctor doctor = null;

        for (Doctor d : SystemManager.getDoctors()) {
            if (d.getUserID() == currentUser.getUserID()) {
                doctor = d;
                break;
            }
        }

        if (doctor == null) {
            System.out.println("Error: Doctor profile not found!");
            return;
        }

        Scanner scan = new Scanner(System.in);

        System.out.println("\n--- Edit Your Profile ---");
        System.out.println("1. Edit Personal Information");
        System.out.println("2. Edit Office Information");
        System.out.println("3. Edit Specialization");
        System.out.println("4. Edit Years of Experience");
        System.out.println("5. Return to main menu");

        int choice = scan.nextInt();
        scan.nextLine(); // since getting value after a nextInt causes errors, just insert a new line
        // the value leaves \n and itll cause trouble, so just insert a newline, let it consume a newline, so its blank

        switch (choice) {
            case 1:
                editUserPersonalInfo(currentUser);
                break;
            case 2:
                editOfficeInfo(doctor);
                break;
            case 3:
                System.out.print("Enter new specialization: ");
                String specialization = scan.nextLine();
                DataBaseManager.updateDoctorSpecialization(doctor.getDoctorID(), specialization);
                break;
            case 4:
                System.out.print("Enter new years of experience: ");
                int years = scan.nextInt();
                DataBaseManager.updateDoctorExperience(doctor.getDoctorID(), years);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option. Going back.");
        }
    }

    public static void editPatientProfile() {
        User currentUser = SystemManager.getSession().getFirst();
        Patient patient = null;

        //use the logged in user (session variable)
        for (Patient p : SystemManager.getPatients()) {
            if (p.getUserID() == currentUser.getUserID()) {
                patient = p;
                break;
            }
        }

        if (patient == null) {
            System.out.println("Error: Patient profile not found!");
            return;
        }

        Scanner scan = new Scanner(System.in);

        System.out.println("\n--- Edit Your Profile ---");
        System.out.println("1. Edit Personal Information");
        System.out.println("2. Edit Medical Aid Number");
        System.out.println("3. Return to main menu");

        int choice = scan.nextInt();
        scan.nextLine(); // consume newline

        switch (choice) {
            case 1:
                editUserPersonalInfo(currentUser);
                break;
            case 2:
                System.out.print("Enter new Medical Aid Number: ");
                int medicalAidNumber = scan.nextInt();
                DataBaseManager.updatePatientMedicalAid(patient.getPatientID(), medicalAidNumber);
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Going back.");
        }
    }

    public static void editAdminProfile() {
        User currentUser = SystemManager.getSession().getFirst();

        Scanner scan = new Scanner(System.in);

        System.out.println("\n--- Edit Your Profile ---");
        System.out.println("1. Edit Personal Information");
        System.out.println("2. Return to main menu");

        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice) {
            case 1:
                editUserPersonalInfo(currentUser);
                break;
            case 2:
                return;
            default:
                System.out.println("Invalid option. Going back.");
        }
    }

    private static void editUserPersonalInfo(User user) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n--- Edit Personal Information ---");
        System.out.println("Current Information:");
        System.out.println("1. First Name: " + user.getFirstName());
        System.out.println("2. Last Name: " + user.getLastName());
        System.out.println("3. Phone Number: " + user.getPhoneNumber());
        System.out.println("4. Telephone: " + user.getTelephone());
        System.out.println("5. Email: " + user.getEmail());
        System.out.println("6. Change Password");
        System.out.println("7. Go back");

        System.out.print("\nSelect field to edit (1-7): ");
        int choice = scan.nextInt();
        scan.nextLine(); // consume newline

        String newValue = "";
        String field = "";

        switch (choice) {
            case 1:
                System.out.print("Enter new first name: ");
                newValue = scan.nextLine();
                field = "firstName";
                user.setFirstName(newValue);
                break;
            case 2:
                System.out.print("Enter new last name: ");
                newValue = scan.nextLine();
                field = "lastName";
                user.setLastName(newValue);
                break;
            case 3:
                System.out.print("Enter new phone number: ");
                newValue = scan.nextLine();
                field = "phoneNumber";
                user.setPhoneNumber(newValue);
                break;
            case 4:
                System.out.print("Enter new telephone: ");
                newValue = scan.nextLine();
                field = "telephone";
                user.setTelephone(newValue);
                break;
            case 5:
                System.out.print("Enter new email: ");
                newValue = scan.nextLine();
                field = "email";
                user.setEmail(newValue);
                break;
            case 6:
                System.out.print("Enter new password: ");
                newValue = scan.nextLine();
                field = "password";
                user.setPassword(newValue);
                break;
            case 7:
                return;
            default:
                System.out.println("Invalid option. Going back.");
                return;
        }

        DataBaseManager.updateUserField(user.getUserID(), field, newValue);
        System.out.println("Information updated successfully!");
    }

    private static void editOfficeInfo(Doctor doctor) {
        Scanner scan = new Scanner(System.in);
        Office office = null;

        // look for the office for this doctor
        for (Office o : SystemManager.getOffices()) {
            if (o.getDoctorID() == doctor.getDoctorID()) {
                office = o;
                break;
            }
        }

        if (office == null) {
            System.out.println("Error: Office not found!");
            return;
        }

        System.out.println("\n--- Edit Office Information ---");
        System.out.println("Current Information:");
        System.out.println("1. Office Name: " + office.getOfficeName());
        System.out.println("2. Location: " + office.getOfficeLocation());
        System.out.println("3. Opening Hours: " + office.getOpeningHours());
        System.out.println("4. Closing Hours: " + office.getClosingHours());
        System.out.println("5. Go back");

        System.out.print("\nSelect field to edit (1-5): ");
        int choice = scan.nextInt();
        scan.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new office name: ");
                String name = scan.nextLine();
                DataBaseManager.updateOfficeField(office.getOfficeID(), "name", name);
                office.setOfficeName(name);
                break;
            case 2:
                System.out.print("Enter new location: ");
                String location = scan.nextLine();
                DataBaseManager.updateOfficeField(office.getOfficeID(), "location", location);
                office.setOfficeLocation(location);
                break;
            case 3:
                System.out.print("Enter new opening hours (HH:MM): ");
                String openTime = scan.nextLine();
                try {
                    LocalTime openingTime = LocalTime.parse(openTime, DateTimeFormatter.ofPattern("HH:mm"));
                    DataBaseManager.updateOfficeTime(office.getOfficeID(), "openingHours", openingTime);
                    office.setOpeningHours(openingTime);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format. Please use HH:MM.");
                }
                break;
            case 4:
                System.out.print("Enter new closing hours (HH:MM): ");
                String closeTime = scan.nextLine();
                try {
                    LocalTime closingTime = LocalTime.parse(closeTime, DateTimeFormatter.ofPattern("HH:mm"));
                    DataBaseManager.updateOfficeTime(office.getOfficeID(), "closingHours", closingTime);
                    office.setClosingHours(closingTime);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format. Please use HH:MM.");
                }
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option. Going back.");
        }

        System.out.println("Office information updated successfully!");
    }

    public static void viewDoctorAppointments(int doctorID) {
        try {
            ArrayList<Appointment> allAppointments = SystemManager.getAppointments();
            ArrayList<Appointment> doctorAppointments = new ArrayList<>();

            for (Appointment appt : allAppointments) {
                if (appt.getDoctorID() == doctorID) {
                    doctorAppointments.add(appt);
                }
            }

            if (doctorAppointments.isEmpty()) {
                System.out.println("You have no pending appointments.");
                return;
            }

            System.out.println("\nYour Pending Appointments:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Date | Time | Patient | Reason");
            System.out.println("----------------------------------------");

            for (Appointment appt : doctorAppointments) {
                Patient patient = SystemManager.findPatient(appt.getPatientID());
                String patientName = (patient != null) ? patient.getFirstName() + " " + patient.getLastName() : "Unknown";

                System.out.printf("%d | %s | %s | %s | %s\n",
                        appt.getAppointmentID(),
                        appt.getAppointmentDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                        appt.getAppointmentTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                        patientName,
                        appt.getReasonForVisit());
            }
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error viewing your appointments: " + e.getMessage(), "View Error");
        }
    }

    public static void viewDoctorPendingAppointments(int doctorID) {
        try {
            ArrayList<Appointment> allAppointments = SystemManager.getAppointments();
            ArrayList<Appointment> doctorPendingAppointments = new ArrayList<>();

            for (Appointment appt : allAppointments) {
                if (appt.getDoctorID() == doctorID && appt.getStatus().equals("PENDING")) {
                    doctorPendingAppointments.add(appt);
                }
            }

            if (doctorPendingAppointments.isEmpty()) {
                System.out.println("You have no pending appointments.");
                return;
            }

            System.out.println("\nYour Pending Appointments:");
            System.out.println("----------------------------------------");
            System.out.println("ID | Date | Time | Patient | Reason");
            System.out.println("----------------------------------------");

            for (Appointment appt : doctorPendingAppointments) {
                Patient patient = SystemManager.findPatient(appt.getPatientID());
                String patientName = (patient != null) ? patient.getFirstName() + " " + patient.getLastName() : "Unknown";

                System.out.printf("%d | %s | %s | %s | %s\n",
                        appt.getAppointmentID(),
                        appt.getAppointmentDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")),
                        appt.getAppointmentTime().format(DateTimeFormatter.ofPattern("h:mm a")),
                        patientName,
                        appt.getReasonForVisit());
            }
            System.out.println("----------------------------------------");

        } catch (Exception e) {
            displayError("Error viewing pending appointments: " + e.getMessage(), "View Error");
        }
    }


    public static void doctorChoices() {
        try {
            Scanner scan = new Scanner(System.in);
            User currentUser = SystemManager.getSession().getFirst();
            int userID = currentUser.getUserID();
            int doctorID = -1;

            // Find the doctor ID from user ID
            for (Doctor d : SystemManager.getDoctors()) {
                if (d.getUserID() == userID) {
                    doctorID = d.getDoctorID();
                    break;
                }
            }

            if (doctorID == -1) {
                System.out.println("Error: Could not find doctor record for the current user.");
                return;
            }

            System.out.println("\n--- Doctor Dashboard ---");
            System.out.println("Welcome Dr. " + currentUser.getLastName() + "!");
            System.out.println("What would you like to do?");

            System.out.println("1. View pending appointments");
            System.out.println("2. View all appointments");
            System.out.println("3. View office details");
            System.out.println("4. Complete appointment");
            System.out.println("5. Create prescription");
            System.out.println("6. Edit profile");
            System.out.println("7. Edit office information");
            System.out.println("8. Logout");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    viewDoctorPendingAppointments(doctorID);
                    break;
                case 2:
                    viewDoctorAppointments(doctorID);
                    break;
                case 3:
                    viewOfficeDetails();
                    break;
                case 4:
                    completeAppointment(doctorID);
                    break;
                case 5:
                    createPrescription(doctorID);
                    break;
                case 6:
                    editDoctorProfile();
                    break;
                case 7:
                    editOfficeInfo(doctorID);
                    break;
                case 8:
                    AuthFunctions.logout();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } catch (Exception e) {
            displayError("Error in doctorChoices(): " + e.getMessage(), "Doctor Error");
            e.printStackTrace();
        }
    }

    private static void viewOfficeDetails() {
        Office office = null;


        int loggedInDoctorID = SystemManager.getSession().getFirst().getUserID();
        for (Office o : SystemManager.getOffices()) {

            System.out.println("Logged-in doctor ID: " + loggedInDoctorID);
            System.out.println("Checking office with doctor ID: " + o.getDoctorID());

            if (o.getDoctorID() == SystemManager.getSession().getFirst().getUserID()) {
                office = o;
                break;
            }
        }

        if (office == null) {
            System.out.println("Error: Office not found!");
            return;
        }

        System.out.println("\n--- Office Details ---");
        System.out.println("Office Name: " + office.getOfficeName());
        System.out.println("Location: " + office.getOfficeLocation());
        System.out.println("Opening Hours: " + office.getOpeningHours());
        System.out.println("Closing Hours: " + office.getClosingHours());
        System.out.println("Account Balance: $" + office.getAccountBalance());

        System.out.println("\nPress Enter to continue...");
        new Scanner(System.in).nextLine();
    }

    private static void editOfficeInfo(int doctorID) {
        Scanner scan = new Scanner(System.in);
        Office office = null;

        // get offce for this doctor
        for (Office o : SystemManager.getOffices()) {
            if (o.getDoctorID() == doctorID) {
                office = o;
                break;
            }
        }

        if (office == null) {
            System.out.println("Error: Office not found!");
            return;
        }

        System.out.println("\n--- Edit Office Information ---");
        System.out.println("Current Information:");
        System.out.println("1. Office Name: " + office.getOfficeName());
        System.out.println("2. Location: " + office.getOfficeLocation());
        System.out.println("3. Opening Hours: " + office.getOpeningHours());
        System.out.println("4. Closing Hours: " + office.getClosingHours());
        System.out.println("5. Go back");

        System.out.print("\nSelect one to edit (1-5): ");
        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new office name: ");
                String name = scan.nextLine();
                DataBaseManager.updateOfficeField(office.getOfficeID(), "name", name);
                office.setOfficeName(name);
                break;
            case 2:
                System.out.print("Enter new location: ");
                String location = scan.nextLine();
                DataBaseManager.updateOfficeField(office.getOfficeID(), "location", location);
                office.setOfficeLocation(location);
                break;
            case 3:
                System.out.print("Enter new opening hours (HH:MM): ");
                String openTime = scan.nextLine();
                try {
                    LocalTime openingTime = LocalTime.parse(openTime, DateTimeFormatter.ofPattern("HH:mm"));
                    DataBaseManager.updateOfficeTime(office.getOfficeID(), "openingHours", openingTime);
                    office.setOpeningHours(openingTime);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format. Please use HH:MM.");
                }
                break;
            case 4:
                System.out.print("Enter new closing hours (HH:MM): ");
                String closeTime = scan.nextLine();
                try {
                    LocalTime closingTime = LocalTime.parse(closeTime, DateTimeFormatter.ofPattern("HH:mm"));
                    DataBaseManager.updateOfficeTime(office.getOfficeID(), "openingHours", closingTime);
                    office.setOpeningHours(closingTime);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format. Please use HH:MM.");
                }
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid option. Going back.");
                return;

        }
    }
    private static void completeAppointment(int doctorID) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Appointment> appointments = SystemManager.getAppointments();
        boolean hasAccepted = false;

        System.out.println("\n--- Complete Appointment ---");

        for (Appointment appt : appointments) {
            if (appt.getDoctorID() == doctorID && appt.getStatus().equals("ACCEPTED")) {
                hasAccepted = true;
                displayAppointmentDetails(appt);
            }
        }

        if (!hasAccepted) {
            System.out.println("No accepted appointments available to complete.");
            return;
        }

        System.out.print("\nEnter the Appointment ID to mark as completed or 0 to go back: ");
        int id = scan.nextInt();
        scan.nextLine();

        if (id == 0) return;

        Appointment apptToComplete = SystemManager.findAppointment(id);

        if (apptToComplete != null && apptToComplete.getDoctorID() == doctorID) {
            updateAppointmentStatus(id, "COMPLETED");
            System.out.println("Appointment marked as COMPLETED.");
        } else {
            System.out.println("Invalid appointment ID or not assigned to you.");
        }
    }

    private static void createPrescription(int doctorID) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Appointment> appointments = SystemManager.getAppointments();
        boolean hasCompleted = false;

        System.out.println("\n--- Create Prescription ---");

        for (Appointment appt : appointments) {
            if (appt.getDoctorID() == doctorID && appt.getStatus().equals("COMPLETED")) {
                hasCompleted = true;
                displayAppointmentDetails(appt);
            }
        }

        if (!hasCompleted) {
            System.out.println("No completed appointments found.");
            return;
        }

        System.out.print("\nEnter the Appointment ID to write a prescription for or 0 to go back: ");
        int apptID = scan.nextInt();
        scan.nextLine();

        if (apptID == 0) return;

        Appointment selectedAppt = SystemManager.findAppointment(apptID);
        if (selectedAppt == null || selectedAppt.getDoctorID() != doctorID || !selectedAppt.getStatus().equals("COMPLETED")) {
            System.out.println("Invalid appointment selection.");
            return;
        }

        System.out.print("Enter prescription instructions: ");
        String instructions = scan.nextLine();

        int patientID = selectedAppt.getPatientID();
        Date issueDate = new Date();

        Prescription prescription = new Prescription(
                instructions,
                doctorID,
                patientID,
                apptID,
                issueDate
        );

        SystemManager.addPrescription(prescription);
        System.out.println("Prescription created successfully!");
    }

    private static void manageAppointment(int appointmentID, int doctorID) {
        Scanner scan = new Scanner(System.in);
        Appointment appt = SystemManager.findAppointment(appointmentID);

        if (appt == null) {
            System.out.println("Appointment not found.");
            return;
        }

        if (appt.getDoctorID() != doctorID) {
            System.out.println("This appointment is not assigned to you.");
            return;
        }

        System.out.println("\n--- Appointment Details ---");
        Patient patient = SystemManager.findPatient(appt.getPatientID());
        String patientName = (patient != null) ?
                patient.getFirstName() + " " + patient.getLastName() : "Unknown Patient";

        System.out.println("Patient: " + patientName);
        System.out.println("Date: " + appt.getAppointmentDate());
        System.out.println("Time: " + appt.getAppointmentTime());
        System.out.println("Reason: " + appt.getReasonForVisit());
        System.out.println("Status: " + appt.getStatus());

        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Accept appointment");
        System.out.println("2. Decline appointment");
        System.out.println("3. Go back");

        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                updateAppointmentStatus(appointmentID, "ACCEPTED");
                System.out.println("Appointment accepted successfully!");
                break;
            case 2:
                updateAppointmentStatus(appointmentID, "REJECTED");
                System.out.println("Appointment declined.");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Going back.");
        }
    }

    private static void displayAppointmentDetails(Appointment appt) {
        Patient patient = SystemManager.findPatient(appt.getPatientID());
        String patientName = (patient != null) ?
                patient.getFirstName() + " " + patient.getLastName() : "Unknown Patient";

        System.out.println("ID: " + appt.getAppointmentID() +
                " | Patient: " + patientName +
                " | Date: " + appt.getAppointmentDate() +
                " | Time: " + appt.getAppointmentTime() +
                " | Reason: " + appt.getReasonForVisit() +
                " | Status: " + appt.getStatus());
    }

}


