
import java.util.*;
import java.util.stream.*;
abstract class Employee {
    private int id;
    private String name;
    private String department;
    Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    abstract double calculateSalary();
}
class FullTimeEmployee extends Employee {
    double baseSalary, bonus;
    FullTimeEmployee(int id, String name, String department,
                     double baseSalary, double bonus) {
        super(id, name, department);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
    }
    double calculateSalary() {
        return baseSalary + bonus;
    }
}
class ContractEmployee extends Employee {
    double hourlyRate;
    int hoursWorked;
    ContractEmployee(int id, String name, String department,
                     double hourlyRate, int hoursWorked) {
        super(id, name, department);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}
class PayrollManager {
    List<Employee> employees = new ArrayList<>();

    void addEmployee(Employee e) {
        employees.add(e);
    }
    Map<String, Double> getDepartmentWiseTotalSalary() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        LinkedHashMap::new,
                        Collectors.summingDouble(Employee::calculateSalary)
                ));
    }
    List<String> getTopEarners(double threshold) {
        return employees.stream()
                .filter(e -> e.calculateSalary() > threshold)
                .map(Employee::getName)
                .sorted()
                .collect(Collectors.toList());
    }
}
public class payroll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayrollManager pm = new PayrollManager();
        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("\n1. Full Time Employee");
            System.out.println("2. Contract Employee");
            System.out.print("Enter employee type: ");
            int type = sc.nextInt();
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Department: ");
            String dept = sc.next();
            if (type == 1) {
                System.out.print("Enter Base Salary: ");
                double base = sc.nextDouble();

                System.out.print("Enter Bonus: ");
                double bonus = sc.nextDouble();

                pm.addEmployee(new FullTimeEmployee(
                        id, name, dept, base, bonus));
            } else if (type == 2) {
                System.out.print("Enter Hourly Rate: ");
                double rate = sc.nextDouble();

                System.out.print("Enter Hours Worked: ");
                int hours = sc.nextInt();

                pm.addEmployee(new ContractEmployee(
                        id, name, dept, rate, hours));
            }
        }
        System.out.println("\nDepartment-wise Total Salary:");
        System.out.println(pm.getDepartmentWiseTotalSalary());
        System.out.print("\nEnter Salary Threshold: ");
        double threshold = sc.nextDouble();
        System.out.println("Top Earners (> " + threshold + "):");
        System.out.println(pm.getTopEarners(threshold));
        sc.close();
    }
}
