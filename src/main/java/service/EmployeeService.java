package service;

import domain.*;
import domain.enums.EmployeeType;
import domain.enums.EntityType;
import repository.EmployeeRepository;
import shared.id.IdGenerator;
import repository.OrganizationRepository;

import java.util.Scanner;

public class EmployeeService {

    private final IdGenerator idGenerator;
    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;

    public EmployeeService(OrganizationRepository organizationRepository, EmployeeRepository employeeRepository, IdGenerator idGenerator) {
        this.organizationRepository = organizationRepository;
        this.employeeRepository = employeeRepository;
        this.idGenerator = idGenerator;
    }

    // 직원 추가
    public void hireEmployee(Scanner scanner) {
        System.out.println("직원 이름을 입력하세요 : ");
        String name = scanner.nextLine();
        System.out.println("직급을 선택하세요 (1: Manager, 2: Engineer, 3: Intern) : ");
        int option;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("유효한 숫자 값을 입력해주세요.");
            return;
        }

        EmployeeType employeeType;

        try {
            employeeType = EmployeeType.fromCode(option);
        } catch (IllegalArgumentException e) {
            System.out.println("유효한 값을 입력해주세요.");
            return;
        }

        System.out.println("연봉을 입력하세요 : ");
        int salary = Integer.parseInt(scanner.nextLine());

        int id = idGenerator.next(EntityType.EMPLOYEE);

        Employee employee = switch(employeeType) {
            case MANAGER  -> new Manager(id, name, salary, employeeType);
            case ENGINEER -> new Engineer(id, name, salary, employeeType);
            case INTERN   -> new Intern(id, name, salary, employeeType);
        };

        employeeRepository.getEmployees().put(id, employee);
        System.out.println("직원이 성공적으로 추가되었습니다 : ID = " + id);
    }

    // 직원 배정 (팀)
    public void assignEmployee(Scanner scanner) {
        System.out.println("직원 ID를 입력해주세요 : ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        Employee employee = employeeRepository.getEmployees().get(employeeId);
        if (employee == null) {
            System.out.println("해당 직원이 존재하지 않습니다.");
            return;
        }

        System.out.println("배정할 조직(팀)의 ID를 입력하세요 : ");
        int teamId = Integer.parseInt(scanner.nextLine());

        if (organizationRepository.getTeams().containsKey(teamId)) {
            Team team = organizationRepository.getTeams().get(teamId);
            team.addMember(employeeId);
            employee.assignTeam(teamId);
            System.out.println("직원 " + "'" + employee.getName() + "'" + "님이 팀 " + "'" + team.getName() + "'" + "에 성공적으로 배정되었습니다.");
        } else {
            System.out.println("해당 조직 ID가 존재하지 않습니다.");
        }

    }
}
