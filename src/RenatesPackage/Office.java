package RenatesPackage;

import doctorModules.Doctor;
import patientModules.Appointment;

import java.util.ArrayList;
import java.util.List;

public class Office {
    private int officeID;
    private String officeName;
    private String officeLocation;
    private String openingHours;
    private String closingHours;
    private double accountBalance;
    private List<Doctor> doctors;
    private List<Appointment> appointments;

    public Office() {
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    // Getters
    public int getOfficeID() { return officeID; }
    public String getOfficeName() { return officeName; }
    public String getOfficeLocation() { return officeLocation; }
    public String getOpeningHours() { return openingHours; }
    public String getClosingHours() { return closingHours; }
    public double getAccountBalance() { return accountBalance; }

    // Setters
    public void setOfficeID(int officeID) { this.officeID = officeID; }
    public void setOfficeName(String officeName) { this.officeName = officeName; }
    public void setOfficeLocation(String officeLocation) { this.officeLocation = officeLocation; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }
    public void setClosingHours(String closingHours) { this.closingHours = closingHours; }
    public void setAccountBalance(double accountBalance) { this.accountBalance = accountBalance; }

    // Main Methods
    public String addDoctor(Doctor doctor) {
        doctors.add(doctor);
        return "Doctor successfully added to the office.";
    }

    public String removeDoctor(Doctor doctor) {
        if (doctors.remove(doctor)) {
            return "Doctor successfully removed from the office.";
        } else {
            return "Doctor not found.";
        }
    }

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

    public String listDoctors() {
        StringBuilder list = new StringBuilder("Doctors in Office:\n");
        for (Doctor doctor : doctors) {
            list.append(doctor.getFirstName()).append(" ").append(doctor.getLastName()).append("\n");
        }
        return list.toString();
    }
}
