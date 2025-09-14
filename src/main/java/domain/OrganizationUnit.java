package domain;

import java.util.HashSet;
import java.util.Set;

abstract class OrganizationUnit {
    protected final int id;
    protected final String name;

    protected OrganizationUnit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    // 모든 조직은 구성원(Employee ID) 목록을 가질 수 있다
    protected final Set<Integer> memberIds = new HashSet<>();
    public void addMember(int empId) { memberIds.add(empId); }
    public void removeMember(int empId) { memberIds.remove(empId); }
    public Set<Integer> getMembers() { return memberIds; }
}
