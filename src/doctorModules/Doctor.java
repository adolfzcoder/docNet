package doctorModules;

import auth.AuthFunctions;
import models.User;
import validations.DoctorValidate;

import java.util.ArrayList;

public class Doctor extends User {
    private int doctorID;
    private String medicalCertificate;
    private int yearsOfXP =0;
    private String specialisation;
    private double totalRating;
    private int numberOfRatings;
    private boolean isBooked=false;

    public static ArrayList<Doctor> allDoctors = new ArrayList<>();

    // AdolfsPackage.User user = new AdolfsPackage.User();

    public DoctorValidate validate = new DoctorValidate();

    public Doctor(int userID, int doctorID, String medicalCertificate, int yearsOfXP, String specialisation, String firstName, String lastName, String phoneNumber, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender) {



        super(userID,firstName, lastName, phoneNumber, telephone, dob, isApproved, userType, email, password, gender);
        setMedicalCertificate(medicalCertificate);
        setYearsOfXP(yearsOfXP);
        this.specialisation = specialisation;

        AuthFunctions.signUp(this);




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
