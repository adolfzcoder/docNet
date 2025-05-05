import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Your UI setup goes here
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        // Label label = new Label("Hello, JavaFX!");
        // StackPane root = new StackPane(label);
        Scene scene = new Scene(root);

        primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("test");
        launch(args); // Launches the JavaFX application
    }
}
