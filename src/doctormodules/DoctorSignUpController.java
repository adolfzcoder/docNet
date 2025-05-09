package doctormodules;

import auth.AuthFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Doctor;
import models.Office;
import utils.AlertHelper;

public class DoctorSignUpController {

    @FXML
    private DatePicker birthDay;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private ComboBox<?> gender;

    @FXML
    private TextField lastName;


    @FXML
    private TextField officeNameID;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumberID;

    @FXML
    private TextField specialisation;

    @FXML
    private TextField telephoneID;

    @FXML
    private TextField yearsOfXp;

    @FXML
    void loginClicked(MouseEvent event) {

    }

    @FXML
    void signupButtonClicked(ActionEvent event) {

        try{
            String phoneNumberText = phoneNumberID.getText();
            String telephoneText = telephoneID.getText();
            String firstNameText = firstName.getText();
            String lastNameText = lastName.getText();
            String emailText = email.getText();
            String passwordText = password.getText();
            String confirmPasswordText = confirmPassword.getText();
            String specialisationText = specialisation.getText();
            String yearsOfXpText = yearsOfXp.getText();
            String officeNameText = officeNameID.getText(); // This is a PasswordField; consider renaming for clarity

            String genderText = gender.getValue() != null ? gender.getValue().toString() : "";
            String birthDayText = birthDay.getValue() != null ? birthDay.getValue().toString() : "";
            int years = Integer.parseInt(yearsOfXpText);
            if (!passwordText.equals(confirmPasswordText)) {
                AlertHelper.showError("Registration Failed", "Passwords do not match.");
                return;
            }

            Doctor doctor = new Doctor( years, specialisationText, firstNameText, lastNameText, phoneNumberText, telephoneText, birthDayText, emailText, passwordText, genderText);
            doctor.setOfficeName(officeNameText);
            AuthFunctions.signUp(doctor);



        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
