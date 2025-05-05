package UIandControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class patientBookingController  {

    @FXML
    private Button bookButton;

    @FXML
    private ComboBox<?> comboBox;

    @FXML
    private Text dash;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField docName;

    @FXML
    private Text findDoc;

    @FXML
    private Button logout;

    @FXML
    private TextArea reason;

    @FXML
    private TextField spec;

    @FXML
    private Text userHandle;

    @FXML
    void bookButtonClicked(ActionEvent event) {

    }

    @FXML
    void findDoc(MouseEvent event) {

    }

    @FXML
    void logoutButtonClicked(ActionEvent event) {

    }

}
