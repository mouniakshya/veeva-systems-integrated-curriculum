
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        VendingMachine vm = new VendingMachine();

        int choice;

        do {
            System.out.println("\n===== VENDING MACHINE =====");
            System.out.println("1. Display Products");
            System.out.println("2. Buy Product");
            System.out.println("3. Cancel Transaction");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    vm.displayProducts();
                    break;

                case 2:
                    sc.nextLine();

                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();

                    System.out.println("1. Cash");
                    System.out.println("2. UPI");
                    System.out.print("Choose payment method: ");
                    int paymentChoice = sc.nextInt();

                    String payment;
                    int money = 0;

                    if (paymentChoice == 1) {
                        payment = "CASH";

                        System.out.print("Enter money amount: Rs.");
                        money = sc.nextInt();
                    } else {
                        payment = "UPI";
                        System.out.println("UPI payment successful!");
                    }

                    System.out.println("1. Confirm");
                    System.out.println("2. Cancel");
                    System.out.print("Enter choice: ");
                    int confirm = sc.nextInt();

                    if (confirm == 2) {
                        vm.cancelTransaction();
                        break;
                    }

                    try {
                        vm.buyProduct(name, quantity, money, payment);
                    }
                    catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (InsufficientChangeException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (SoldOutException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (InsufficientQuantityException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    vm.cancelTransaction();
                    break;

                case 4:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
