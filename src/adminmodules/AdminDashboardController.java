package adminmodules;

import com.sun.javafx.sg.prism.NGNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import models.Appointment;

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

        columnPatientName.setCellValueFactory(cellData -> cellData.getValue().patientID());
        columnDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        columnTime.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        columnAppointmentStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        loadDashboardData();
    }

    private void loadDashboardData() {
        ObservableList<Appointment> appointments = DataBaseManager.getInstance().getAllAppointments();
        tableAppointments.setItems(appointments);

        IDtotalAppointments.setText(String.valueOf(appointments.size()));
        IDCompletedAppointments.setText(String.valueOf(countAppointmentsByStatus(appointments, "Completed")));
        IDPendingAppointments.setText(String.valueOf(countAppointmentsByStatus(appointments, "Pending")));
        IDAcceptedAppointments.setText(String.valueOf(countAppointmentsByStatus(appointments, "Accepted")));
        IDDeniedAppointments.setText(String.valueOf(countAppointmentsByStatus(appointments, "Rejected")));

        IDOfficeBalance.setText(String.valueOf(DataBaseManager.getInstance().getTotalOfficeBalance()));
        IDRating.setText(String.format("%.2f", DataBaseManager.getInstance().getAverageDoctorRating()));
    }

    private int countAppointmentsByStatus(ObservableList<Appointment> list, String status) {
        return (int) list.stream().filter(a -> a.getStatus().equalsIgnoreCase(status)).count();
    }

    @FXML private void totalAppointmentsClicked(MouseEvent event) {}
    @FXML private void todaysAppointmentsClicked(MouseEvent event) {}
    @FXML private void pendingAppointmentsClicked(MouseEvent event) {}
    @FXML private void acceptedAppointmentsClicked(MouseEvent event) {}
    @FXML private void deniedAppointmentsClicked(MouseEvent event) {}
    @FXML private void dashboardRedirectClicked(MouseEvent event) {}
    @FXML private void appointmentsRedirectClicked(MouseEvent event) {}
}
