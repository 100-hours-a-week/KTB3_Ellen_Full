package domain;

public class Team extends OrganizationUnit{

    private final int parentDepartmentId;

    public Team(int id, String name, int parentDepartmentId) {
        super(id, name);
        this.parentDepartmentId = parentDepartmentId;
    }

    public int getParentDepartmentId() {
        return parentDepartmentId;
    }
}
