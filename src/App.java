import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storage.SystemManager;

import java.io.IOException;

public class App extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        loadScene("auth/login.fxml", "Login");

    }

    public static void loadScene(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource(fxmlPath));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(title);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        // System.out.println("test");

        // SystemManager.initializeLists();


        launch(args); // Launches the JavaFX application
    }
}
