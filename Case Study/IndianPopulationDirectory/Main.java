
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PopulationDirectory directory = new PopulationDirectory();

        // Sample data
        directory.addState("Andhra Pradesh");
        directory.addState("Telangana");
        directory.addState("Karnataka");

        directory.addCity("Andhra Pradesh", "Vijayawada", 1000000);
        directory.addCity("Andhra Pradesh", "Visakhapatnam", 2000000);
        directory.addCity("Andhra Pradesh", "Guntur", 650000);

        directory.addCity("Telangana", "Hyderabad", 6900000);
        directory.addCity("Telangana", "Warangal", 800000);
        directory.addCity("Telangana", "Nizamabad", 310000);

        directory.addCity("Karnataka", "Bengaluru", 8400000);
        directory.addCity("Karnataka", "Mysuru", 920000);
        directory.addCity("Karnataka", "Mangaluru", 500000);

        while (true) {

            System.out.println("\n================================");
            System.out.println(" INDIA POPULATION DIRECTORY");
            System.out.println("================================");

            System.out.println("1. Add State");
            System.out.println("2. Add City");
            System.out.println("3. Update City Population");
            System.out.println("4. City Lookup");
            System.out.println("5. State Population Details");
            System.out.println("6. Global City Search");
            System.out.println("7. Population Threshold Filter");
            System.out.println("8. Display All States and Cities");
            System.out.println("9. Exit");

            System.out.print("\nEnter your choice: ");

            try {

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:

                        System.out.print("Enter State Name: ");

                        String state = sc.nextLine();

                        directory.addState(state);

                        break;


                    case 2:

                        System.out.print("Enter State Name: ");

                        state = sc.nextLine();

                        System.out.print("Enter City Name: ");

                        String city = sc.nextLine();

                        System.out.print("Enter Population: ");

                        long population = Long.parseLong(sc.nextLine());

                        directory.addCity(state, city, population);

                        break;


                    case 3:

                        System.out.print("Enter State Name: ");

                        state = sc.nextLine();

                        System.out.print("Enter City Name: ");

                        city = sc.nextLine();

                        System.out.print("Enter New Population: ");

                        long newPopulation = Long.parseLong(sc.nextLine());

                        directory.updatePopulation(
                                state, city, newPopulation
                        );

                        break;


                    case 4:

                        System.out.print("Enter State Name: ");

                        state = sc.nextLine();

                        System.out.print("Enter City Name: ");

                        city = sc.nextLine();

                        directory.cityLookup(state, city);

                        break;


                    case 5:

                        System.out.print("Enter State Name: ");

                        state = sc.nextLine();

                        directory.stateAggregation(state);

                        break;


                    case 6:

                        System.out.print("Enter City Name: ");

                        city = sc.nextLine();

                        directory.globalSearch(city);

                        break;


                    case 7:

                        System.out.print("Enter Population Threshold: ");

                        long threshold = Long.parseLong(sc.nextLine());

                        if (threshold < 0) {
                            System.out.println("Threshold cannot be negative!");
                        } else {
                            directory.thresholdFiltering(threshold);
                        }

                        break;


                    case 8:

                        directory.displayAll();

                        break;


                    case 9:

                        System.out.println("Application closed!");

                        sc.close();

                        return;


                    default:

                        System.out.println("Invalid choice!");

                }

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid numeric value!");

            }
        }
    }
}
