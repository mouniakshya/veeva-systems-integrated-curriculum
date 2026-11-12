
import java.util.Scanner;

public class InventoryManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Inventory inventory = new Inventory();

        int choice;

        do {
            System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Check Quantity");
            System.out.println("4. Display All Items");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter item name: ");
                    String addName = sc.nextLine();

                    System.out.print("Enter quantity: ");
                    int addQuantity = sc.nextInt();

                    inventory.addItem(addName, addQuantity);
                    break;

                case 2:
                    System.out.print("Enter item name: ");
                    String removeName = sc.nextLine();

                    System.out.print("Enter quantity to remove: ");
                    int removeQuantity = sc.nextInt();

                    inventory.removeItem(removeName, removeQuantity);
                    break;

                case 3:
                    System.out.print("Enter item name: ");
                    String checkName = sc.nextLine();

                    inventory.checkQuantity(checkName);
                    break;

                case 4:
                    inventory.displayItems();
                    break;

                case 5:
                    System.out.println("Exiting Inventory Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
