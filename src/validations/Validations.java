package validations;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    private static final Pattern PATTERN_PHONE = Pattern.compile("^(081|085)\\d{7}$");

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

    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PATTERN_PHONE.pattern());
    }

    public static boolean validateMedicalAidNumber(String medicalAidNumber) {
        return medicalAidNumber.matches(PATTERN_MEDICAL_AID.pattern());
    }

    public static boolean validateEnumGender(String gender) {
        for (Gender g : Gender.values()) {
            if (g.getDisplayGender().equalsIgnoreCase(gender)) {
                return true;
            }
        }
        System.out.println("Invalid gender input");
        return false;
    }

    public static boolean validatePasswordMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static boolean validateUserName(String name) {
        return !name.isEmpty() && name.length() > 1;
    }

    public static boolean validateDateOfBirth(String dob) {
        if (dob == null || dob.isEmpty()) {
            return false;
        }

        try {
            LocalDate dateOfBirth = LocalDate.parse(dob); // Assuming format is YYYY-MM-DD
            return !dateOfBirth.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false; // Invalid format
        }
    }

    public static boolean validatePositiveNumber(int number) {
        return number > 0;
    }

    public static boolean validateFirstName(String firstName) {
        return firstName != null && firstName.matches("^[A-Za-z]+$");
    }

    public static boolean validateLastName(String lastName) {
        return lastName != null && lastName.matches("^[A-Za-z]+$");
    }

    public static boolean validatePasswordStrength(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$";
        return password.matches(regex);
    }

    public static boolean validateNotEmpty(String field) {
        return field != null && !field.trim().isEmpty();
    }
}
