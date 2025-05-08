package doctormodules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import storage.StorageFunctions;
import storage.SystemManager;

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

    }

    public void initialize() {
        StorageFunctions storageFunctions = new StorageFunctions();

        // Fetch and display data
        updateDashboard(storageFunctions);
    }


    private void updateDashboard(StorageFunctions storageFunctions) {
        // Fetch data using StorageFunctions
        int totalAppointments = storageFunctions.countTotalAppointmentsForDoctor(SystemManager.getSession().getFirst().getUserTypeID());
        int acceptedAppointments = storageFunctions.countTotalAppointmentsAccepted(SystemManager.getSession().getFirst().getUserTypeID());
        int completedAppointments = storageFunctions.countTotalAppointmentsCompleted(SystemManager.getSession().getFirst().getUserTypeID());
        int rejectedAppointments = storageFunctions.countTotalAppointmentsRejected(SystemManager.getSession().getFirst().getUserTypeID());
        int pendingAppointments = storageFunctions.countTotalAppointmentsPending(SystemManager.getSession().getFirst().getUserTypeID());
        double officeBalance = storageFunctions.getOfficeBalance(SystemManager.getSession().getFirst().getUserTypeID());

        // Set the values in the Dashboard labels
        IDtotalAppointments.setText(String.valueOf(totalAppointments));
        IDAcceptedAppointments.setText(String.valueOf(acceptedAppointments));
        IDCompletedAppointments.setText(String.valueOf(completedAppointments));
        IDDeniedAppointments.setText(String.valueOf(rejectedAppointments));
        IDPendingAppointments.setText(String.valueOf(pendingAppointments));
        IDOfficeBalance.setText(String.format("$%.2f", officeBalance));

        // Example for dynamic doctor's rating
        IDRating.setText("4.8"); // Replace with an actual logic to compute ratings if available
    }



}
