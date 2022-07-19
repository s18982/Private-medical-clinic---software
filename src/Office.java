import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Office {

    private int number;
    List<Device> devices = new ArrayList<>();
    private static List<Office> extent = new ArrayList<>();

    private List<Reservation> reservations = new ArrayList<>();

    public Office(int number) {
        try {
            setNumber(number);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setNumber(int number) throws Exception{
        boolean exist = false;
        for (Office office:extent) {
            if (office.number == number)
                exist = true;
        }

        if (exist == true) {
            throw new Exception("There is the office with that number.");
        }else {
            this.number = number;
            addExtent(this);
        }
    }
    public void addDevice(Device device) throws Exception{
        if (!devices.contains(device)) {
            devices.add(device);
            device.setOffice(this);
        }else throw new Exception("You cannot add the device");
    }
    public void removeDevice(Device device) throws Exception{
        if (devices.contains(device)) {
            devices.remove(device);
            device.removeOffice();
        }else throw new Exception("You cannot remove the device");
    }

    public String toString(){
        String str = "Equipment of office number: "+number+"\n";
        for (Device d:devices){
            str+=d.getName()+"\n";
        }
        return str;
    }
    public static void addExtent(Office office){
        if(!extent.contains(office)){
            extent.add(office);
        }

    }
    public static void removeExtent(Office office){
        if(extent.contains(office))
            extent.remove(office);
    }

    public void addReservation(Reservation reservation){
        if(!reservations.contains(reservation)){
            reservations.add(reservation);
            reservation.setOffice(this);
        }
    }
    public void removeReservation(Reservation reservation){
        if(reservations.contains(reservation)){
            reservations.remove(reservation);
            reservation.removeOffice();
        }
    }



}
