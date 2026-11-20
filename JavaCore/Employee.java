
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
        return id + " " + name + " " + salary;
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        TreeSet<Employee> idAsc = new TreeSet<>(
            (a, b) -> a.id - b.id);

        TreeSet<Employee> nameAsc = new TreeSet<>(
            (a, b) -> a.name.compareTo(b.name));

        TreeSet<Employee> salaryAsc = new TreeSet<>(
            (a, b) -> Double.compare(a.salary, b.salary));

        TreeSet<Employee> salaryDesc = new TreeSet<>(
            (a, b) -> Double.compare(b.salary, a.salary));

        for (int i = 0; i < n; i++) {
            System.out.println("Enter id, name, salary:");
            int id = sc.nextInt();
            String name = sc.next();
            double salary = sc.nextDouble();

            Employee e = new Employee(id, name, salary);

            idAsc.add(e);
            nameAsc.add(e);
            salaryAsc.add(e);
            salaryDesc.add(e);
        }

        System.out.println("\nID Ascending:");
        System.out.println(idAsc);

        System.out.println("\nName Ascending:");
        System.out.println(nameAsc);

        System.out.println("\nSalary Ascending:");
        System.out.println(salaryAsc);

        System.out.println("\nSalary Descending:");
        System.out.println(salaryDesc);
    }
}
