package domain;

public class Employee extends Person{

    protected int salary;
    protected Integer teamId;

    public Employee(int id, String name, int salary) {
        super(id, name);
        this.salary = salary;
    }

    public void assignTeam(int teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamId() {
        return teamId;
    }

}
