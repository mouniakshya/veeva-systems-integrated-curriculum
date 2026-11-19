
import java.util.*;

public class VendingMachine {

    HashMap<String, Integer> price = new HashMap<>();
    HashMap<String, Integer> stock = new HashMap<>();
    ArrayList<Integer> denominations = new ArrayList<>();

    int machineMoney = 500;

    VendingMachine() {

        price.put("Soda", 30);
        price.put("Chocolate", 20);
        price.put("Candy", 10);
        price.put("Chips", 25);

        stock.put("Soda", 5);
        stock.put("Chocolate", 5);
        stock.put("Candy", 10);
        stock.put("Chips", 5);

        denominations.add(100);
        denominations.add(50);
        denominations.add(20);
        denominations.add(10);
        denominations.add(5);
    }

    void displayProducts() {

        System.out.println("\n--- Products ---");

        for (String name : price.keySet()) {
            System.out.println(name + " - Rs." + price.get(name)
                    + " - Stock: " + stock.get(name));
        }
    }

    void buyProduct(String name, int quantity, int money, String payment)
            throws InsufficientFundsException,
            InsufficientChangeException,
            SoldOutException,
            InsufficientQuantityException {

        if (!price.containsKey(name)) {
            System.out.println("Invalid product!");
            return;
        }

        if (stock.get(name) == 0) {
            throw new SoldOutException("Product is sold out!");
        }

        if (quantity > stock.get(name)) {
            throw new InsufficientQuantityException(
                    "Insufficient quantity available!");
        }

        int total = price.get(name) * quantity;

        if (payment.equals("UPI")) {
            money = total;
        }

        if (money < total) {
            throw new InsufficientFundsException(
                    "No sufficient funds for transaction!");
        }

        int change = money - total;

        if (change > machineMoney) {
            throw new InsufficientChangeException(
                    "Insufficient change available!");
        }

        machineMoney += money;

        stock.put(name, stock.get(name) - quantity);

        System.out.println("\nTransaction Successful!");
        System.out.println("Product: " + name);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: Rs." + total);
        System.out.println("Change Returned: Rs." + change);
        System.out.println("Product Dispensed!");
    }

    void cancelTransaction() {
        System.out.println("Transaction cancelled.");
        System.out.println("Money returned to customer.");
    }
}
