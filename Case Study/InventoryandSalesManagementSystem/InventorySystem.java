
import java.math.BigDecimal;
import java.util.*;

public class InventorySystem {

    private Map<String, Company> companies = new LinkedHashMap<>();

    private Map<String, Product> products = new LinkedHashMap<>();

    private Map<String, Integer> inventory = new LinkedHashMap<>();

    private List<Transaction> transactions = new ArrayList<>();

    private int transactionCounter = 1;


    // ================= COMPANY OPERATIONS =================

    public void addCompany(Company company) {

        if (companies.containsKey(company.getCompanyId())) {
            throw new IllegalArgumentException("Company ID already exists");
        }

        if (company.getCompanyName().isBlank()) {
            throw new IllegalArgumentException("Company name cannot be empty");
        }

        companies.put(company.getCompanyId(), company);

        System.out.println("Company added successfully!");
    }


    public void updateCompany(String id, String name,
                              String contact, String location) {

        Company company = companies.get(id);

        if (company == null) {
            throw new IllegalArgumentException("Company not found");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Company name cannot be empty");
        }

        company.updateDetails(name, contact, location);

        System.out.println("Company updated successfully!");
    }


    public void viewCompanies() {

        if (companies.isEmpty()) {
            System.out.println("No companies available");
            return;
        }

        System.out.println("\n===== COMPANY DIRECTORY =====");

        for (Company company : companies.values()) {
            System.out.println(company);
            System.out.println("-----------------------");
        }
    }


    public void deleteCompany(String id) {

        if (!companies.containsKey(id)) {
            throw new IllegalArgumentException("Company not found");
        }

        for (Product p : products.values()) {

            if (p.getCompanyId().equals(id)) {
                throw new IllegalArgumentException(
                        "Cannot delete company. Products are associated with it"
                );
            }
        }

        companies.remove(id);

        System.out.println("Company deleted successfully!");
    }


    // ================= PRODUCT OPERATIONS =================

    public void addProduct(Product product, int initialStock) {

        if (products.containsKey(product.getProductId())) {
            throw new IllegalArgumentException("Product ID already exists");
        }

        if (product.getProductName().isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (findProduct(product.getProductName()) != null) {
            throw new IllegalArgumentException("Product name already exists");
        }

        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (initialStock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        if (!companies.containsKey(product.getCompanyId())) {
            throw new IllegalArgumentException("Invalid Company ID");
        }

        products.put(product.getProductId(), product);

        inventory.put(product.getProductId(), initialStock);

        System.out.println("Product added successfully!");
    }


    public void updateProduct(String id, String name,
                              BigDecimal price, String category,
                              String companyId) {

        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        Product existing = findProduct(name);

        if (existing != null &&
                !existing.getProductId().equals(id)) {
            throw new IllegalArgumentException("Product name already exists");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid price");
        }

        if (!companies.containsKey(companyId)) {
            throw new IllegalArgumentException("Company not found");
        }

        product.updateDetails(name, price, category, companyId);

        System.out.println("Product updated successfully!");
    }


    public void viewProducts() {

        if (products.isEmpty()) {
            System.out.println("No products available");
            return;
        }

        System.out.println("\n===== PRODUCT CATALOG =====");

        for (Product p : products.values()) {

            System.out.println(p);

            System.out.println("Available Stock: " +
                    inventory.get(p.getProductId()));

            System.out.println("-----------------------");
        }
    }


    public void deleteProduct(String id) {

        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product not found");
        }

        products.remove(id);
        inventory.remove(id);

        System.out.println("Product deleted successfully!");
    }


    // ================= PRODUCT SEARCH =================

    private Product findProduct(String key) {

        Product product = products.get(key);

        if (product != null) {
            return product;
        }

        for (Product p : products.values()) {

            if (p.getProductName().equalsIgnoreCase(key)) {
                return p;
            }
        }

        return null;
    }


    public void priceLookup(String key) {

        Product p = findProduct(key);

        if (p == null) {
            throw new IllegalArgumentException("Product not found");
        }

        Company company = companies.get(p.getCompanyId());

        System.out.println("\n===== PRODUCT DETAILS =====");

        System.out.println("Product Name: " + p.getProductName());
        System.out.println("Product ID: " + p.getProductId());
        System.out.println("Price: $" + p.getPrice());
        System.out.println("Category: " + p.getCategory());

        System.out.println("Supplier: " + company.getCompanyName());
        System.out.println("Contact: " + company.getContact());
        System.out.println("Location: " + company.getLocation());

        System.out.println("Available Stock: " +
                inventory.get(p.getProductId()));
    }


    // ================= INVENTORY OPERATIONS =================

    public void viewStock() {

        System.out.println("\n===== INVENTORY TRACKER =====");

        for (Product p : products.values()) {

            int stock = inventory.get(p.getProductId());

            System.out.println(
                    p.getProductName() + " : " + stock + " units"
            );

            if (stock == 0) {
                System.out.println("ALERT: OUT OF STOCK!");
            } else if (stock <= 5) {
                System.out.println("WARNING: LOW STOCK!");
            }
        }
    }


    public void restock(String id, int quantity) {

        if (!inventory.containsKey(id)) {
            throw new IllegalArgumentException("Product not found");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Restock quantity must be positive"
            );
        }

        int currentStock = inventory.get(id);

        inventory.put(id, Math.addExact(currentStock, quantity));

        System.out.println("Stock updated successfully!");

        System.out.println("Available Stock: " + inventory.get(id));
    }


    // ================= SALES OPERATIONS =================

    public void sellProducts(Map<String, Integer> order) {

        if (order.isEmpty()) {
            throw new IllegalArgumentException("Order cannot be empty");
        }

        // Resolve all products and combine duplicate references.
        Map<String, Integer> validatedOrder = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : order.entrySet()) {

            Product p = findProduct(entry.getKey());

            if (p == null) {
                throw new IllegalArgumentException(
                        "Product not found: " + entry.getKey()
                );
            }

            int quantity = entry.getValue();

            if (quantity <= 0) {
                throw new IllegalArgumentException(
                        "Quantity must be greater than zero"
                );
            }

            try {
                validatedOrder.merge(
                        p.getProductId(),
                        quantity,
                        Math::addExact
                );
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("Quantity is too large");
            }
        }

        // Validate every item before deducting any stock.
        BigDecimal total = BigDecimal.ZERO;

        List<TransactionItem> items = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : validatedOrder.entrySet()) {

            Product p = products.get(entry.getKey());

            int requested = entry.getValue();

            int available = inventory.get(p.getProductId());

            if (available == 0) {
                throw new IllegalArgumentException(
                        p.getProductName() + " is currently out of stock"
                );
            }

            if (requested > available) {
                throw new IllegalArgumentException(
                        "Only " + available + " units of " +
                                p.getProductName() + " are available"
                );
            }

            BigDecimal subtotal = p.getPrice().multiply(
                    BigDecimal.valueOf(requested)
            );

            total = total.add(subtotal);

            items.add(new TransactionItem(
                    p.getProductId(),
                    p.getProductName(),
                    requested,
                    p.getPrice()
            ));
        }

        // Deduct stock only after ALL items pass validation.
        for (Map.Entry<String, Integer> entry : validatedOrder.entrySet()) {

            String id = entry.getKey();

            int remaining = inventory.get(id) - entry.getValue();

            inventory.put(id, remaining);
        }

        // Record successful transaction.
        String transactionId = "T" + transactionCounter++;

        Transaction transaction = new Transaction(
                transactionId, items, total
        );

        transactions.add(transaction);

        System.out.println("\n===== TRANSACTION SUCCESSFUL =====");

        transaction.displayTransaction();

        System.out.println("\nUpdated Inventory:");

        for (String id : validatedOrder.keySet()) {

            Product p = products.get(id);

            System.out.println(
                    p.getProductName() + " : " +
                            inventory.get(id) + " units"
            );
        }
    }


    // ================= TRANSACTION HISTORY =================

    public void viewTransactions() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
            System.out.println("-----------------------");
        }
    }
}
