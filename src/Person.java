import java.util.ArrayList;
import java.util.List;

public abstract class Person{
    private String pesel;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;

    public Person(String pesel, String firstName, String lastName,String phoneNumber, Address address){
        this.pesel=pesel;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        setAddress(address);
    }
    public void setAddress(Address address){
        if(this.address==null){
            this.address=address;
            address.addPerson(this);
        }
    }


    public Address getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
