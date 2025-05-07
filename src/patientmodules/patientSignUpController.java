package patientmodules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Patient;

public class patientSignUpController {

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
    void signUpButtonCicked(ActionEvent event) {


        // Patient patient = new Patient(0, 0, )

    }

}
