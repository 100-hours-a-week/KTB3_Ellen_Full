package repository;

import domain.Department;
import domain.Team;

import java.util.HashMap;
import java.util.Map;

public class OrganizationRepository {

    private final Map<Integer, Department> departments = new HashMap<>();
    private final Map<Integer, Team> teams = new HashMap<>();

    public Map<Integer, Department> getDepartments() {
        return departments;
    }

    public Map<Integer, Team> getTeams() {
        return teams;
    }
}
