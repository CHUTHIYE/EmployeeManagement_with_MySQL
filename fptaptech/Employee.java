package vn.fptaptech;
//employee entity aka employee table
public class Employee {
    private String ID;
    private String name;
    private String salary;

    public Employee() {
    }

    public Employee(String ID, String name, String salary) {
        this.ID = ID;
        this.name = name;
        this.salary = salary;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }



}
