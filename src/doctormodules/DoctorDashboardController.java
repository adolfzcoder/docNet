package doctormodules;

import Main.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.Appointment;
import models.Doctor;
import models.User;
import storage.StorageFunctions;
import storage.SystemManager;
import utils.NavigatorHelper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
        SystemManager.flushSession();
        NavigatorHelper.loadScene("auth/login.fxml", "Login");

    }
    @FXML
    private Text username;
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

    @FXML
    void initialize() {
        StorageFunctions storage = new StorageFunctions();

        System.out.println("Before the session empty check");

        if(SystemManager.getSession().isEmpty()){
            System.out.println("Session is empty");
            return;
        }
        User session =  SystemManager.getSession().getFirst();
        int doctorID = session.getUserTypeID();


        System.out.println("Doctor ID:"+doctorID);
        System.out.println("First name doctor:"+session.getFirstName());
        username.setText(session.getFirstName());
        IDAcceptedAppointments.setText(String.valueOf(storage.countTotalAppointmentsAcceptedDoctors(doctorID)));
        IDCompletedAppointments.setText(String.valueOf(storage.countTotalAppointmentsCompletedDoctors(doctorID)));
        IDDeniedAppointments.setText(String.valueOf(storage.countTotalAppointmentsRejectedDoctors(doctorID)));
        IDPendingAppointments.setText(String.valueOf(storage.countTotalAppointmentsPendingDoctors(doctorID)));
        IDOfficeBalance.setText(String.format("%.2f", storage.getOfficeBalance(session.getUserTypeID())));
        IDRating.setText(String.format("%.1f", storage.averageDoctorRating(doctorID)));

        IDtotalAppointments.setText(String.valueOf( storage.countTotalAppointmentsForDoctor(doctorID) ));

        columnPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnAppointmentStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


    }

}
