
import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name +
               ", Salary: " + salary;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TreeMap<Integer, Employee> map = new TreeMap<>();

        int ch;

        do {
            System.out.println("\n1. Insert Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Display First Employee");
            System.out.println("5. Display Last Employee");
            System.out.println("6. Total Employees");
            System.out.println("7. Display All Employees");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter ID, Name, Salary: ");
                    int id = sc.nextInt();
                    String name = sc.next();
                    double salary = sc.nextDouble();

                    map.put(id, new Employee(id, name, salary));
                    System.out.println("Employee Inserted");
                    break;

                case 2:
                    System.out.print("Enter Employee ID: ");
                    id = sc.nextInt();

                    if (map.containsKey(id)) {
                        System.out.print("Enter New Name and Salary: ");
                        name = sc.next();
                        salary = sc.nextDouble();

                        map.put(id, new Employee(id, name, salary));
                        System.out.println("Employee Updated");
                    } else {
                        System.out.println("Employee Not Found");
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID: ");
                    id = sc.nextInt();

                    if (map.remove(id) != null)
                        System.out.println("Employee Deleted");
                    else
                        System.out.println("Employee Not Found");
                    break;

                case 4:
                    if (!map.isEmpty())
                        System.out.println(map.firstEntry().getValue());
                    else
                        System.out.println("No Employees");
                    break;

                case 5:
                    if (!map.isEmpty())
                        System.out.println(map.lastEntry().getValue());
                    else
                        System.out.println("No Employees");
                    break;

                case 6:
                    System.out.println("Total Employees: " + map.size());
                    break;

                case 7:
                    System.out.println(map.values());
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

            System.out.println("Total Employees: " + map.size());

        } while (ch != 0);

        sc.close();
    }
}
