
import java.util.*;

class Patient {
    int id, severity, time;
    String name;

    Patient(int id, String name, int severity, int time) {
        this.id = id;
        this.name = name;
        this.severity = severity;
        this.time = time;
    }

    public String toString() {
        return id + " " + name + " Severity: " + severity +
               " Arrival Time: " + time;
    }
}

class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PriorityQueue<Patient> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.severity != b.severity)
                    return b.severity - a.severity;
                return a.time - b.time;
            }
        );

        System.out.print("Enter number of patients: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter patient ID, name, severity, arrival time:");

            int id = sc.nextInt();
            String name = sc.next();
            int severity = sc.nextInt();
            int time = sc.nextInt();

            pq.add(new Patient(id, name, severity, time));
        }

        System.out.println("\nPatients based on priority:");

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        sc.close();
    }
}
