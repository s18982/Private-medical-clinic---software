import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private int patientCardNumber;

    private static List<Patient> extent = new ArrayList<Patient>();
    private ArrayList<Medication> medications = new ArrayList<Medication>();
    public  List<Doctor> doctors = new ArrayList<Doctor>();

    public List<Reservation> reservations = new ArrayList<>();
    public List<HealthStory> healthStories = new ArrayList<>();

    public Patient(String pesel, String firstName, String lastName, String phone, Address address, int patientCardNumber){
        super(pesel, firstName, lastName, phone, address);
        try {
            setPatientCardNumber(patientCardNumber);
            addExtent(this);
        }catch (Exception e) {
            System.err.println("Setting card number isn't possible.");
        }
    }

    public void setPatientCardNumber(int number) throws Exception{
        boolean b = false;
        for(Patient p:extent){
            if (p.patientCardNumber == number)
                b=true;
        }
        if (b==false)
            this.patientCardNumber=number;
        else throw new Exception("");
    }

    private void addExtent(Patient patient) throws Exception{
        if(!extent.contains(patient)){
            extent.add(patient);
        }else throw new Exception("");
    }

    public static List<Patient> getExtent() {
        return extent;
    }

    public int getPatientCardNumber() {
        return patientCardNumber;
    }

    public void addMedication(Medication med){
        if(!medications.contains(med)) {
            medications.add(med);
            med.addPatient(this);
        }
    }
    public void removeMedication(Medication med){
        medications.remove(med);
        med.removePatient(this);
    }
    public ArrayList<Medication> getMedications (){
        return medications;
    }

    public void addDoctor(Doctor doctor){
        if(!doctors.contains(doctor)){
            doctors.add(doctor);
            doctor.addPatient(this);
        }
    }

    public void removeDoctor(Doctor doctor){
        if(doctors.contains(doctor)){
            doctors.remove(doctor);
            doctor.removePatient(this);
        }
    }

    public void addReservations(Reservation reservation){
        if(!reservations.contains(reservation)){
            reservations.add(reservation);
            reservation.setPatient(this);
        }
    }
    public void removeReservations(Reservation reservation){
        if(reservations.contains(reservation)){
            reservations.remove(reservation);
            reservation.removePatient();
        }
    }
    public String getPatientData(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Pesel: "+this.getPesel()+"\n");
        stringBuilder.append("First Name: "+this.getFirstName()+"\n");
        stringBuilder.append("Last Name: "+this.getLastName()+"\n");
        stringBuilder.append("Phone number: "+this.getPhoneNumber()+"\n");
        stringBuilder.append("Address: "+getAddress()+"\n");
        stringBuilder.append("Patient card number: "+patientCardNumber+"\n");

        if(!medications.isEmpty()){
            stringBuilder.append("List of medications: "+"\n");
            for (Medication medication:medications) {
                stringBuilder.append("\t"+medication.getName()+"\n");
            }
        }
        return stringBuilder.toString();
    }

    public static Patient searchPatient(String pesel){
        Patient patient;

        for (Patient p:extent) {
            if(p.getPesel().equals(pesel)){
                return p;
            }
        }

        return null;
    }
    public static List<Patient> getExtentBis(){
        return extent;
    }

    public void addHealthStory(HealthStory healthStory){
        if(!healthStories.contains(healthStory)){
            healthStories.add(healthStory);
            healthStory.addPatient(this);
        }
    }

    public List<HealthStory> getHealthStories() {
        return healthStories;
    }
}
