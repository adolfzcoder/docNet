package Models;

import auth.AuthFunctions;

import java.util.ArrayList;

public class Patient extends User {
    private int medicalAidNumber;
    private int patientID;
    private double balance =0.0;


    public Patient(int userID, int patientID, int medicalAidNumber, String firstName, String lastName, String phoneNumber, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender, double balance){
        super(userID,firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender);

        this.medicalAidNumber = medicalAidNumber;
        this.patientID = patientID;
        this.balance = balance;

        AuthFunctions.signUp(this);

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void addAppointment(Doctor doctor, String beginTime, String endTime, String date, String dayOfWeek){

    }
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getMedicalAidNumber(){
        return this.medicalAidNumber;
    }

    public void rateDoctor(Doctor doc, int rating){
        doc.addRating(rating);
        System.out.println("Doctor rated successfully");
    }

    public ArrayList<Doctor> getApprovedDoctors(){
        return SystemManager.getApprovedDoctors();
    }

    public Doctor findDoctor(String email){
        for(Doctor doc: SystemManager.getApprovedDoctors()){
            if ((doc.getEmail()).equalsIgnoreCase((email))){
                return doc;
            }

        }
        return null;

    }

    public void displayApprovedDoctors(){
        for(Doctor doc: SystemManager.getApprovedDoctors()){
            System.out.println(doc.toString());
        }
    }

    public void bookAppointment(String doctorsEmail, String startTime, String endTime){

    }

    @Override
    public int getUserTypeID(){
        return patientID;
    }
}
