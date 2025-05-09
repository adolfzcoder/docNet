package models;

import storage.SystemManager;
import validations.DoctorValidate;

public class Doctor extends User {
    private int doctorID;
    private String medicalCertificate;
    private int yearsOfXP =0;
    private String specialisation;
    private double totalRating;
    private int numberOfRatings;
    private boolean isBooked=false;
    private String officeName;

    private int officeID;




    public DoctorValidate validate = new DoctorValidate();

    public Doctor(int userID, int doctorID, String medicalCertificate, int yearsOfXP, String specialisation, String firstName, String lastName, String phoneNumber, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender, boolean isBooked) {



        super(userID,firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender);
        setMedicalCertificate(medicalCertificate);
        setYearsOfXP(yearsOfXP);
        this.doctorID = doctorID;
        this.specialisation = specialisation;
        this.isBooked = isBooked;

        // AuthFunctions.signUp(this);




    }

    public Doctor(String medicalCertificate, int yearsOfXP, String specialisation, String firstName, String lastName, String phoneNumber, String telephone, String dob, String email, String password, String gender){
        super(firstName, lastName, phoneNumber, telephone, dob, "DOCTOR", email, password, gender);
        setMedicalCertificate(medicalCertificate);
        setYearsOfXP(yearsOfXP);
        this.doctorID = 0;
        this.specialisation = specialisation;
        this.isBooked = false;
        this.officeID = -1;
    }
    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Name='"+getFirstName()+" "+ getLastName()+ '\'' +
        "doctorID='" + doctorID + '\'' +
                "medicalCertificate='" + medicalCertificate + '\'' +
                ", yearsOfXP=" + yearsOfXP +
                ", specialisation='" + specialisation + '\'' +
                ", validate=" + validate +
                '}';
    }
    // @Override
    void displayRole() {
        System.out.println("Signed up as DOCTOR");
    }


    public String getMedicalCertificate() {
        return this.medicalCertificate;
    }

    public void setMedicalCertificate(String medicalCertificate) {
        this.medicalCertificate = medicalCertificate;
    }

    public int getOfficeID() {
        return officeID;
    }

    public void setOfficeID(int officeID) {
        this.officeID = officeID;
    }

    @Override
    public int getUserTypeID(){
        return doctorID;
    }
    public void addRating(int rating){
        totalRating+=rating;
        numberOfRatings ++;
    }

    public double getAverageRate(){
        if(numberOfRatings == 0){
            return 0.0;
        }
        return totalRating/numberOfRatings;
    }


    public int getYearsOfXP() {
        return yearsOfXP;
    }

    public void setYearsOfXP(int yearsOfXP) {
        if (validate.validateYearsOfXP(yearsOfXP)){
            this.yearsOfXP = yearsOfXP;
        }
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }


}
