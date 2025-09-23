import domain.enums.EntityType;
import service.ClockTaskService;
import shared.id.AtomicIdGenerator;
import repository.EmployeeRepository;
import shared.id.IdGenerator;
import repository.OrganizationRepository;
import service.EmployeeService;
import service.OrganizationService;
import thread.ClockTask;
import ui.OrganizationManagementMenu;

import java.util.EnumMap;
import java.util.Scanner;


public class OrganizationManagementMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IdGenerator idGenerator = new AtomicIdGenerator(new EnumMap<>(EntityType.class));

    private static final OrganizationRepository organizationRepository = new OrganizationRepository();
    private static final EmployeeRepository employeeRepository = new EmployeeRepository();

    private static final OrganizationService organizationService = new OrganizationService(organizationRepository, employeeRepository, idGenerator);
    private static final EmployeeService employeeService = new EmployeeService(organizationRepository, employeeRepository, idGenerator);

    public static void main(String[] args) {

        showTime();

        while (true) {

            ClockTask.pauseAndClearLine();
            OrganizationManagementMenu.showMenu();

            ClockTask.resume();
            String option = scanner.nextLine().trim();

            ClockTask.pauseAndClearLine();

            switch (option) {
                case "1" -> createDepartment();
                case "2" -> createTeam();
                case "3" -> hireEmployee();
                case "4" -> assignEmployee();
                case "5" -> getOrganizationChart();
                case "6" -> { System.out.println("프로그램을 종료합니다."); return; }
                default -> System.out.println("잘못된 입력입니다. 유효한 숫자 값을 입력해주세요.");
            }
        }
    }

    private static void showTime() {
        ClockTaskService clockTaskService = new ClockTaskService();
        clockTaskService.startClockTask();
    }

    private static void createDepartment() {
        organizationService.createDepartment(scanner);
    }

    private static void createTeam() {
        organizationService.createTeam(scanner);
    }

    private static void hireEmployee() {
        employeeService.hireEmployee(scanner);
    }

    private static void assignEmployee() {
        employeeService.assignEmployee(scanner);
    }

    private static void getOrganizationChart() {
        organizationService.getOrganizationChart();
    }

}
