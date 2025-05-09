package adminmodules;

import com.sun.javafx.sg.prism.NGNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import models.Appointment;
import models.User;
import storage.DataBaseManager;
import storage.StorageFunctions;
import storage.SystemManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML private Label IDtotalAppointments;
    @FXML private Label IDCompletedAppointments;
    @FXML private Label IDPendingAppointments;
    @FXML private Label IDAcceptedAppointments;
    @FXML private Label IDDeniedAppointments;
    @FXML private Label IDOfficeBalance;
    @FXML private Label IDRating;
    @FXML private Label username;

    @FXML private TableView<Appointment> tableAppointments;
    @FXML private TableColumn<Appointment, String> columnPatientName;
    @FXML private TableColumn<Appointment, String> columnDate;
    @FXML private TableColumn<Appointment, String> columnTime;
    @FXML private TableColumn<Appointment, String> columnAppointmentStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText("Admin"); // Replace this dynamically after admin login

//        columnPatientName.setCellValueFactory(cellData -> cellData.getValue().patientID());
//        columnDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
//        columnTime.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
//        columnAppointmentStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        loadDashboardData();
    }

    private void loadDashboardData() {
        StorageFunctions storage = new StorageFunctions();
        User session = SystemManager.getSession().getFirst();
        ObservableList<Appointment> appointments = (ObservableList<Appointment>) DataBaseManager.getAppointments();
        tableAppointments.setItems(appointments);

        IDtotalAppointments.setText(String.valueOf(appointments.size()));
        IDCompletedAppointments.setText(String.valueOf(storage.countTotalAppointmentsCompletedDoctors(session.getUserTypeID())));
        IDPendingAppointments.setText(String.valueOf(session.getUserTypeID()));
        IDAcceptedAppointments.setText(String.valueOf(session.getUserTypeID()));
        IDDeniedAppointments.setText(String.valueOf(session.getUserTypeID()));

        IDOfficeBalance.setText(storage.getOfficeBalance(session.getUserTypeID()) + "");
        IDRating.setText(String.format(storage.averageDoctorRating(session.getUserTypeID()) + ""));
    }



    @FXML private void totalAppointmentsClicked(MouseEvent event) {}
    @FXML private void todaysAppointmentsClicked(MouseEvent event) {}
    @FXML private void pendingAppointmentsClicked(MouseEvent event) {}
    @FXML private void acceptedAppointmentsClicked(MouseEvent event) {}
    @FXML private void deniedAppointmentsClicked(MouseEvent event) {}
    @FXML private void dashboardRedirectClicked(MouseEvent event) {}
    @FXML private void appointmentsRedirectClicked(MouseEvent event) {}
}
