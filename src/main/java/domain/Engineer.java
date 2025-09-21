package domain;

import domain.enums.EmployeeType;

public class Engineer extends Employee{

    public Engineer(int id, String name, int salary, EmployeeType employeeType) {
        super(id, name, salary, employeeType);
    }
}
