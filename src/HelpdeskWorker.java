import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HelpdeskWorker extends Employee {

    private List<HelpdeskWorker> extent = new ArrayList<>();
    private List<Accident> accidents = new ArrayList<>();

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
    }

    public HelpdeskWorker(String pesel, String firstName, String lastName, String phoneNumber, Address address, double salary) {
        super(pesel, firstName, lastName, phoneNumber, address, salary);
        addExtent(this);
    }

    public void addExtent(HelpdeskWorker helpdeskWorker){
        if(!extent.contains(helpdeskWorker)){
            extent.add(helpdeskWorker);
        }
    }
    public void removeExtent(HelpdeskWorker helpdeskWorker){
        if(extent.contains(helpdeskWorker)){
            extent.remove(helpdeskWorker);
        }
    }

    public void addAccident(Accident accident){
        if(!accidents.contains(accident)){
            accidents.add(accident);
            accident.addHelpdeskWorker(this);
        }
    }

    public void removeAccident(Accident accident){
        if(accidents.contains(accident)){
            accidents.remove(accident);
            accident.removeHelpdeskWorker();
        }
    }

    public void showAccidents(){
        System.out.println("Failures handled: ");
        for (Accident a:accidents) {
            System.out.println(a.getFailureID());
        }
    }
}
