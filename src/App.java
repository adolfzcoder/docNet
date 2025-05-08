
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import static utils.NavigatorHelper.loadScene;

public class App extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        loadScene("/auth/login.fxml", "Login");

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
