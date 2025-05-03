package patientModules;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int appointmentID;
    private int patientID;
    private int doctorID;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reasonForVisit;
    private String status;

    public Appointment() {
        this.status = "Pending"; // Default initial status
    }

    public Appointment(int appointmentID, int patientID, int doctorID, LocalDate appointmentDate, LocalTime appointmentTime, String reason) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reasonForVisit = reason;
        this.status = "Scheduled"; // Default when created
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void makeAppointment(int appointmentID, int patientID, int doctorID, LocalDate appointmentDate, LocalTime appointmentTime, String reason) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reasonForVisit = reason;
        this.status = "Scheduled";
    }

    public void editAppointment(LocalDate newDate, LocalTime newTime, String newReason) {
        if (!"Cancelled".equals(this.status)) {
            this.appointmentDate = newDate;
            this.appointmentTime = newTime;
            this.reasonForVisit = newReason;
            this.status = "Rescheduled";
        } else {
            System.out.println("Cannot edit a cancelled appointment.");
        }
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
    }

    public void confirmAppointment() {
        if (!"Cancelled".equals(this.status)) {
            this.status = "Confirmed";
        } else {
            System.out.println("Cannot confirm a cancelled appointment.");
        }
    }

    public void declineAppointment() {
        if (!"Cancelled".equals(this.status)) {
            this.status = "Declined";
        } else {
            System.out.println("Cannot decline a cancelled appointment.");
        }
    }
}
