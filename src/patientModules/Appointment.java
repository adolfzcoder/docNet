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
    private String status="PENDING";


    ArrayList<Appointment> appointments = new ArrayList<>();

    public Appointment(User user, Doctor doctor, String beginTime, String endTime, String date) {
        this.doctor = doctor;
        this.user = user;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.date = date;
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

    public void setStatus(String status) {
        this.status = status;
    }
}
