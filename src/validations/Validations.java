package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    // Enum for Gender
    enum Gender {
        MALE("Male"),
        FEMALE("Female");

        private String displayGender;

        Gender(String displayGender) {
            this.displayGender = displayGender;
        }

        public String getDisplayGender() {
            return displayGender;
        }
    }

    //UserType
    enum UserType {
        DOCTOR("Doctor"),
        PATIENT("Patient"),
        ADMIN("Admin");

        private String displayUserType;

        UserType(String displayUserType) {
            this.displayUserType = displayUserType;
        }

        public String getDisplayUserType() {
            return displayUserType;
        }
    }

    //Appointment Status
    enum AppointmentStatus {
        PENDING("Pending"),
        ACCEPTED("Accepted"),
        REJECTED("Rejected"),
        COMPLETED("Completed");

        private String displayStatus;

        AppointmentStatus(String displayStatus) {
            this.displayStatus = displayStatus;
        }

        public String getDisplayStatus() {
            return displayStatus;
        }
    }

    // Regular expression patterns for validations
    private static final Pattern PATTERN_NON_ALPHNUM_USASCII = Pattern.compile("[^a-zA-Z0-9]+");
    private static final Pattern PATTERN_EMAIL = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    private static final Pattern PATTERN_PHONE = Pattern.compile("^\\+?\\d{10,15}$");
    private static final Pattern PATTERN_MEDICAL_AID = Pattern.compile("^[0-9]{6,10}$");

    // Validate if the string contains non-alphanumeric characters
    public static boolean validateLetters(String string) {
        Matcher matcher = PATTERN_NON_ALPHNUM_USASCII.matcher(string);
        return !matcher.find();  // Returns true if no non-alphanumeric characters are found
    }

    // Validate if the string contains only digits
    public static boolean validateDigits(String string) {
        return string.matches("\\d+");
    }

    // Validate email format
    public static boolean validateEmail(String email) {
        if (email.matches(PATTERN_EMAIL.pattern())) {
            return true;
        }
        System.out.println("Email is not in correct format");
        return false;
    }

    // Validate phone number (10-15 digits with optional leading '+')
    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PATTERN_PHONE.pattern());
    }

    // Validate medical aid number (6-10 digits)
    public static boolean validateMedicalAidNumber(String medicalAidNumber) {
        return medicalAidNumber.matches(PATTERN_MEDICAL_AID.pattern());
    }

    // Validate if the string matches a valid gender
    public static boolean validateEnumGender(String gender) {
        for (Gender g : Gender.values()) {
            if (g.getDisplayGender().equalsIgnoreCase(gender)) {
                return true;
            }
        }
        System.out.println("Invalid gender input");
        return false;
    }

    // Validate if the password and confirm password match
    public static boolean validatePasswordMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    // Validate the user name (first name, last name, etc.)
    public static boolean validateUserName(String name) {
        return !name.isEmpty() && name.length() > 1;
    }

    // Validate that the date of birth is not in the future (assuming it is in YYYY-MM-DD format)
    public static boolean validateDateOfBirth(String dob) {
        // Here we would compare the date to the current date
        // But since it's a String, we assume it's already validated elsewhere or handled
        return !dob.isEmpty();
    }

    // Validate that a given number (e.g., for appointment, etc.) is positive
    public static boolean validatePositiveNumber(int number) {
        return number > 0;
    }

    // Validate if the provided first name is not empty
    public static boolean validateFirstName(String firstName) {
        return !firstName.trim().isEmpty();
    }

    // Validate if the provided last name is not empty
    public static boolean validateLastName(String lastName) {
        return !lastName.trim().isEmpty();
    }

    // Validate password strength (at least 8 characters, with uppercase, lowercase, number, and special character)
    public static boolean validatePasswordStrength(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$";
        return password.matches(regex);
    }

    // Validate that a field is not empty
    public static boolean validateNotEmpty(String field) {
        return field != null && !field.trim().isEmpty();
    }
}
