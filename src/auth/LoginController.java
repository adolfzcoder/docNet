package auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.User;
import storage.SystemManager;
import utils.AlertHelper;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private TextField password;


    @FXML
    void loginButtonClicked(ActionEvent event) {
        System.out.println("Login button clicked");
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

            User session = SystemManager.getSession().getFirst();
            System.out.println(session.getName());
            System.out.println(session.getUserType());
            System.out.println("Successfully logged in");

            AlertHelper.showSuccess("Logged in", "Successfully logged in");


        }catch(Exception e){
            System.out.println("Something went wrong");
            AlertHelper.showError("Error", "Something went wrong");
        }


    }

}
