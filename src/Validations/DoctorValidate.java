package Validations;

public class DoctorValidate {
    public boolean validateYearsOfXP(int yearXP){
        if (yearXP > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
