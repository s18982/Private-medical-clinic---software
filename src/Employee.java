import java.math.BigDecimal;
import java.util.Optional;

public abstract class Employee extends Person{
    private double salary;
    private double bonus;
    private Optional<Double> extraBonus = Optional.empty();

    public abstract void showData();

    public Employee(String pesel, String firstName, String lastName, String phoneNumber, Address address, double salary) {
        super(pesel, firstName, lastName, phoneNumber, address);
        this.salary=salary;
    }
    public void setSalary(double salary){
        this.salary=salary;
    }
    public double getSalary(){
        return salary;
    }
    public double getBonus(){
        bonus = 0.1*12*salary;
        return bonus;
    }
    public double getBonus(double fractionAnnualSalary){
        bonus = fractionAnnualSalary*12*salary;
        return bonus;
    }
    public Optional<Double> getExtraBonus(){
        return extraBonus;
    }
    public double getExtraBonusInDouble(){
        return extraBonus.orElse(0d);
    }
    public void setExtraBonus(Optional<Double> extraBonus)throws Exception{
        if(extraBonus.get()>=0.05*this.salary)
            this.extraBonus = extraBonus;
        else
            throw new Exception("Extra Bonus cannot be lower than 5% of salary.");
    }
    public double getAnnualIncome(){
        return getSalary()+getBonus()+getExtraBonus().orElse(0d);
    }
}
