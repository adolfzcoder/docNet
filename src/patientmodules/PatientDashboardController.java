package patientmodules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.Appointment;
import storage.StorageFunctions;
import storage.SystemManager;

import java.time.LocalDate;
import java.time.LocalTime;

public class PatientDashboardController {

    @FXML
    private Label IDCompletedAppointments;

    @FXML
    private Label IDtotalAppointments;

    @FXML
    private TableColumn<Appointment, String> columnAppointmentStatus;

    @FXML
    private TableColumn<Appointment, LocalDate> columnDate;

    @FXML
    private TableColumn<Appointment, String> columnPatientName;

    @FXML
    private TableColumn<Appointment, LocalTime> columnTime;

    @FXML
    private TableView<Appointment> tableAppointments;

    @FXML
    private Text username;

    @FXML
    void bookNewAppointment(ActionEvent event) {

    }

    @FXML
    void cancelAppointment(ActionEvent event) {

    }

    @FXML
    void dashboardRedirectClicked(MouseEvent event) {

    }

    @FXML
    void editAppointment(ActionEvent event) {

    }

    @FXML
    void logoutButtonClicked(ActionEvent event) {

    }

    @FXML
    void manageDoctorsRedirectClicked(MouseEvent event) {

    }

    @FXML
    void rateDoctorClicked(MouseEvent event) {

    }

    @FXML
    void searchDoctorClicked(MouseEvent event) {

    }

    @FXML
    void todaysAppointmentsClicked(MouseEvent event) {

    }

    @FXML
    void totalAppointmentsClicked(MouseEvent event) {

    }
    @FXML
    void initialize() {

        StorageFunctions storage = new StorageFunctions();

        username.setText("Adolf");
        IDCompletedAppointments.setText( String.format(storage.countTotalCompletedAppointmentsPatients()));
        IDtotalAppointments.setText( String.format(String.valueOf(storage.countTotalAppointmentsForPatient(Integer.parseInt(String.valueOf(SystemManager.getSession().getFirst().getUserTypeID()))))) );
    }

}
