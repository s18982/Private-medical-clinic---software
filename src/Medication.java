import java.util.ArrayList;
import java.util.List;

public class Medication {
    private String name;
    private StorageWay storageWay;

    private Patient patient;

    private static List<Medication> extent = new ArrayList<>();
    private List<Warehouse> warehouses =new ArrayList<>();
    private List<Freezer> freezers = new ArrayList<>();
    public List<Doctor> doctors = new ArrayList<>();

    private  List<Patient> patients = new ArrayList<>();


    public Medication(String name) {
        try {
            this.name = name;
            this.storageWay = StorageWay.Ambient;
            addExtent(this);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void addExtent(Medication medication) throws Exception{
        if(!extent.contains(medication)){
            extent.add(medication);
        }else{
            throw new Exception("There is that medication.");
        }
    }

    public void removeExtent(Medication medication) throws Exception{
        if(extent.contains(medication)){
            extent.remove(medication);
        }else
            throw new Exception("There isn't that medication.");
    }

    public void storeMedication(Warehouse warehouse){
        if(freezers.isEmpty() && !warehouses.contains(warehouse)) {
            warehouses.add(warehouse);
            warehouse.addMedication(this);
            this.storageWay=StorageWay.Ambient;
        }
    }

    public void freezeMedication(Freezer freezer){
        if(warehouses.isEmpty() && !freezers.contains(freezer)) {
            freezers.add(freezer);
            freezer.addMedication(this);
            this.storageWay=StorageWay.Frozen;
        }
    }

    public void addPatient(Patient patient) {
        if(!patients.contains(patient)) {
            patients.add(patient);
            patient.addMedication(this);
        }
    }
    public void removePatient(Patient patient){
        if(patients.contains(patient)){
            patients.remove(patient);
            patient.removeMedication(this);
        }
    }

    public List<Freezer> getFreezers() {
        return freezers;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public static List<Medication> getExtent() {
        return extent;
    }

    public String getName() {
        return name;
    }

    public StorageWay getStorageWay() {
        return storageWay;
    }

    public void addDoctor(Doctor doctor){
        if(!doctors.contains(doctor)){
            doctors.add(doctor);
            doctor.addMedication(this);
        }
    }

    public void removeDoctor(Doctor doctor){
        if(doctors.contains(doctor)){
            doctors.remove(doctor);
            doctor.removeMedication(this);
        }
    }

}
