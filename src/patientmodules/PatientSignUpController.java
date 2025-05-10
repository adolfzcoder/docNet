package patientmodules;

import auth.AuthFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Patient;
import storage.SystemManager;
import utils.AlertHelper;
import utils.NavigatorHelper;

public class PatientSignUpController {

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
    private TextField medicalAidNumber;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField telephone;

    @FXML
    void comboboxGender(ActionEvent event) {

    }

    @FXML
    void switchToLoginPage(MouseEvent event) {
        NavigatorHelper.loadScene("auth/login.fxml", "Login");
    }

    @FXML
    void signUpButtonCicked(ActionEvent event) {

        try{

            String fName = firstName.getText();
            String lName = lastName.getText();
            String userEmail = email.getText();
            String userPassword = password.getText();
            String confirmPass = confirmPassword.getText();
            String pNumber = phoneNumber.getText();
            String tNumber = telephone.getText();
            String genderValue = "MALE"; // assuming gender ComboBox holds Strings
            String dob = birthDay.getValue().toString(); // ISO format YYYY-MM-DD
            int medAidNum = Integer.parseInt(medicalAidNumber.getText());

            if (!userPassword.equals(confirmPass)) {
              //   AlertHelper.showError("Registration Failed", "Passwords do not match.");
                return;
            }


            Patient newPatient = new Patient(
                    medAidNum,
                    fName,
                    lName,
                    pNumber,
                    tNumber,
                    dob,
                    userEmail,
                    userPassword,
                    genderValue
            );

            AuthFunctions.signUp(newPatient);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }



    }



}
