
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {

    ArrayList<Product> products = new ArrayList<>();

    // Add product
    public void addProduct(Product p)
            throws InvalidProductException {

        if (p.getQuantity() < 100) {
            throw new InvalidProductException(
                    "Quantity is less than 100. Product cannot be stored!"
            );
        }

        products.add(p);
        System.out.println("Product added successfully!");
    }

    // Display all products
    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.printf(
                "%-5s %-15s %-10s %-15s %-12s %-15s%n",
                "ID", "Name", "Price", "Date",
                "Quantity", "Company"
        );

        for (Product p : products) {
            p.display();
        }
    }

    // 1. Sort by price in descending order
    public void sortByPrice() {

        products.sort(
                Comparator.comparingDouble(Product::getPrice).reversed()
        );

        System.out.println("Products sorted by price (Descending):");
        displayProducts();
    }

    // 2. Sort products within a price range in descending order
    public void sortByPriceRange(double min, double max) {

        List<Product> result = products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .sorted(
                        Comparator.comparingDouble(Product::getPrice).reversed()
                )
                .collect(Collectors.toList());

        System.out.println("Products in the given price range:");

        displayList(result);
    }

    // 3. Sort by price and manufacturing date in descending order
    public void sortByPriceAndDate() {

        products.sort(
                Comparator.comparingDouble(Product::getPrice)
                        .reversed()
                        .thenComparing(
                                Product::getManufacturingDate,
                                Comparator.reverseOrder()
                        )
        );

        System.out.println("Sorted by Price and Manufacturing Date:");
        displayProducts();
    }

    // 4. Search based on product name
    public void searchByName(String name) {

        List<Product> result = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        displayList(result);
    }

    // 5. Search based on manufacturing date
    public void searchByDate(String date) {

        List<Product> result = products.stream()
                .filter(p -> p.getManufacturingDate().toString().equals(date))
                .collect(Collectors.toList());

        displayList(result);
    }

    // 6. Search based on price
    public void searchByPrice(double price) {

        List<Product> result = products.stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());

        displayList(result);
    }

    // 7. Search based on product name, company and price
    public void searchByDetails(
            String name, String company, double price) {

        List<Product> result = products.stream()
                .filter(p ->
                        p.getName().equalsIgnoreCase(name) &&
                                p.getCompany().equalsIgnoreCase(company) &&
                                p.getPrice() == price
                )
                .collect(Collectors.toList());

        displayList(result);
    }

    // 8. Top K products using PriorityQueue
    public void topKProducts(int k) {

        if (k <= 0) {
            System.out.println("Enter a positive K value.");
            return;
        }

        PriorityQueue<Product> pq = new PriorityQueue<>(
                Comparator.comparingDouble(Product::getPrice)
        );

        for (Product p : products) {
            pq.add(p);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<Product> result = new ArrayList<>(pq);

        result.sort(
                Comparator.comparingDouble(Product::getPrice).reversed()
        );

        System.out.println("Top " + k + " products:");
        displayList(result);
    }

    // 9. Least K products using PriorityQueue
    public void leastKProducts(int k) {

        if (k <= 0) {
            System.out.println("Enter a positive K value.");
            return;
        }

        PriorityQueue<Product> pq = new PriorityQueue<>(
                Comparator.comparingDouble(Product::getPrice).reversed()
        );

        for (Product p : products) {
            pq.add(p);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<Product> result = new ArrayList<>(pq);

        result.sort(
                Comparator.comparingDouble(Product::getPrice)
        );

        System.out.println("Least " + k + " products:");
        displayList(result);
    }

    // Display search and query results
    private void displayList(List<Product> list) {

        if (list.isEmpty()) {
            System.out.println("No matching products found.");
            return;
        }

        System.out.printf(
                "%-5s %-15s %-10s %-15s %-12s %-15s%n",
                "ID", "Name", "Price", "Date",
                "Quantity", "Company"
        );

        for (Product p : list) {
            p.display();
        }
    }
}
