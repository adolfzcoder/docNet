package AdolfsPackage;

import SystemManager.SystemManager;

import java.util.ArrayList;

public class Patient {
    private String medicalAidNumber;


    public Patient(){

        SystemManager.addPatient(this);
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
}
