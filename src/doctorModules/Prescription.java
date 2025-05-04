package doctorModules;

import java.util.Date;

public class Prescription {

        private int prescriptionID;
        private String instruction;
        private int doctorID;
        private int patientID;
        private int appointmentID;
        private Date issueDate;


        // Constructor
        public Prescription(int prescriptionID, String instruction, int doctorID, int patientID, int appointmentID, Date issueDate) {
            setPrescriptionID(prescriptionID);
            setInstruction(instruction);
            setDoctorID(doctorID);
            setPatientID(patientID);
            setAppointmentID(appointmentID);
            setIssueDate(issueDate);
        }

        // Getters
        public int getPrescriptionID() {
            return prescriptionID;
        }

        public String getInstruction() {
            return instruction;
        }

        public int getDoctorID() {
            return doctorID;
        }

        public int getPatientID() {
            return patientID;
        }

        public int getAppointmentID() {
            return appointmentID;
        }

        public Date getIssueDate() {
            return issueDate;
        }

        // Setters
        public void setPrescriptionID(int prescriptionID) {
            this.prescriptionID = prescriptionID;
        }

        public void setInstruction(String instruction) {
            this.instruction = instruction;
        }

        public void setDoctorID(int doctorID) {
            this.doctorID = doctorID;
        }

        public void setPatientID(int patientID) {
            this.patientID = patientID;
        }

        public void setAppointmentID(int appointmentID) {
            this.appointmentID = appointmentID;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        // Methods
        public String updateInstruction(int prescriptionID, String newInstruction) {
            if(this.prescriptionID == prescriptionID) {
                this.instruction = newInstruction;
                return "Instruction updated successfully.";
            } else {
                return "Prescription ID not found.";
            }
        }

        public String deletePrescription(int prescriptionID) {
            if(this.prescriptionID == prescriptionID) {
                this.prescriptionID = 0;
                this.instruction = null;
                this.doctorID = 0;
                this.patientID = 0;
                this.appointmentID = 0;
                this.issueDate = null;
                return "Prescription deleted successfully.";
            } else {
                return "Prescription ID not found.";
            }
        }
    }


