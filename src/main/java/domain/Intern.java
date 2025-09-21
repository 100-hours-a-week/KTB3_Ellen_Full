package domain;

import domain.enums.EmployeeType;

public class Intern extends Employee{

    public Intern(int id, String name, int salary, EmployeeType employeeType) {
        super(id, name, salary, employeeType);
    }
}
