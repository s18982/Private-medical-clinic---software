import java.time.LocalDate;

public class HealthStory {
    private LocalDate date;
    private String text;
    private Patient patient;
    private Doctor doctor;

    public HealthStory(LocalDate date, String text, Doctor doctor){
        this.date = date;
        this.text=text;
        this.doctor=doctor;
    }

    public void addPatient(Patient patient){
        if(patient==null){
            this.patient=patient;
            patient.addHealthStory(this);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getText() {
        return text;
    }

    public Patient getPatient() {
        return patient;
    }
}
