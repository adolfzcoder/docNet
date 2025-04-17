public class Patient {
    private int medicalAidNumber;
    private double balance;

    public Patient(int medicalAidNumber, double balance){
        setMedicalAidNumber(medicalAidNumber);
        setBalance(balance);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getMedicalAidNumber() {
        return medicalAidNumber;
    }

    public void setMedicalAidNumber(int medicalAidNumber) {
        this.medicalAidNumber = medicalAidNumber;
    }
}
