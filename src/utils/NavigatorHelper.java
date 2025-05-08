package utils;

import Main.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigatorHelper {

    public static void loadScene(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource(fxmlPath));
            Stage stage = App.getPrimaryStage(); // you'd need a getter for the primary stage
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
