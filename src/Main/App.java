package Main;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import static utils.NavigatorHelper.loadScene;

public class App extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        loadScene("auth/login.fxml", "Login");
        // loadScene("doctormodules/DoctorSignUp.fxml", "Doctor Sign up");


    }



    public static Stage getPrimaryStage() {
            return primaryStage;
    }


    public static void main(String[] args) {
        // System.out.println("test");

        // SystemManager.initializeLists();


        launch(args); // Launches the JavaFX application
    }
}
