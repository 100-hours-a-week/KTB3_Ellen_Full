package domain;

import java.util.ArrayList;
import java.util.List;

public class Department extends OrganizationUnit{

    private final List<Team> teams = new ArrayList<>();

    public Department(int id, String name) {
        super(id, name);
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public List<Team> getTeams() {
        return teams;
    }
}
