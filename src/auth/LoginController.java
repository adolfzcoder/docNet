package auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utils.AlertHelper;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    void loginRedirectPressed(MouseEvent event) {

    }
    @FXML
    void loginButtonClicked(ActionEvent event) {

        String emailText = email.getText().trim();
        String passwordText = password.getText().trim();

        try{

            if(emailText.isEmpty()){
                AlertHelper.showError("Empty Email", "Email cannot be empty");
                System.out.println("Email cannot be empty");
                return;
            }

            if(passwordText.isEmpty()){
                AlertHelper.showError("Empty Password", "Password cannot be empty");
                System.out.println("Password cannot be empty");
                return;
            }

            AuthFunctions.authenticateUser(emailText, passwordText);
            System.out.println("Successfully logged in");

            AlertHelper.showSuccess("Logged in", "Successfully logged in");


        }catch(Exception e){
            System.out.println("Something went wrong");
            AlertHelper.showError("Error", "Something went wrong");
        }


    }

}
