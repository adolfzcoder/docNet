package models;

import validations.UserValidation;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String telephone;
    private String dob;
    private boolean isApproved=false;
    private String userType;
    private String phoneNumber;


    private String email;
    private String password;
    private String gender;
    public UserValidation validate= new  UserValidation();

    // Notifications notification = new Notifications();


    public User(int userID, String firstName, String lastName, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender) {

    // this.notifications = new ArrayList<>();

    }

    public User() {

    }

//    public void notify(String message) {
//        notifications.add(new Notifications(message));
//    }
    public UserValidation getValidate() {
        return validate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dob='" + dob + '\'' +
                ", isApproved=" + isApproved +
                ", userType='" + userType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    // this method is obsolete, will be removed
    public void createUser(String firstName, String lastName, String telephone, String dob, String userType, String email){

        setFirstName(firstName);
        setLastName(lastName);
        setTelephone(telephone);
        setDob(dob);
        setEmail(email);
        setPassword(password);
        setUserType(userType);
        setEmail(email);
        setPassword(password);
        setGender(gender);

        System.out.println("Successfully created user");

    }


    public void setValidate(UserValidation validate) {
        this.validate = validate;
    }


    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String number){
        this.phoneNumber = number;
    }


    public int getUserID(){
        return this.userID;
    }

    public void setUserID(int userID){
        this.userID = userID;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
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
