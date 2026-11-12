
import java.util.HashMap;

public class Inventory {

    HashMap<String, Integer> items = new HashMap<>();

    // Add items to inventory
    public void addItem(String name, int quantity) {

        if (quantity <= 0) {
            System.out.println("Enter a valid quantity.");
            return;
        }

        int currentQuantity = items.getOrDefault(name, 0);
        items.put(name, currentQuantity + quantity);

        System.out.println(quantity + " " + name + " added successfully.");
    }

    // Remove items from inventory
    public void removeItem(String name, int quantity) {

        if (quantity <= 0) {
            System.out.println("Enter a valid quantity.");
            return;
        }

        if (!items.containsKey(name)) {
            System.out.println("Item not found.");
            return;
        }

        int currentQuantity = items.get(name);

        if (quantity > currentQuantity) {
            System.out.println("Insufficient stock.");
        } else {
            int newQuantity = currentQuantity - quantity;

            if (newQuantity == 0) {
                items.remove(name);
            } else {
                items.put(name, newQuantity);
            }

            System.out.println(quantity + " " + name + " removed successfully.");
        }
    }

    // Check item quantity
    public void checkQuantity(String name) {

        if (items.containsKey(name)) {
            System.out.println("Available quantity of " + name
                    + " : " + items.get(name));
        } else {
            System.out.println("Item not found.");
        }
    }

    // Display all items
    public void displayItems() {

        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\nInventory Items:");

        for (String name : items.keySet()) {
            System.out.println(name + " : " + items.get(name));
        }
    }
}
