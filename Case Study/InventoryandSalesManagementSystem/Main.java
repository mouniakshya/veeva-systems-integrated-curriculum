
import java.math.BigDecimal;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static InventorySystem system = new InventorySystem();

    public static void main(String[] args) {

        // Adding sample company
        system.addCompany(new Company(
                "C101",
                "Tech Solutions",
                "9876543210",
                "Hyderabad"
        ));

        // Adding sample products
        system.addProduct(
                new Product(
                        "P101",
                        "Wireless Mouse",
                        new BigDecimal("25.00"),
                        "Electronics",
                        "C101"
                ), 10
        );

        system.addProduct(
                new Product(
                        "P102",
                        "Mechanical Keyboard",
                        new BigDecimal("50.00"),
                        "Electronics",
                        "C101"
                ), 0
        );

        while (true) {

            System.out.println("\n==================================");
            System.out.println(" INVENTORY AND SALES MANAGEMENT");
            System.out.println("==================================");

            System.out.println("1. Add Company");
            System.out.println("2. Update Company");
            System.out.println("3. View Companies");
            System.out.println("4. Delete Company");

            System.out.println("5. Add Product");
            System.out.println("6. Update Product");
            System.out.println("7. View Products");
            System.out.println("8. Delete Product");

            System.out.println("9. Price Lookup");
            System.out.println("10. Sell Products");
            System.out.println("11. View Stock");
            System.out.println("12. Restock Product");
            System.out.println("13. Transaction History");

            System.out.println("14. Exit");

            System.out.print("\nEnter your choice: ");

            try {

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        addCompany();
                        break;

                    case 2:
                        updateCompany();
                        break;

                    case 3:
                        system.viewCompanies();
                        break;

                    case 4:
                        deleteCompany();
                        break;

                    case 5:
                        addProduct();
                        break;

                    case 6:
                        updateProduct();
                        break;

                    case 7:
                        system.viewProducts();
                        break;

                    case 8:
                        deleteProduct();
                        break;

                    case 9:
                        priceLookup();
                        break;

                    case 10:
                        sellProducts();
                        break;

                    case 11:
                        system.viewStock();
                        break;

                    case 12:
                        restockProduct();
                        break;

                    case 13:
                        system.viewTransactions();
                        break;

                    case 14:
                        System.out.println("Application closed!");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number!");

            } catch (IllegalArgumentException | ArithmeticException e) {

                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }


    // ADD COMPANY
    static void addCompany() {

        System.out.print("Company ID: ");
        String id = sc.nextLine();

        System.out.print("Company Name: ");
        String name = sc.nextLine();

        System.out.print("Contact: ");
        String contact = sc.nextLine();

        System.out.print("Location: ");
        String location = sc.nextLine();

        system.addCompany(
                new Company(id, name, contact, location)
        );
    }


    // UPDATE COMPANY
    static void updateCompany() {

        System.out.print("Company ID: ");
        String id = sc.nextLine();

        System.out.print("New Company Name: ");
        String name = sc.nextLine();

        System.out.print("New Contact: ");
        String contact = sc.nextLine();

        System.out.print("New Location: ");
        String location = sc.nextLine();

        system.updateCompany(id, name, contact, location);
    }


    // DELETE COMPANY
    static void deleteCompany() {

        System.out.print("Enter Company ID: ");
        String id = sc.nextLine();

        system.deleteCompany(id);
    }


    // ADD PRODUCT
    static void addProduct() {

        System.out.print("Product ID: ");
        String id = sc.nextLine();

        System.out.print("Product Name: ");
        String name = sc.nextLine();

        System.out.print("Price: ");
        BigDecimal price = new BigDecimal(sc.nextLine());

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Company ID: ");
        String companyId = sc.nextLine();

        System.out.print("Initial Stock: ");
        int stock = Integer.parseInt(sc.nextLine());

        system.addProduct(
                new Product(id, name, price, category, companyId),
                stock
        );
    }


    // UPDATE PRODUCT
    static void updateProduct() {

        System.out.print("Product ID: ");
        String id = sc.nextLine();

        System.out.print("New Product Name: ");
        String name = sc.nextLine();

        System.out.print("New Price: ");
        BigDecimal price = new BigDecimal(sc.nextLine());

        System.out.print("New Category: ");
        String category = sc.nextLine();

        System.out.print("New Company ID: ");
        String companyId = sc.nextLine();

        system.updateProduct(id, name, price, category, companyId);
    }


    // DELETE PRODUCT
    static void deleteProduct() {

        System.out.print("Product ID: ");
        String id = sc.nextLine();

        system.deleteProduct(id);
    }


    // PRICE LOOKUP
    static void priceLookup() {

        System.out.print("Enter Product ID or Name: ");

        String key = sc.nextLine();

        system.priceLookup(key);
    }


    // SELL PRODUCTS
    static void sellProducts() {

        Map<String, Integer> order = new LinkedHashMap<>();

        System.out.print("Number of different products: ");

        int count = Integer.parseInt(sc.nextLine());

        if (count <= 0) {
            throw new IllegalArgumentException(
                    "Number of products must be positive"
            );
        }

        for (int i = 1; i <= count; i++) {

            System.out.println("\nProduct " + i);

            System.out.print("Enter Product ID or Name: ");
            String key = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            order.merge(key, quantity, Math::addExact);
        }

        system.sellProducts(order);
    }





    // RESTOCK PRODUCT
    static void restockProduct() {

        System.out.print("Enter Product ID: ");
        String id = sc.nextLine();

        System.out.print("Enter quantity to add: ");
        int quantity = Integer.parseInt(sc.nextLine());

        system.restock(id, quantity);
    }
}
