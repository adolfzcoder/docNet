package Appointment;

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

    public Appointment(int appointmentID, int patientID, int doctorID, LocalDate appointmentDate, LocalTime appointmentTime, String reason) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reasonForVisit = reason;
        this.status = status;
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

    //method to update the schedule
    public void updateSchedule(LocalDate appointmentDate, LocalTime appointmentTime) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        System.out.println("The appointment has been updated to " + this.appointmentDate + " at " + this.appointmentTime);
    }

    //method to see all the appointments
    public void viewAllSchedule() {
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Patient ID: " + patientID);
        System.out.println("Doctor ID: " + doctorID);
        System.out.println("Date: " + appointmentDate);
        System.out.println("Time: " + appointmentTime);
        System.out.println("Reason: " + reasonForVisit);
        System.out.println("Status: " + status);
    }

    //checks whether the schedule is empty
    //should be improved to check for a specific day
    public boolean isScheduleEmpty() {
        return (appointmentDate == null || appointmentTime == null);
    }

    public void removeTask() {
        this.appointmentDate = null;
        this.appointmentTime = null;
        this.reasonForVisit = null;
        this.status = "Cancelled";
        System.out.println("Appointment has been cancelled.");
    }
}
