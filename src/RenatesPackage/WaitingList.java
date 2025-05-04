package RenatesPackage;

import patientModules.Appointment;
import patientModules.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaitingList {
    private int waitingListID;
    private List<Patient> waitingPatients;
    private int maxCapacity = 50; // Default max size
    private Date dateCreated;
    private String status;
    private Appointment appointment;

    public WaitingList(Appointment appt) {

        this.appointment = appt;

        waitingPatients = new ArrayList<>();
        dateCreated = new Date();
        status = "Active";
    }

    // Getters
    public int getWaitingListID() { return waitingListID; }
    public Date getDateCreated() { return dateCreated; }
    public String getStatus() { return status; }

    // Setters
    public void setWaitingListID(int waitingListID) { this.waitingListID = waitingListID; }
    public void setStatus(String status) { this.status = status; }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    // Main Methods
    public String addToWaitingList(Patient patient) {
        if (isFull()) {
            return "Waiting list is full. Cannot add more patients.";
        }
        waitingPatients.add(patient);
        return "Patient successfully added to waiting list.";
    }

    public String removeFromWaitingList(Patient patient) {
        if (waitingPatients.remove(patient)) {
            return "Patient successfully removed from waiting list.";
        } else {
            return "Patient not found in waiting list.";
        }
    }

    public Patient getNextPatient() {
        if (!waitingPatients.isEmpty()) {
            return waitingPatients.get(0);
        }
        return null;
    }

    public boolean isFull() {
        return waitingPatients.size() >= maxCapacity;
    }

    public String viewWaitingList() {
        StringBuilder list = new StringBuilder("Waiting List:\n");
        for (Patient patient : waitingPatients) {
            list.append(patient.getFirstName()).append(" ").append(patient.getLastName()).append("\n");
        }
        return list.toString();
    }
}
