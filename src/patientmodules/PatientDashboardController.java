package patientmodules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class PatientDashboardController {

    @FXML
    private Label IDCompletedAppointments;

    @FXML
    private Label IDRating1;

    @FXML
    private Label IDtotalAppointments;

    @FXML
    private TableColumn<?, ?> columnAppointmentStatus;

    @FXML
    private TableColumn<?, ?> columnDate;

    @FXML
    private TableColumn<?, ?> columnPatientName;

    @FXML
    private TableColumn<?, ?> columnTime;

    @FXML
    private TableView<?> tableAppointments;

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
    void todaysAppointmentsClicked(MouseEvent event) {

    }

    @FXML
    void totalAppointmentsClicked(MouseEvent event) {

    }

}
