package domain;

public class Employee extends Person{

    protected int salary;
    protected Integer orgUnitId;

    public Employee(int id, String name, int salary) {
        super(id, name);
        this.salary = salary;
    }

    public void assignOrganization(int orgUnitId) {
        this.orgUnitId = orgUnitId;
    }

}
