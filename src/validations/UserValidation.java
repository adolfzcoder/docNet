package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {
    enum Gender{
        MALE,
        FEMALE
    }
    enum UserType{
        DOCTOR,
        PATIENT,
        ADMIN
    }
    private static final Pattern PATTERN_NON_ALPHNUM_USASCII = Pattern.compile("[^a-zA-Z0-9]+");

    public boolean validateLetters(String string){
        Matcher matcher = PATTERN_NON_ALPHNUM_USASCII.matcher(string);
        return matcher.find();
    }
    public boolean validateDigits(String string){
        return string.matches("\\d+");

    }
    public boolean validateEmail(String email){
        String regex = "^[\\w.-]+@[\\w.-]+\\.\\w+$";
        return email.matches(regex);
    }

    public boolean validateEnumGender(String gender){
        return true;
    }

}
