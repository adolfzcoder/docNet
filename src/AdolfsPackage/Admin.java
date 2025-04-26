package AdolfsPackage;// create admin, inherit from user, but set the user type to admin, allow them to approve
// and not approve users

// lets assume just one admin

import java.util.ArrayList;

public class Admin extends User {

    private ArrayList<Doctor> approvedDoctors = new ArrayList<>();
    private ArrayList<Doctor> notApprovedDoctors = new ArrayList<>();



    public Admin(int userID, String firstName, String lastName, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender){
        super(userID, firstName, lastName, telephone, dob, isApproved, userType, email, password, gender);

    }

/*
    we need an actual algorithm or way to verify the doctor, right now it jsut checks if the
    submission is not empty or not submitted, possible ones could be
    check the submitted documents, so we handle file uploads, admin can then check the file
    to see if its legit
 */
    public void addToApprovedList(Doctor dr){
        approvedDoctors.add(dr);
    }

    public void addToNotApprovedList(Doctor dr){
        notApprovedDoctors.add(dr);
    }
    public void verifyDoctor(Doctor doctor){
        if(doctor.getMedicalCertificate() != null && ! doctor.getMedicalCertificate().isEmpty()){
            doctor.setApproved(true);
            addToApprovedList(doctor); // add this approved doctor to list of approved doctors

            System.out.println("Doctor has been approved, added to approved list");
        }else {
            notApprovedDoctors.add(doctor); // add this rejected doctor to list of rejected doctors

            System.out.println("Doctor "+ doctor.getFirstName() + " "+ doctor.getLastName()+" has invalid certificate");

        }

    }

    public ArrayList<Doctor> approvedDoctors(){
        return approvedDoctors;
    }
    public ArrayList<Doctor> notApprovedDoctors(){
        return notApprovedDoctors;

    }

    public void displayApprovedDoctors(){
        for(Doctor dr: approvedDoctors){
            System.out.println(dr);
        }
    }

    public void displayNotApprovedDoctors(){
        for(Doctor dr: notApprovedDoctors){
            System.out.println(dr.getFirstName());

        }
    }
}
