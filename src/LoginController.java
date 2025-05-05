import auth.AuthFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    void logoutButtonClicked(ActionEvent event) {
        AuthFunctions.logout();
    }

    @FXML
    void signUpButtonClicked(ActionEvent event) {

    }

}
