package models;

import storage.SystemManager;

public class Admin extends User implements UserInterface{
    private int adminID;
    // SystemManager sys = new SystemManager();


    public Admin(int userID, int adminID, String firstName, String lastName, String phoneNumber, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender) {
        super(userID, firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender);
        this.adminID = adminID;

        // AuthFunctions.signUp(this);


    }

    public Admin(int adminID, int userID) {
    }

    /*
        we need an actual algorithm or way to verify the doctor, right now it jsut checks if the
        submission is not empty or not submitted, possible ones could be
        check the submitted documents, so we handle file uploads, admin can then check the file
        to see if its legit
     */

    public void addToApprovedList(Doctor dr) {
        SystemManager.addApprovedDoctor(dr);
        // alert user of approval status
        dr.notify();
    }

    public static void addToAllDoctors(Doctor dr) {
        SystemManager.addDoctor(dr);
    }

    //    public void addToNotApprovedList(Doctor dr){
//        notApprovedDoctors.add(dr);
//    }
    public void verifyDoctor(Doctor doctor) {
        doctor.setApproved(true);
        addToApprovedList(doctor); // add this approved doctor to list of approved doctors

        System.out.println("Doctor has been approved, added to approved list");


    }

}
