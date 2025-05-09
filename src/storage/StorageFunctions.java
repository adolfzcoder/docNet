package storage;

import models.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class StorageFunctions {
    private SystemManager sys = new SystemManager();
    public int countTotalAppointmentsForDoctor(int doctorID){
        int drCount = 0;
        for (Appointment appt : SystemManager.getAppointments()){
            if (appt.getDoctorID() == doctorID){
                drCount++;
            }

        }


        return drCount;


    }

    public int countTotalAppointmentsForPatient(int patientID){
        int ptCount = 0;
        for (Appointment appt : sys.getAppointments()){
            if (appt.getPatientID() == patientID){
                ptCount++;
            }

        }

        return ptCount;

    }

    public int countTotalAppointmentsPendingDoctors(int doctorID){

        int totalCount = 0;
        for( Appointment appt : sys.getAppointments()){
            if(appt.getDoctorID()==doctorID && appt.getStatus().equals("PENDING")){
                totalCount++;
            }
        }
        return totalCount;
    }


    public int countTotalAppointmentsCompletedDoctors(int doctorID){

        int totalCount = 0;
        for( Appointment appt : sys.getAppointments()){
            if(appt.getDoctorID()==doctorID && appt.getStatus().equals("COMPLETED")){
                totalCount++;
            }
        }
        return totalCount;
    }
    public int countTotalAppointmentsAcceptedDoctors(int doctorID){

        int totalCount = 0;
        for( Appointment appt : sys.getAppointments()){
            if(appt.getDoctorID()==doctorID && appt.getStatus().equals("ACCEPTED")){
                totalCount++;
            }
        }
        return totalCount;
    }
    public int countTotalAppointmentsRejectedDoctors(int doctorID){

        int totalCount = 0;
        for( Appointment appt : sys.getAppointments()){
            if( appt.getDoctorID()==doctorID && appt.getStatus().equals("REJECTED")){
                totalCount++;
            }
        }
        return totalCount;
    }
    public double getOfficeBalance(int drID){
        double balance = 0;
        int officeID = sys.findOfficeIDByDoctorID(drID);
        for(Office office : sys.getOffices()){

            if(office.getOfficeID()==officeID){

                return office.getAccountBalance();

            }

        }
        return balance;
    }


    public double averageDoctorRating(int doctorID){
        return sys.calculateDoctorAvgRating(doctorID);
    }
    public int countTotalAppointments(){
        int totalCount = 0;
        for (Appointment appt : sys.getAppointments()){
            totalCount++;
        }
        return totalCount;
    }














    //patients
    public static int countPrescriptions(){
        ArrayList<Prescription> allPrescriptions = SystemManager.getPrescriptions();
        ArrayList<User> session = SystemManager.getSession();
        int count = 0;
        for (Prescription prescription : allPrescriptions){
            if (prescription.getPatientID() == session.getFirst().getUserTypeID()) {
                count++;
            }
        }
        return count;
    }
}
