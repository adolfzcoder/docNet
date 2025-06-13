package models;

import storage.SystemManager;

import java.util.ArrayList;

public class Patient extends User implements UserInterface{
    private int medicalAidNumber;
    private int patientID;


    public Patient(int userID, int patientID, int medicalAidNumber, String firstName, String lastName, String phoneNumber, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender){
        super(userID,firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender);

        this.medicalAidNumber = medicalAidNumber;
        this.patientID = patientID;

        // AuthFunctions.signUp(this);

    }

    public Patient(int medicalAidNumber, String firstName, String lastName, String phoneNumber, String telephone, String dob, String email, String password, String gender){
        super(firstName, lastName, phoneNumber, telephone, dob, "PATIENT", email, password, gender);
        this.medicalAidNumber = medicalAidNumber;
        this.patientID = 0;

    }

    public Patient(int userID, int patientID, int medAidNumber, String firstName, String lastName, String phoneNumber, String telephone, String dob, boolean b, String patient, String email, String password, String gender, double balance) {
        super(userID,firstName, lastName, phoneNumber, telephone, dob, b, patient, email, password, gender);
        this.medicalAidNumber = medAidNumber;
        this.patientID = patientID;

    }

    @Override
    public void setUserTypeID(int userTypeID){
        this.patientID = userTypeID;
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
