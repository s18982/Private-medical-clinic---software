import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private int number;
    private static List<Warehouse> extent = new ArrayList<Warehouse>();
    private List<Medication> medicationsList = new ArrayList<>();

    public Warehouse(int number){
        try {
            setNumber(number);
            addExtent(this);
        } catch (Exception e) {
            System.err.println("The warehouse exists");
        }
    }

    private void setNumber(int id) throws Exception{
        boolean b = false;
        for (Warehouse w:extent) {
            if(w.number == id)
                b=true;
        }

        if (b==false)
            this.number=id;
        else
            throw new Exception("");
    }

    public void addExtent(Warehouse warehouse) throws Exception{
        if(!extent.contains(warehouse)){
            extent.add(warehouse);
        }else throw new Exception("");
    }

    public void addMedication(Medication newMedication){
        if (!medicationsList.contains(newMedication)){
            medicationsList.add(newMedication);
            newMedication.storeMedication(this);
        }
    }

    public List<Warehouse> getExtent() {
        return extent;
    }

    public List<Medication> getMedicationsList() {
        return medicationsList;
    }

    public void showExtent(){
        for (Warehouse w:extent
             ) {
            System.out.println(w.getNumber());
        }
    }

    public int getNumber() {
        return number;
    }
}
