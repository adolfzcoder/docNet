package patientModules;

import auth.AuthFunctions;
import doctorModules.Doctor;
import models.SystemManager;
import models.User;

import java.util.ArrayList;

public class Patient extends User {
    private String medicalAidNumber;
    private int patientID;


    public Patient(int userID, int patientID, String medicalAidNumber, String firstName, String lastName, String phoneNumber, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender){
        super(userID,firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender);

        this.medicalAidNumber = medicalAidNumber;
        this.patientID = patientID;

        AuthFunctions.signUp(this);

    }

    public static void addAppointment(Doctor doctor, String beginTime, String endTime, String date, String dayOfWeek){

    }
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getMedicalAidNumber(){
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
