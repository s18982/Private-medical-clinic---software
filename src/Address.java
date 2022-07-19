import java.util.ArrayList;
import java.util.List;

public class Address {
    private String city;
    private String street;
    private String houseNumber;
    private String postCode;

    private List<Person> personList = new ArrayList<Person>();

    public Address(String street, String houseNumber, String postCode, String city){
        this.city=city;
        this.houseNumber=houseNumber;
        this.street=street;
        this.postCode=postCode;
    }
    public void addPerson(Person person){
        if(!personList.contains(person)){
            personList.add(person);
        }
    }
    public void removePerson(Person person){
        if (personList.contains(person)){
            personList.remove(person);
        }
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public String toString(){
        return street+" "+houseNumber+"; "+postCode+" "+city;
    }

}
