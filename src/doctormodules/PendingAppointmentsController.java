package doctormodules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import models.Appointment;
import storage.DataBaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PendingAppointmentsController {

    @FXML
    private Label dynamicTotalPendingAppointments;

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

        loadPendingAppointments();
    }

    private void loadPendingAppointments() {
        ArrayList<Appointment> appointments = DataBaseManager.getAppointments();
        ObservableList<Appointment> pendingAppointments = FXCollections.observableArrayList();

        for (Appointment appointment : appointments) {
            if ("PENDING".equalsIgnoreCase(appointment.getStatus())) {
                pendingAppointments.add(appointment);
            }
        }

        appointmentsTable.setItems(pendingAppointments);
    }
}
