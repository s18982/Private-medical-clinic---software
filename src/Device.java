import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Device {
    private String name;
    private Office office;

    private static List<Device> extent = new ArrayList<>();

    private Device(){
        addExtent(this);
    }
    public Device(Office office,String name){
        try {
            setOffice(office);
            this.name=name;
            addExtent(this);
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void addExtent(Device device){
        if(!extent.contains(device)){
            extent.add(device);
        }
    }
    public void removeExtent(Device device){
        if(extent.contains(device)){
            extent.remove(device);
        }
    }

    public void setOffice(Office office) throws Exception{
        this.office=office;
        office.addDevice(this);
    }

    public void removeOffice() throws Exception{
        office.removeDevice(this);
        this.office=null;
    }

    public String getName() {
        return name;
    }

    public Office getOffice() {
        return office;
    }

    // ======================================
    private void write(DataOutputStream stream) throws IOException {
        stream.writeUTF(name);
        //stream.writeUTF(office.toString());
    }
    private void read(DataInputStream stream) throws IOException{
        name=stream.readUTF();

    }
    public static void writeExtent(DataOutputStream stream) throws IOException{
        stream.writeInt(extent.size());
        for (Device device:extent) {
            device.write(stream);
        }
    }
    public static void readExtent(DataInputStream stream) throws IOException{
        int objectCount = stream.readInt();
        extent.clear();
        for (int i=0; i<objectCount;i++){
            Device device=new Device();
            device.read(stream);
        }
    }
    public static void readFromFile(){
        final String extentFile = "data/device.bin";
        try {
            DataInputStream in2 = new DataInputStream(new BufferedInputStream(new FileInputStream(extentFile)));
            Device.readExtent(in2);
            in2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeToFile(){
        final String extentFile = "data/device.bin";
        try {
            DataOutputStream out2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(extentFile)));
            Device.writeExtent(out2);
            out2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean checkExistence(List<Device> list, Device device){
        boolean b = false;
        for (Device d:list){
            if(d.getName()== device.getName())
                b=true;
        }
        return b;
    }
}
