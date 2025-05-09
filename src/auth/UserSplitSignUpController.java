package auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import utils.NavigatorHelper;

public class UserSplitSignUpController {

    @FXML
    void doctorRegister(ActionEvent event) {
        NavigatorHelper.loadScene("doctormodules/DoctorSignUp.fxml", "Doctor Sign Up");
    }

    @FXML
    void patientRegister(ActionEvent event) {
        NavigatorHelper.loadScene("patientmodules/patientSignUp.fxml", "Patient Sign Up");

    }

    @FXML
    void switchToLogin(MouseEvent event) {
        NavigatorHelper.loadScene("auth/login.fxml", "User Login");

    }


}
