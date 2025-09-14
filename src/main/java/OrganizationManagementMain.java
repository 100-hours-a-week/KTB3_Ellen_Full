import domain.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrganizationManagementMain {

    private static final Scanner sc = new Scanner(System.in);
    private static final Map<Integer, Department> departments = new HashMap<>();
    private static final Map<Integer, Team> teams = new HashMap<>();
    private static final Map<Integer, Employee> employees = new HashMap<>();
    private static int nextId = 1;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== 조직 관리 시스템 ===");
            System.out.println("1) 부서 생성");
            System.out.println("2) 팀 생성");
            System.out.println("3) 직원 추가");
            System.out.println("4) 직원 배정");
            System.out.println("5) 조직도 출력");
            System.out.println("6) 종료");
            System.out.println("====================\n");
            System.out.println("원하는 옵션을 선택하세요.\n");
            System.out.print("> ");
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "1" -> createDepartment();
                case "2" -> createTeam();
                case "3" -> hireEmployee();
                case "4" -> assignEmployee();
                case "5" -> getOrganizationChart();
                case "6" -> { System.out.println("종료합니다."); return; }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 부서 생성 로직
    private static void createDepartment() {
        System.out.println("부서 이름: ");
        String name = sc.nextLine();

        Department department = new Department(nextId++, name);
        departments.put(department.getId(), department);
        System.out.println("새로운 부서가 생성되었습니다 : ID = " + department.getId());
    }

    // 팀 생성
    private static void createTeam() {
        System.out.println("팀 이름을 입력하세요: ");
        String name = sc.nextLine();
        System.out.println("소속 부서 ID를 입력하세요: ");
        int departmentId = Integer.parseInt(sc.nextLine());

        Department department = departments.get(departmentId);
        if (department == null) {
            System.out.println("해당 부서가 존재하지 않습니다.");
            return;
        }

        Team team = new Team(nextId++, name, departmentId);
        teams.put(team.getId(), team);
        department.addTeam(team);
        System.out.println("팀이 성공적으로 생성되었습니다 : ID = " + team.getId());
    }

    // 직원 추가
    private static void hireEmployee() {
        System.out.println("직원 이름을 입력하세요 : ");
        String name = sc.nextLine();
        System.out.println("직급을 선택하세요 (1: Manager, 2: Engineer, 3: Intern) : ");
        int option = Integer.parseInt(sc.nextLine());
        System.out.println("연봉을 입력하세요 : ");
        int salary = Integer.parseInt(sc.nextLine());

        Employee employee = switch(option) {
            case 1 -> new Manager(nextId, name, salary);
            case 2 -> new Engineer(nextId, name, salary);
            case 3 -> new Intern(nextId, name,salary);
            default -> null;
        };

        if (employee == null) {
            System.out.println("유효한 값을 입력해주세요");
            return;
        }

        employees.put(nextId, employee);
        System.out.println("직원이 성공적으로 추가되었습니다 : ID = " + nextId);
        nextId++;
    }

    // 직원 배정 (팀)
    private static void assignEmployee() {
        System.out.println("직원 ID를 입력해주세요 : ");
        int employeeId = Integer.parseInt(sc.nextLine());

        Employee employee = employees.get(employeeId);
        if (employee == null) {
            System.out.println("해당 직원이 존재하지 않습니다.");
            return;
        }

        System.out.println("배정할 조직(팀)의 ID를 입력하세요 : ");
        int organizationId = Integer.parseInt(sc.nextLine());

        if (teams.containsKey(organizationId)) {
            Team team = teams.get(organizationId);
            team.addMember(employeeId);
            employee.assignOrganization(employeeId);
            System.out.println("직원 " + "'" + employee.getName() + "'" + "님이 팀 " + "'" + team.getName() + "'" + "에 성공적으로 배정되었습니다.");
        } else {
            System.out.println("해당 조직 ID가 존재하지 않습니다.");
        }

    }

    // 전체 조직 차트 출력
    private static void getOrganizationChart() {
        System.out.println("\n--- 조직도 ---");
        // 부서 출력
        for (Department department : departments.values()) {
            System.out.println("[부서]" + " " + department.getName() + " (ID = " + department.getId() + ")");
            // 팀 출력
            for (Team team : teams.values()) {
                System.out.println("  [팀]" + " " + team.getName() + " (ID = " + team.getId() + ")");
                // 팀 구성원 (직원) 출력
                for (int employeeId : team.getMembers()) {
                    Employee employee = employees.get(employeeId);
                    System.out.println("   - " + employee.getClass().getSimpleName()
                            + " " + employee.getName() + " (ID = " + employeeId + ")");
                }
            }
        }
    }


}
