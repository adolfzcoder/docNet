package patientmodules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.Appointment;
import models.Doctor;
import models.User;
import storage.SystemManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class patientBookingController {

    @FXML
    private Button bookButton;

    @FXML
    private ComboBox<String> comboBox;

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

    private User currentUser;

    @FXML
    void initialize() {
        currentUser = SystemManager.getSession().get(0);
        userHandle.setText(currentUser.getFirstName());


        comboBox.getItems().addAll("09:00", "10:00", "11:00", "13:00", "14:00", "15:00");
    }

    @FXML
    void findDoc(MouseEvent event) {
        String name = docName.getText().trim();
        String specialty = spec.getText().trim();

        ArrayList<Doctor> found = SystemManager.fetchDoctors(docName,spec);
        if (found != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Doctor Found");
            alert.setHeaderText(null);
            alert.setContentText("Doctor " + found.getFirst() + " is available.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No doctor found with the provided name and specialty.");
            alert.showAndWait();
        }
    }

    @FXML
    void bookButtonClicked(ActionEvent event) {
        try {
            String name = docName.getText().trim();
            String specialty = spec.getText().trim();
            LocalDate date = datePicker.getValue();
            String timeStr = comboBox.getValue();
            String visitReason = reason.getText().trim();

            if (name.isEmpty() || specialty.isEmpty() || date == null || timeStr == null || visitReason.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Incomplete Form", "Please fill in all fields.");
                return;
            }

            ArrayList<Doctor> doctor = SystemManager.fetchDoctors(docName, spec);
            if (doctor == null) {
                showAlert(Alert.AlertType.ERROR, "Doctor Not Found", "Please enter a valid doctor.");
                return;
            }

            LocalTime time = LocalTime.parse(timeStr);
            Appointment appointment = new Appointment(currentUser.getUserID(), doctor.getFirst(), date, time, visitReason, "PENDING");

            if (appointment.checkBookingsDates(date)) {
                SystemManager.addAppointment(appointment);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Appointment booked successfully.");
            } else {
                showAlert(Alert.AlertType.WARNING, "Time Conflict", "The doctor has another appointment at that time.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Something went wrong: " + e.getMessage());
        }
    }

    @FXML
    void logoutButtonClicked(ActionEvent event) {
        SystemManager.getSession();
        showAlert(Alert.AlertType.INFORMATION, "Logout", "You have been logged out.");

    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
