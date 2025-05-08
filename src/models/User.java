package models;

import validations.Validations;

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

    // Notifications notification = new Notifications();




    public User() {
        // SystemManager.addUser(this);
    }

    public User(int userID, String firstName, String lastName, String phoneNumber, String telephone, String dob, boolean isApproved, String userType, String email, String password, String gender) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.dob = dob;
        this.isApproved = isApproved;
        this.userType = userType;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    //    public void notify(String message) {
//        notifications.add(new Notifications(message));
//    }




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

    public String getName(){
        return firstName +" "+ lastName;
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

        if(Validations.validateLetters(firstName)){
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(Validations.validateLetters(lastName)){
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

    public  int getUserTypeID(){
        return userID;
    }
    public boolean getApproved(){
        return this.isApproved;
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
        if(Validations.validateEmail(email)){
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
