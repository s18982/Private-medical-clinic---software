import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Accident {
    private static int failureID;
    private static List<Accident> extent = new ArrayList<>();
    private HelpdeskWorker helpdeskWorker;

    public Accident(){
        failureID++;
        addExtent(this);
    }
    public String toString(){
        return "Accident number: "+ failureID;
    }

    private static void addExtent(Accident accident){
        if(checkExistence(extent,accident)==false)
            extent.add(accident);
    }
    private static void removeExtent(Accident accident){
        if(extent.contains(accident))
            extent.remove(accident);
    }
    public static void showExtent(){
        System.out.println("Extent of the class: "+Accident.class.getName());
        for (Accident accident:
             extent) {
            System.out.println(accident);
        }
    }

    public void addHelpdeskWorker(HelpdeskWorker helpdeskWorker){
        if(this.helpdeskWorker != helpdeskWorker)
            this.helpdeskWorker = helpdeskWorker;
    }

    public void removeHelpdeskWorker(){
        if(this.helpdeskWorker!=null) {
            this.helpdeskWorker.removeAccident(this);
            this.helpdeskWorker = null;
        }
    }

    public int getFailureID() {
        return failureID;
    }


   // ======================================
    private void write(DataOutputStream stream) throws IOException {
        stream.writeShort(failureID);
        //stream.writeUTF(helpdeskWorker);
    }
    private void read(DataInputStream stream) throws IOException{
        failureID= stream.readShort();
        // helpdeskWorker.=stream.readUTF();
    }
    public static void writeExtent(DataOutputStream stream) throws IOException{
        stream.writeInt(extent.size());
        for (Accident accident:extent) {
            accident.write(stream);
        }
    }
    public static void readExtent(DataInputStream stream) throws IOException{
        int objectCount = stream.readInt();
        extent.clear();
        for (int i=0; i<objectCount;i++){
            Accident accident=new Accident();
            accident.read(stream);
        }
    }
    public static void readFromFile(){
        final String extentFile = "data/accident.bin";
        try {
            DataInputStream in2 = new DataInputStream(new BufferedInputStream(new FileInputStream(extentFile)));
            Accident.readExtent(in2);
            in2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeToFile(){
        final String extentFile = "data/accident.bin";
        try {
            DataOutputStream out2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(extentFile)));
            Accident.writeExtent(out2);
            out2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean checkExistence(List<Accident> list, Accident accident){
        boolean b = false;
        for (Accident a:list){
            if(a.getFailureID()==accident.getFailureID())
                b=true;
        }
        return b;
    }
}
