import validations.UserValidation;

public class User {
    private String firstName;
    private String lastName;
    private int telephone;
    private String dob;
    private boolean isApproved;
    private String userType;


    private String email;
    private String password;
    private String gender;
    public UserValidation validate= new  UserValidation();



    public User(String firstName, String lastName, int telephone, String dob, boolean isApproved, String userType, String email, String password, String gender) {
        setFirstName(firstName);
        setLastName(lastName);
        setTelephone(telephone);
        setDob(dob);
        setApproved(isApproved);
        setEmail(email);
        setPassword(password);
        setUserType(userType);
        setEmail(email);
        setPassword(password);
        setGender(gender);


    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        if(validate.validateLetters(firstName)){
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(validate.validateLetters(lastName)){
            this.lastName = lastName;
        }
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(validate.validateEmail(email)){
            this.email = email;
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
