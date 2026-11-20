
import java.util.*;

class Order {
    int orderId, customerId;
    String category;
    double amount;

    Order(int o, int c, String cat, double a) {
        orderId = o;
        customerId = c;
        category = cat;
        amount = a;
    }
}

public class Main {
    public static void main(String[] args) {

        ArrayList<Order> orders = new ArrayList<>();

        orders.add(new Order(1, 101, "Electronics", 5000));
        orders.add(new Order(2, 102, "Clothing", 3000));
        orders.add(new Order(3, 101, "Furniture", 7000));
        orders.add(new Order(4, 103, "Electronics", -500));
        orders.add(new Order(5, 104, "Clothing", 8000));
        orders.add(new Order(6, 102, "Furniture", 6000));
        orders.add(new Order(7, 105, "Electronics", 0));

        // Find orders with amount <= 0
        System.out.println("Invalid Orders:");
        for (Order o : orders) {
            if (o.amount <= 0)
                System.out.println(o.orderId);
        }

        // Calculate total spending of each customer
        HashMap<Integer, Double> total = new HashMap<>();

        for (Order o : orders) {
            if (o.amount > 0)
                total.put(o.customerId,
                    total.getOrDefault(o.customerId, 0.0) + o.amount);
        }

        // Sort customers by spending in descending order
        TreeMap<Double, Integer> map =
            new TreeMap<>(Collections.reverseOrder());

        for (int id : total.keySet())
            map.put(total.get(id), id);

        // Display top 3 customers
        System.out.println("Top 3 Customers:");
        int count = 0;

        for (int id : map.values()) {
            System.out.println(id);
            if (++count == 3) break;
        }
    }
}
