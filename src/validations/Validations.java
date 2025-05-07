package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    enum Gender{
        MALE,
        FEMALE
    }
    enum UserType{
        DOCTOR,
        PATIENT,
        ADMIN
    }
    enum AppointmentStatus{
        PENDING,
        ACCEPTED,
        REJECTED,
        COMPLETED
    }

    private static final Pattern PATTERN_NON_ALPHNUM_USASCII = Pattern.compile("[^a-zA-Z0-9]+");

    public static boolean validateLetters(String string){
        Matcher matcher = PATTERN_NON_ALPHNUM_USASCII.matcher(string);
        return matcher.find();
    }
    public static boolean validateDigits(String string){
        return string.matches("\\d+");

    }
    public static boolean validateEmail(String email){
        String regex = "^[\\w.-]+@[\\w.-]+\\.\\w+$";

        if(email.matches(regex)){
            return true;
        }
        System.out.println("Email is not in correct format");
        return false;
    }

    public static boolean validateEnumGender(String gender){
        return true;
    }


}
