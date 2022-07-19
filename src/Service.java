import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service {
    TypeOfService type;
    private double cost;
    private double extraFee;

    private List<Service> extent = new ArrayList<>();
    public List<Reservation> reservations = new ArrayList<>();

    public Service(TypeOfService service){
        type = service;
        addExtent(this);
    }

    private void addExtent(Service service){
        if(!extent.contains(service))
            extent.add(service);
    }

    public double getCost(){
        switch (type){
            case Examination:{cost = 150;}
            case Appoiment:{cost = 100;}
            case Treatment:{cost = 200;}
        }
        return cost;
    }

    public void setExtraFee(double extraFee){
        if (extraFee<=1000.0){
            this.extraFee=extraFee;
        }else
            System.err.println("The extra fee cannot be higher than 1000.");
    }

    public double getExtraFee(){
        return extraFee;
    }

    public String toString(){
        String str = type.toString()+"; "+getCost()+"\n";
        for (Reservation r:reservations) {
            str+=r.getDate()+" "+r.getTime()+"\n";
        }
        return str;
    }

    public void addReservation(Reservation reservation){
        if (!reservations.contains(reservation)){
            reservations.add(reservation);
            reservation.setService(this);
        }
    }
    public void removeReservation(Reservation reservation){
        if(reservations.contains(reservation)){
            reservations.remove(reservation);
            reservation.removeService();
        }
    }
}
