package AdolfsPackage;

import validations.DoctorValidate;

public class Doctor extends User {
    private int doctorID;
    private String medicalCertificate;
    private int yearsOfXP =0;
    private String specialisation;

    // AdolfsPackage.User user = new AdolfsPackage.User();

    public DoctorValidate validate = new DoctorValidate();
    public Doctor(int userID, int doctorID, String medicalCertificate, int yearsOfXP, String specialisation, String firstName, String lastName, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender) {
        super(userID,firstName, lastName, telephone, dob, isApproved, userType, email, password, gender);
        setMedicalCertificate(medicalCertificate);
        setYearsOfXP(yearsOfXP);
        this.specialisation = specialisation;
    }


    @Override
    public String toString() {
        return "Doctor{" +
                "medicalCertificate='" + medicalCertificate + '\'' +
                ", yearsOfXP=" + yearsOfXP +
                ", specialisation='" + specialisation + '\'' +
                ", validate=" + validate +
                '}';
    }

    public String getUserType(){
        return "AdolfsPackage.Doctor";
    }

    public String getMedicalCertificate() {
        return this.medicalCertificate;
    }

    public void setMedicalCertificate(String medicalCertificate) {
        this.medicalCertificate = medicalCertificate;
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
