package doctormodules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import models.Appointment;
import storage.DataBaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TotalAppointmentsController {

    @FXML
    private Label dynamicTotalAppointments;

    @FXML
    private TableView<Appointment> appointmentsTable;

    @FXML
    private TableColumn<Appointment, String> patientNameColumn;

    @FXML
    private TableColumn<Appointment, LocalDate> dateColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> timeColumn;

    @FXML
    void appointmentsRedirectClicked(MouseEvent event) {
    }

    @FXML
    void dashboardRedirectClicked(MouseEvent event) {
    }

    @FXML
    void logoutButtonClicked(ActionEvent event) {
    }

    @FXML
    void manageDoctorsRedirectClicked(MouseEvent event) {
    }

    @FXML
    void profileRedirectClicked(MouseEvent event) {
    }

    @FXML
    public void initialize() {

        loadTotalAppointments();
    }

    private void loadTotalAppointments() {
        ArrayList<Appointment> appointments = DataBaseManager.getAppointments();
        ObservableList<Appointment> totalAppointments = FXCollections.observableArrayList(appointments);
        appointmentsTable.setItems(totalAppointments);

        if (dynamicTotalAppointments != null) {
            dynamicTotalAppointments.setText(String.valueOf(totalAppointments.size()));
        }
    }
}
