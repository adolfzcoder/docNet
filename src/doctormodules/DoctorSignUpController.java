package doctormodules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    private TextField medicalCertificate;

    @FXML
    private PasswordField password;

    @FXML
    private TextField specialisation;

    @FXML
    private PasswordField textOfficeName;

    @FXML
    private TextField yearsOfXp;

    @FXML
    void loginClicked(MouseEvent event) {

    }

    @FXML
    void signupButtonClicked(ActionEvent event) {

    }

}
