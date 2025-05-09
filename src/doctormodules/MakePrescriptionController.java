package doctormodules;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class MakePrescriptionController {

    @FXML
    private TextField patientIdField;

    @FXML
    private TextField medicineField;

    @FXML
    private TextField dosageField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea instructionsArea;

    @FXML
    private Button submitButton;

    @FXML
    private Button clearButton;

    @FXML
    public void initialize() {
        submitButton.setOnAction(event -> handleSubmit());
        clearButton.setOnAction(event -> handleClear());
    }

    private void handleSubmit() {
        String patientId = patientIdField.getText().trim();
        String medicine = medicineField.getText().trim();
        String dosage = dosageField.getText().trim();
        LocalDate date = datePicker.getValue();
        String instructions = instructionsArea.getText().trim();

        if (patientId.isEmpty() || medicine.isEmpty() || dosage.isEmpty() || date == null) {
            showAlert(Alert.AlertType.ERROR, "Please fill all required fields.");
            return;
        }

        // TODO: Save prescription to the database (DataBaseManager or similar)
        System.out.println("Prescription submitted:\n"
                + "Patient ID: " + patientId + "\n"
                + "Medicine: " + medicine + "\n"
                + "Dosage: " + dosage + "\n"
                + "Date: " + date + "\n"
                + "Instructions: " + instructions);

        showAlert(Alert.AlertType.INFORMATION, "Prescription submitted successfully!");
        handleClear();
    }

    private void handleClear() {
        patientIdField.clear();
        medicineField.clear();
        dosageField.clear();
        datePicker.setValue(null);
        instructionsArea.clear();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Prescription");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
