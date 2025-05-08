package storage;

import models.Appointment;
import models.Office;

import java.time.LocalDate;

public class StorageFunctions {

    public int countTotalAppointmentsForDoctor(int doctorID){
        SystemManager sys = new SystemManager();
        int drCount = 0;
        for (Appointment appt : sys.getAppointments()){
            if (appt.getDoctorID() == doctorID){
                drCount++;
            }

        }

        return drCount;


    }

    public int countTotalAppointmentsForPatient(int patientID){
        SystemManager sys = new SystemManager();
        int ptCount = 0;
        for (Appointment appt : sys.getAppointments()){
            if (appt.getPatientID() == patientID){
                ptCount++;
            }

        }

        return ptCount;

    }





//    public int countTotalAppointmentsToday(){
//        SystemManager sys = new SystemManager();
//        LocalDate today = LocalDate.now();
//
//        int totalCount = 0;
//        for( Appointment appt : sys.getAppointments()){
//            if(appt.getAppointmentDate().equals(today)){
//                totalCount++;
//            }
//        }
//        return totalCount;
//    }

    public int countTotalAppointmentsPending(int doctorID){
        SystemManager sys = new SystemManager();

        int totalCount = 0;
        for( Appointment appt : sys.getAppointments()){
            if(appt.getDoctorID()==doctorID && appt.getStatus().equals("PENDING")){
                totalCount++;
            }
        }
        return totalCount;
    }

    public int countTotalAppointmentsApproved(int doctorID){
        SystemManager sys = new SystemManager();

        int totalCount = 0;
        for( Appointment appt : sys.getAppointments()){
            if(appt.getDoctorID()==doctorID && appt.getStatus().equals("ACCEPTED")){
                totalCount++;
            }
        }
        return totalCount;
    }

    public int countTotalAppointmentsCompleted(int doctorID){
        SystemManager sys = new SystemManager();

        int totalCount = 0;
        for( Appointment appt : sys.getAppointments()){
            if(appt.getDoctorID()==doctorID && appt.getStatus().equals("COMPLETED")){
                totalCount++;
            }
        }
        return totalCount;
    }
    public int countTotalAppointmentsAccepted(int doctorID){
        SystemManager sys = new SystemManager();

        int totalCount = 0;
        for( Appointment appt : sys.getAppointments()){
            if(appt.getDoctorID()==doctorID && appt.getStatus().equals("ACCEPTED")){
                totalCount++;
            }
        }
        return totalCount;
    }
    public int countTotalAppointmentsRejected(int doctorID){
        SystemManager sys = new SystemManager();

        int totalCount = 0;
        for( Appointment appt : sys.getAppointments()){
            if( appt.getDoctorID()==doctorID && appt.getStatus().equals("REJECTED")){
                totalCount++;
            }
        }
        return totalCount;
    }
    public double getOfficeBalance(int officeID){
        SystemManager sys = new SystemManager();
        double balance = 0;
        for(Office office : sys.getOffices()){
            if(office.getOfficeID()==officeID){
                balance = office.getAccountBalance();
            }

        }
        return balance;
    }



    public int countTotalAppointments(){
        SystemManager sys = new SystemManager();
        int totalCount = 0;
        for (Appointment appt : sys.getAppointments()){
            totalCount++;
        }
        return totalCount;
    }
}
