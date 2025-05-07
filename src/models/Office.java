package models;

import doctormodules.Doctor;
import storage.SystemManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Office {
    private int officeID;
    private String officeName;
    private String officeLocation;
    private LocalTime openingHours;
    private LocalTime closingHours;
    private double accountBalance;
    private ArrayList<Appointment> appointments;
    public Office(int officeID, String officeName, String officeLocation, LocalTime openingHours, LocalTime closingHours, double accountBalance) {
        this.officeID = officeID;
        this.officeName = officeName;
        this.officeLocation = officeLocation;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.accountBalance = accountBalance;


        //

        ArrayList<Doctor> doctors = SystemManager.getOfficeDoctors(officeID);


        appointments = SystemManager.getAppointments();




    }

//    public Office() {
//
//    }

    // Getters
    public int getDoctorID;
    public int getOfficeID() { return officeID; }
    public String getOfficeName() { return officeName; }
    public String getOfficeLocation() { return officeLocation; }
    public LocalTime getOpeningHours() { return openingHours; }
    public LocalTime getClosingHours() { return closingHours; }
    public double getAccountBalance() { return accountBalance; }

    // Setters
    public void setOfficeID(int officeID) { this.officeID = officeID; }
    public void setOfficeName(String officeName) { this.officeName = officeName; }
    public void setOfficeLocation(String officeLocation) { this.officeLocation = officeLocation; }
    public void setOpeningHours(LocalTime openingHours) { this.openingHours = openingHours; }
    public void setClosingHours(LocalTime closingHours) { this.closingHours = closingHours; }
    public void setAccountBalance(double accountBalance) { this.accountBalance = accountBalance; }

    public String scheduleAppointment(Appointment appointment) {
        appointments.add(appointment);
        return "Appointment scheduled successfully.";
    }

    public String cancelAppointment(Appointment appointment) {
        if (appointments.remove(appointment)) {
            return "Appointment cancelled successfully.";
        } else {
            return "Appointment not found.";
        }
    }

    public String viewOfficeDetails() {
        return "Office Name: " + officeName + "\nLocation: " + officeLocation + 
               "\nOpening Hours: " + openingHours + "\nClosing Hours: " + closingHours;
    }

//    public String listDoctors() {
//        StringBuilder list = new StringBuilder("Doctors in Office:\n");
//        for (Doctor doctor : doctors) {
//            list.append(doctor.getFirstName()).append(" ").append(doctor.getLastName()).append("\n");
//        }
//        return list.toString();
//    }
}
