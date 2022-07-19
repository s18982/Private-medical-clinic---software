import java.util.ArrayList;
import java.util.List;

public class Specialization {
    public String name;
    public List<Doctor> doctors = new ArrayList<>();

    public Specialization(String name){
        this.name = name;
    }

    public void addDoctor(Doctor newDoctor){
        if (!doctors.contains(newDoctor)){
            doctors.add(newDoctor);
            newDoctor.addSpecialization(this);
        }
    }

    public void removeDoctor(Doctor doctor){
        if(doctors.contains(doctor)){
            doctors.remove(doctor);
            doctor.removeSpecialization(this);
        }
    }

    public String toString(){
        String str = name+"\n";
        for (Doctor doctor:doctors) {
            str+=doctor.getLastName()+"\n";
        }
        return str;
    }
}
