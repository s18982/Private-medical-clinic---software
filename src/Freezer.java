import java.util.ArrayList;
import java.util.List;

public class Freezer {
    private int number;
    private static List<Freezer> extent = new ArrayList<Freezer>();
    private List<Medication> medicationsList = new ArrayList<>();

    public Freezer(int number){
        try {
            setNumber(number);
            addExtent(this);
        } catch (Exception e) {
            System.err.println("The freezer exists");;
        }
    }

    private void setNumber(int id) throws Exception{
        boolean b = false;
        for (Freezer f:extent) {
            if(f.number == id)
                b=true;
        }

        if (b==false)
            this.number=id;
        else
            throw new Exception("");
    }

    public void addExtent(Freezer freezer) throws Exception{
        if(!extent.contains(freezer)){
            extent.add(freezer);
        }else throw new Exception("");
    }

    public void addMedication(Medication newMedication){
        if (!medicationsList.contains(newMedication)){
            medicationsList.add(newMedication);
            newMedication.freezeMedication(this);
        }
    }

    public int getNumber() {
        return number;
    }

    public List<Medication> getMedicationsList() {
        return medicationsList;
    }

    public void showExtent(){
        for (Freezer f:extent
        ) {
            System.out.println(f.getNumber());
        }
    }
}
