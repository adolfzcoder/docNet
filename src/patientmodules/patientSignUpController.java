package patientmodules;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class patientSignUpController  {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private PasswordField password;
    @FXML private PasswordField confirmPassword;
    @FXML private TextField gender; // Consider changing to ComboBox
    @FXML private DatePicker dob;
    @FXML private Button signUpButton;

    @FXML
    private void initialize() {
        signUpButton.setOnAction(event -> handleSignUp());
    }

    private void handleSignUp() {
        String fname = firstName.getText();
        String lname = lastName.getText();
        String mail = email.getText();
        String pass = password.getText();
        String confirmPass = confirmPassword.getText();
        String gen = gender.getText();
        LocalDate birthDate = dob.getValue();

        if (!pass.equals(confirmPass)) {
            showAlert("Error", "Passwords do not match.");
            return;
        }

        // Proceed with validation or backend communication
        System.out.println("User registered: " + fname + " " + lname);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}