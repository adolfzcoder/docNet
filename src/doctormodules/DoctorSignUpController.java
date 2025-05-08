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
    private ComboBox<String> gender;

    @FXML
    private TextField lastName;

    @FXML
    private TextField medicalCertificate;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField phoneNumberIID;

    @FXML
    private TextField specialisation;

    @FXML
    private PasswordField telephoneInput;


    @FXML
    private PasswordField textOfficeName;

    @FXML
    private TextField yearsOfXp;

    @FXML
    void loginClicked(MouseEvent event) {
        try{
            String phoneNumberText = phoneNumberIID.getText();
            String telephoneText = telephoneInput.getText();
            String firstNameText = firstName.getText();
            String lastNameText = lastName.getText();
            String emailText = email.getText();
            String passwordText = password.getText();
            String confirmPasswordText = confirmPassword.getText();
            String medicalCertificateText = medicalCertificate.getText();
            String specialisationText = specialisation.getText();
            String yearsOfXpText = yearsOfXp.getText();
            String officeNameText = textOfficeName.getText(); // This is a PasswordField; consider renaming for clarity

            String genderText = gender.getValue() != null ? gender.getValue().toString() : "";
            String birthDayText = birthDay.getValue() != null ? birthDay.getValue().toString() : "";

            if (!passwordText.equals(confirmPasswordText)) {
                AlertHelper.showError("Registration Failed", "Passwords do not match.");
                return;
            }

            int years = Integer.parseInt(yearsOfXpText);
            Doctor doctor = new Doctor(medicalCertificateText, years, specialisationText, firstNameText, lastNameText, phoneNumberText, telephoneText, birthDayText, emailText, passwordText, genderText);

            AuthFunctions.signUp(doctor);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @FXML
    void signupButtonClicked(ActionEvent event) {

    }

}
