import javax.print.Doc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Employee{
    private int permissionsNumber;
    private List<Specialization> specializations = new ArrayList<>();
    private Specialization mainSpec;

    private static List<Doctor> extent = new ArrayList<Doctor>();

    public List<Patient> patients = new ArrayList<Patient>();
    public List<Medication> medications = new ArrayList<Medication>();
    public Doctor(String pesel, String firstName, String lastName, String phoneNumber, Address address, double salary, int perNr) {
        super(pesel, firstName, lastName, phoneNumber, address, salary);
        this.permissionsNumber=perNr;
        addExtent(this);
    }

    private void addExtent(Doctor doctor){
        if(!extent.contains(doctor)){
            extent.add(doctor);
        }
    }

    private void removeExtent(Doctor doctor){
        if (extent.contains(doctor)){
            extent.remove(doctor);
        }
    }

    public void addSpecialization(Specialization newSpec){
        if(!specializations.contains(newSpec)){
            specializations.add(newSpec);
            newSpec.addDoctor(this);
        }
    }

    public void removeSpecialization(Specialization spec){
        if(specializations.contains(spec)){
            specializations.remove(spec);
            spec.removeDoctor(this);
        }
    }

    public void addPatient(Patient patient){
        if(!patients.contains(patient)){
            patients.add(patient);
            patient.addDoctor(this);
        }
    }

    public void removePatient(Patient patient){
        if(patients.contains(patient)){
            patients.remove(patient);
            patient.removeDoctor(this);
        }
    }


    public void addMedication(Medication medication){
        if(!medications.contains(medication)){
            medications.add(medication);
            medication.addDoctor(this);
        }
    }

    public void removeMedication(Medication medication){
        if(medications.contains(medication)){
            medications.remove(medication);
            medication.removeDoctor(this);
        }
    }

    public String getWorkerData(){

        String stringBuilder = ("Pesel: " + this.getPesel() + "\n") +
                "First Name: " + this.getFirstName() + "\n" +
                "Last Name: " + this.getLastName() + "\n" +
                "Phone number: " + this.getPhoneNumber() + "\n" +
                "Address: " + this.getAddress() + "\n" +
                "Salary: " + this.getSalary() + "\n" +
                "Permission number: " + permissionsNumber+"\n";

        for (Specialization spec:specializations) {
            stringBuilder+=spec.name+"\n";
        }
        return stringBuilder;
    }

    public void setMainSpec(Specialization specialization) throws Exception{
        if (specializations.contains(specialization)){
            mainSpec = specialization;
        }else throw new Exception("The main specializaton cannot be set. The doctor does not have that specialization.");
    }

    @Override
    public void showData() {
        System.out.println(this.getWorkerData());;
    }

    public String modifyPatientStory(Patient patient, LocalDate localDate, String text){
        if(patients.contains(patient)){
            patient.addHealthStory(new HealthStory(localDate,text,this));
            return "Zmodyfikowano.";
        }else return "Ten lekarz nie może modyfikować tego pacjenta.";
    }

    public String removeMedicationFromList(Patient patient,Medication medication){
        if(patients.contains(patient)){
            if(patient.getMedications().contains(medication)){
                patient.removeMedication(medication);
                return "Zmodyfikowano.";
            }else return "Nie można usunąć leku.";
        }else return "Nie można usunąć leku.";
    }

    public String addMedicationToList(Patient patient,Medication medication){
        if(patients.contains(patient)){
            if(!patient.getMedications().contains(medication)){
                patient.addMedication(medication);
                return "Dodano";
            }else return "Nie można dodać. Pacjent przyjmuje ten lek";
        }else return "Nie ma takiego pacjenta";
    }

    public Medication getMed(String medName){
        boolean bool = false;

        for (Medication med:Medication.getExtent()) {
            if(med.getName().equals(medName))
                return med;
        }
        return null;
    }
}
