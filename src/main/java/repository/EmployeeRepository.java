package repository;

import domain.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository {

    public final Map<Integer, Employee> employees = new HashMap<>();

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }
}
