package domain;

import domain.enums.EmployeeType;

public class Manager extends Employee{

    public Manager(int id, String name, int salary, EmployeeType employeeType) {
        super(id, name, salary, employeeType);
    }
}
