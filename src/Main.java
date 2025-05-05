import doctorModules.Prescription;
import models.SystemManager;
import adminModules.Admin;
import auth.AuthFunctions;
import doctorModules.Doctor;
import models.User;
import patientModules.Appointment;
import patientModules.Patient;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
//     public User sessionUser = SystemManager.getSession().getFirst();
//
//     public String loggedInUserType = sessionUser.getUserType();

     // Main main = new Main();

    public static void main(String[] args){
        System.out.println("TESTS");
        // creating a new doctor user {3 user types, admin, patient, doctor}
        // for admins, keep a default admin, which means they will always be approved
        Scanner scan = new Scanner(System.in);
        System.out.println("WELCOME TO Doc NET, PRESS anything to continue, press q to exit");

        String quit = "";

        while (!quit.equalsIgnoreCase("q")) {
            // if user isnt logged in, then ask whether they want to login or reg
            if( SystemManager.getSession().isEmpty() ){
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
                                System.out.println("Invalid input. Try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number or 'q' to quit.");
                    }
            }

            }
            else{


                switch(SystemManager.getSession().getFirst().getUserType()){
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

    public static void patientChoices(){

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome Patient!");
        System.out.println("What would you like to do?");

        System.out.println("1 Make appointment. ");
        System.out.println("2 View all appointments. ");
        System.out.println("3 View Prescriptions. ");
        System.out.println("4 View Prescription (Enter a prescription ID). ");
        int choice = scan.nextInt();

        switch(choice){
            case 1:
                makeAppointment(scan);
                break;
            case 2:
                viewAppointments();
                break;
            case 3:
                viewPrescription(scan);
                break;
            case 4:
                break;
            default:
                System.out.println("invalid choice, please try again. ");

        }

    }

    public static void viewPrescription(Scanner scan){
        ArrayList<Prescription> prescriptions = SystemManager.getPrescriptions();
        System.out.println("Enter a Prescription ID");

        int presctiptionID = scan.nextInt();



        for(Prescription prescription: prescriptions){
            if(prescription.getPrescriptionID() == presctiptionID){
                System.out.println("presction found");
                System.out.println(prescription);
            }
            else{
                System.out.println("Prescrition id not found");
            }
        }
    }
    public static void viewAppointments() {
            ArrayList<Appointment> appointmentList = SystemManager.getAppointments();

            for(Appointment appointment: appointmentList){
                System.out.println(appointment);
            }
    }



    public static void makeAppointment(Scanner scanner){
            int patientID =  SystemManager.getSession().getFirst().getUserTypeID();

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

    }

    public static void adminChoices(){
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

        switch (choice){
            case 1:
                ArrayList<Appointment> pendingAppts = SystemManager.getPendingAppointments(SystemManager.getSession().getFirst().getUserTypeID());
                for(Appointment appt: pendingAppts ){
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
                System.out.println("All appointmenets displayed");
                break;
            default:
                System.out.println("invalid entry try again");


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

    }
}


