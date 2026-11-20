
import java.util.*;

class Student {
    String name;
    int marks;
    String grade;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
        if (marks >= 90)
            grade = "A";
        else if (marks >= 75)
            grade = "B";
        else if (marks >= 50)
            grade = "C";
        else
            grade = "Fail";
    }

    public String toString() {
        return "Name: " + name + ", Marks: " + marks +
               ", Grade: " + grade;
    }
}

class StudentNotFoundException extends Exception {
    StudentNotFoundException() {
        super("Student Not Found");
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Student> map = new HashMap<>();

        int ch;

        do {
            System.out.println("\n1. Insert Student");
            System.out.println("2. Get Student by Name");
            System.out.println("3. Get Marks by ID");
            System.out.println("4. Update Marks");
            System.out.println("5. Remove Student");
            System.out.println("6. Check Student Exists");
            System.out.println("7. Display All Students");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            try {
                switch (ch) {

                    case 1:
                        System.out.print("Enter ID, Name, Marks: ");
                        int id = sc.nextInt();
                        String name = sc.next();
                        int marks = sc.nextInt();

                        map.put(id, new Student(name, marks));
                        System.out.println("Record Inserted/Updated");
                        break;

                    case 2:
                        System.out.print("Enter Name: ");
                        name = sc.next();
                        boolean found = false;

                        for (Student s : map.values()) {
                            if (s.name.equalsIgnoreCase(name)) {
                                System.out.println(s);
                                found = true;
                            }
                        }

                        if (!found)
                            throw new StudentNotFoundException();
                        break;

                    case 3:
                        System.out.print("Enter ID: ");
                        id = sc.nextInt();

                        if (!map.containsKey(id))
                            throw new StudentNotFoundException();

                        System.out.println("Marks: " + map.get(id).marks);
                        break;

                    case 4:
                        System.out.print("Enter ID and New Marks: ");
                        id = sc.nextInt();
                        marks = sc.nextInt();

                        if (map.containsKey(id)) {
                            Student s = map.get(id);
                            map.put(id, new Student(s.name, marks));
                        } else {
                            System.out.print("Enter Student Name: ");
                            name = sc.next();
                            map.put(id, new Student(name, marks));
                        }

                        System.out.println("Record Updated/Inserted");
                        break;

                    case 5:
                        System.out.print("Enter ID: ");
                        id = sc.nextInt();

                        if (map.remove(id) == null)
                            throw new StudentNotFoundException();

                        System.out.println("Student Removed");
                        break;

                    case 6:
                        System.out.print("Enter ID: ");
                        id = sc.nextInt();

                        System.out.println(map.containsKey(id));
                        break;

                    case 7:
                        for (int key : map.keySet())
                            System.out.println(key + " " + map.get(key));
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid Choice");
                }

                System.out.println("Total Students: " + map.size());

            } catch (StudentNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Total Students: " + map.size());
            }

        } while (ch != 0);

        sc.close();
    }
}
