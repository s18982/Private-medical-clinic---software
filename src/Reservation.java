import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private LocalDate date;
    private LocalTime time;

    private Service service;
    private RegistrationWorker worker;

    private Patient patient;

    private Office office;

    private List<Reservation> extent = new ArrayList<>();
    public Reservation(LocalDateTime dateTime, Service service, RegistrationWorker worker, Office office){
       changeTime(dateTime);
       setService(service);
       setWorker(worker);
       addExtent(this);
    }

    public Reservation(LocalDateTime dateTime, Service service, Patient patient, Office office){
        changeTime(dateTime);
        setService(service);
        setPatient(patient);
        addExtent(this);
    }

    public void addExtent(Reservation reservation){
        if(!extent.contains(reservation)){
            extent.add(reservation);
        }
    }

    public void changeTime(LocalDateTime dateTime){
        date = dateTime.toLocalDate();
        time = dateTime.toLocalTime();
    }
    public String toString(){
        return this.date+"; "+this.time+"; ";
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Service getService() {
        return service;
    }

    public RegistrationWorker getWorker() {
        return worker;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public void setWorker(RegistrationWorker worker) {
        this.worker = worker;
    }
    public void removeService(){
        this.service=null;
    }
    public void removePatient(){
        this.patient=null;
    }
    public void removeWorker(){
        this.worker=null;
    }

    public void setOffice(Office office){
        this.office = office;
    }
    public void removeOffice(){
        office.removeReservation(this);
        this.office=null;
    }
}
