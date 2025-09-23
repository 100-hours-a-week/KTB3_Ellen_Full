package service;

import domain.Department;
import domain.Employee;
import domain.Team;
import domain.enums.EntityType;
import repository.EmployeeRepository;
import shared.id.IdGenerator;
import repository.OrganizationRepository;

import java.util.Scanner;

public class OrganizationService {

    private final IdGenerator idGenerator;
    private final OrganizationRepository organizationRepository;
    private final EmployeeRepository employeeRepository;

    public OrganizationService(OrganizationRepository organizationRepository, EmployeeRepository employeeRepository, IdGenerator idGenerator) {
        this.organizationRepository = organizationRepository;
        this.employeeRepository = employeeRepository;
        this.idGenerator = idGenerator;
    }

    // 부서 생성 로직
    public void createDepartment(Scanner scanner) {
        System.out.println("부서 이름: ");
        String name = scanner.nextLine();

        int id = idGenerator.next(EntityType.DEPARTMENT);
        Department department = new Department(id, name);

        organizationRepository.getDepartments().put(department.getId(), department);
        System.out.println("새로운 부서가 생성되었습니다 : ID = " + department.getId());
    }

    // 팀 생성
    public void createTeam(Scanner scanner) {
        System.out.println("팀 이름을 입력하세요: ");
        String name = scanner.nextLine();
        System.out.println("소속 부서 ID를 입력하세요: ");
        int departmentId = Integer.parseInt(scanner.nextLine());


        Department department = organizationRepository.getDepartments().get(departmentId);
        if (department == null) {
            System.out.println("해당 부서가 존재하지 않습니다.");
        }

        int id = idGenerator.next(EntityType.TEAM);
        Team team = new Team(id, name, departmentId);
        organizationRepository.getTeams().put(team.getId(), team);

        assert department != null;
        department.addTeam(team);

        System.out.println("팀이 성공적으로 생성되었습니다 : ID = " + team.getId());
    }

    // 전체 조직 차트 출력
    public void getOrganizationChart() {
        System.out.println("\n--- 조직도 ---");
        // 부서 출력
        for (Department department : organizationRepository.getDepartments().values()) {
            System.out.println("[부서]" + " " + department.getName() + " (ID = " + department.getId() + ")");
            // 팀 출력
            for (Team team : organizationRepository.getTeams().values()) {
                if (team.getParentDepartmentId() == department.getId()) {
                    System.out.println("  [팀]" + " " + team.getName() + " (ID = " + team.getId() + ")");
                    // 팀 구성원 (직원) 출력
                    for (int employeeId : team.getMembers()) {
                        Employee employee = employeeRepository.getEmployees().get(employeeId);
                        System.out.println("   - " + employee.getClass().getSimpleName()
                                + " " + employee.getName() + " (ID = " + employeeId + ")");
                    }
                }
            }
        }
    }

}
