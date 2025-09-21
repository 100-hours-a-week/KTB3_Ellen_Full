package domain;

import domain.enums.EmployeeType;

public class Employee extends Person{

    protected int salary;
    protected Integer teamId;
    protected EmployeeType employeeType;

    public Employee(int id, String name, int salary, EmployeeType employeeType) {
        super(id, name);
        this.salary = salary;
        this.employeeType = employeeType;
    }

    public void assignTeam(int teamId) {
        this.teamId = teamId;
    }
}
