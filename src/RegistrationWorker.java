import java.util.ArrayList;
import java.util.List;

public class RegistrationWorker extends Employee{
    public List<Reservation> reservations = new ArrayList<>();

    private static List<RegistrationWorker> extent = new ArrayList<RegistrationWorker>();

    @Override
    public void showData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Pesel: "+this.getPesel()+"\n");
        stringBuilder.append("First Name: "+this.getFirstName()+"\n");
        stringBuilder.append("Last Name: "+this.getLastName()+"\n");
        stringBuilder.append("Phone number: "+this.getPhoneNumber()+"\n");
        stringBuilder.append("Address: "+this.getAddress()+"\n");
        stringBuilder.append("Salary: "+this.getSalary()+"\n");
        System.out.println(stringBuilder);

        if (!reservations.isEmpty()){
            for (Reservation res:reservations) {
                System.out.println(res);
            }
        }
    }

    public RegistrationWorker(String pesel, String firstName, String lastName, String phoneNumber, Address address, double salary) {
        super(pesel, firstName, lastName, phoneNumber, address, salary);
        addExtent(this);
    }

    private void addExtent(RegistrationWorker rw){
        if (!extent.contains(rw)){
            extent.add(rw);
        }
    }

    public void addReservation(Reservation reservation){
        if (!reservations.contains(reservation)){
            reservations.add(reservation);
            reservation.setWorker(this);
        }
    }

}
