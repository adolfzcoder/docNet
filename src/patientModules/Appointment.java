package patientModules;

import doctorModules.Doctor;
import models.User;

import java.util.ArrayList;

public class Appointment {
    private Doctor doctor;
    private User user;
    private String beginTime;
    private String endTime;
    private String date;
    private String dayOfWeek;
    private String status="PENDING";
    private int appointmentID;


    public Appointment(int appointmentID, User user, Doctor doctor, String beginTime, String endTime, String date, String dayOfWeek, String status) {
        this.appointmentID = appointmentID;
        this.doctor = doctor;
        this.user = user;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        this.date = date;
        this.status = status;
    }



    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public User getUser() {
        return user;
    }

    public void setPatient(User user) {
        this.user= user;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }
}
