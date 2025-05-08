package doctormodules;

import Main.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import models.Appointment;
import utils.NavigatorHelper;

import java.time.LocalTime;

public class DoctorDashboardController {

    @FXML
    private Label IDAcceptedAppointments;

    @FXML
    private Label IDCompletedAppointments;

    @FXML
    private Label IDDeniedAppointments;

    @FXML
    private Label IDOfficeBalance;

    @FXML
    private Label IDPendingAppointments;

    @FXML
    private Label IDRating;

    @FXML
    private Label IDtotalAppointments;

    @FXML
    private TableColumn<?, ?> columnAppointmentStatus;

    @FXML
    private TableColumn<?, ?> columnDate;

    @FXML
    private TableColumn<?, ?> columnPatientName;

    @FXML
    private TableColumn<Appointment, LocalTime> columnTime;

    @FXML
    private TableView<Appointment> tableAppointments;

    @FXML
    void acceptedAppointmentsClicked(MouseEvent event) {

    }

    @FXML
    void appointmentsRedirectClicked(MouseEvent event) {

    }

    @FXML
    void dashboardRedirectClicked(MouseEvent event) {

    }

    @FXML
    void deniedAppointmentsClicked(MouseEvent event) {

    }

    @FXML
    void logoutButtonClicked(ActionEvent event) {

    }

    @FXML
    void manageDoctorsRedirectClicked(MouseEvent event) {

    }

    @FXML
    void pendingAppointmentsClicked(MouseEvent event) {

    }

    @FXML
    void profileRedirectClicked(MouseEvent event) {

    }

    @FXML
    void todaysAppointmentsClicked(MouseEvent event) {

    }

    @FXML
    void totalAppointmentsClicked(MouseEvent event) {
        NavigatorHelper.loadScene("", "");
    }



}
