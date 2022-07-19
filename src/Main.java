import java.util.*;

public class Main {
    public static void main(String[] args) {
       //Accident.readFromFile();
       // Accident accident = new Accident();
       // Accident.writeToFile();

         Accident.readFromFile();
         Accident.showExtent();



        Medication medication = new Medication("sralutan");
        Medication medication2 = new Medication("Aspiryna");
        System.out.println(medication.getName()+" "+medication.getExtent());

        Address address = new Address("Kwiatkowicka", "2a", "23-200", "Kraśnik");
        System.out.println(address);

        Patient patient = new Patient("99042608235", "Filip", "Ksiądz", "784555423", address, 12345);
        patient.addMedication(medication);
        patient.addMedication(medication2);


        List<Medication> list = patient.getMedications();

        Patient patient1 = new Patient("123456789","Andrzej","Nowak","123456789",address,2222);

        for (Medication m : list) {
            System.out.println(m.getName());
        }

        Medication medication1=new Medication("sralutan");
        //patient.addMedication(medication);
        for (Medication m:medication.getExtent()
             ) {
            System.out.println(m.getName());

        }


        System.out.println(patient.getPatientData());
        Freezer f = new Freezer(3);

        Freezer w = new Freezer(7);
        Freezer w1 = new Freezer(4);
        //System.out.println(w1.getNumber());


        w.addMedication(medication);
        w1.addMedication(medication);
        for (Freezer fr: medication.getFreezers()) {
            System.out.println(fr.getNumber());
        }

        for (Medication me: w.getMedicationsList()) {
            System.out.println(me.getName());
        }

        Medication medication3 = new Medication("Minirim");
        Medication medication4 = new Medication("Staloral");
        Medication medication5 = new Medication("Apap");


        Doctor doctor =new Doctor("99052608235","Jan","Kowalski","999888777",address,8000.0,123);
        patient.addDoctor(doctor);
        patient1.addDoctor(doctor);
        new Window(doctor);
        Patient.getExtent();

        /*

        //Patient patient1 = new Patient("353", "Filip", "Ksiądz", "784555423", address, 12345);



        RegistrationWorker worker = new RegistrationWorker("98072305465", "Adam", "Nowak", "789654765", address, 3000);
        System.out.println(worker.getBonus());
        System.out.println(worker.getWorkerData());

        int x = 10;
        while (x > 0) {
            Accident accident = new Accident();
            System.out.println(accident.failureID);
            x--;
        }

        Accident.showExtent();


        try {
            worker.setExtraBonus(Optional.of(34.0));
        } catch (Exception e) {
            System.err.println(e.getMessage());;
        }

        System.out.println(worker.getExtraBonusInDouble());
        System.out.println(worker.getAnnualIncome());

        System.out.println(worker.getBonus(0.5));

        // została do zrobienia twała ekstensja

        Service service = new Service(TypeOfService.Examination);
        System.out.println(service.type);

        Specialization orto = new Specialization("Ortopeda");
        Specialization cardio = new Specialization("Kardiolog");
        Doctor doctor = new Doctor(worker.pesel, worker.firstName, worker.lastName, worker.phoneNumber, worker.address, worker.getSalary(), "887");
        doctor.addSpecialization(cardio);
        doctor.addSpecialization(orto);

        Doctor doctor1 =  new Doctor(patient.pesel, patient.firstName, patient.lastName, patient.phoneNumber, address, worker.salary, "342");
        doctor1.addSpecialization(cardio);

        System.out.println(doctor.getWorkerData());
        System.out.println(doctor1.getWorkerData());
        System.out.println(cardio);
        System.out.println(orto);

        RegistrationWorker registrationWorker = new RegistrationWorker(worker.pesel, worker.firstName, worker.lastName, worker.phoneNumber, address, worker.salary);
        Reservation reservation = new Reservation(LocalDateTime.now(),service,registrationWorker);
        registrationWorker.addReservation(reservation);
        service.addReservation(reservation);

        System.out.println(registrationWorker.getWorkerData());
        System.out.println(reservation);
        System.out.println(service);

        Accident accident = new Accident();
        Accident accident1 = new Accident();

        HelpdeskWorker helpdeskWorker = new HelpdeskWorker(worker.pesel, worker.firstName, worker.lastName, worker.phoneNumber, address, worker.getSalary());
        helpdeskWorker.addAccidentsQualif(accident);
        helpdeskWorker.addAccidentsQualif(accident1);
        helpdeskWorker.showAccidents();

        System.out.println("+++++");
        registrationWorker.showData();


        */
    }
}
