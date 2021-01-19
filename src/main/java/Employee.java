import com.fasterxml.jackson.annotation.JsonInclude;



@JsonInclude(JsonInclude.Include.NON_DEFAULT) //We do not want a field with a default value to come to the target payload. For this, we can use an annotation @JsonInclude in POJO class at the class level and individual property level provided by Jackson Java API.

public class Employee {

    // private variables or data members of pojo class
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private double salary;
    private boolean married;

    // Getter and setter methods
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public boolean getMarried() {
        return married;
    }
    public void setMarried(boolean married) {
        this.married = married;
    }
}