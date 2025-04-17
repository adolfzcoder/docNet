import validations.DoctorValidate;

public class Doctor {
    private String medicalCertificatePath;
    private int yearsOfXP =0;
    private String specialisation;


    public DoctorValidate validate = new DoctorValidate();
    public Doctor(String medicalCertificatePath, int yearsOfXP, String specialisation) {
        this.medicalCertificatePath = medicalCertificatePath;
        setYearsOfXP(yearsOfXP);
        this.specialisation = specialisation;
    }


    public String getMedicalCertificatePath() {
        return medicalCertificatePath;
    }

    public void setMedicalCertificatePath(String medicalCertificatePath) {
        this.medicalCertificatePath = medicalCertificatePath;
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
