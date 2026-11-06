
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ProductService service = new ProductService();

        while (true) {

            System.out.println("\n===== PRODUCT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Sort by Price (Descending)");
            System.out.println("4. Sort by Price Range");
            System.out.println("5. Sort by Price and Manufacturing Date");
            System.out.println("6. Search by Product Name");
            System.out.println("7. Search by Manufacturing Date");
            System.out.println("8. Search by Price");
            System.out.println("9. Search by Name, Company and Price");
            System.out.println("10. Top K Products");
            System.out.println("11. Least K Products");
            System.out.println("12. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter Product ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Product Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();

                        System.out.print(
                                "Enter Manufacturing Date (yyyy-mm-dd): "
                        );
                        LocalDate date = LocalDate.parse(sc.next());

                        System.out.print("Enter Quantity: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Company Name: ");
                        String company = sc.nextLine();

                        Product p = new Product(
                                id, name, price, date, quantity, company
                        );

                        service.addProduct(p);
                        break;

                    case 2:
                        service.displayProducts();
                        break;

                    case 3:
                        service.sortByPrice();
                        break;

                    case 4:
                        System.out.print("Enter Minimum Price: ");
                        double min = sc.nextDouble();

                        System.out.print("Enter Maximum Price: ");
                        double max = sc.nextDouble();

                        if (min > max) {
                            System.out.println("Invalid price range.");
                        } else {
                            service.sortByPriceRange(min, max);
                        }
                        break;

                    case 5:
                        service.sortByPriceAndDate();
                        break;

                    case 6:
                        System.out.print("Enter Product Name: ");
                        String searchName = sc.nextLine();

                        service.searchByName(searchName);
                        break;

                    case 7:
                        System.out.print(
                                "Enter Manufacturing Date (yyyy-mm-dd): "
                        );
                        String searchDate = sc.nextLine();

                        service.searchByDate(searchDate);
                        break;

                    case 8:
                        System.out.print("Enter Price: ");
                        double searchPrice = sc.nextDouble();

                        service.searchByPrice(searchPrice);
                        break;

                    case 9:
                        System.out.print("Enter Product Name: ");
                        String n = sc.nextLine();

                        System.out.print("Enter Company Name: ");
                        String c = sc.nextLine();

                        System.out.print("Enter Price: ");
                        double pr = sc.nextDouble();

                        service.searchByDetails(n, c, pr);
                        break;

                    case 10:
                        System.out.print("Enter K: ");
                        int topK = sc.nextInt();

                        service.topKProducts(topK);
                        break;

                    case 11:
                        System.out.print("Enter K: ");
                        int leastK = sc.nextInt();

                        service.leastKProducts(leastK);
                        break;

                    case 12:
                        System.out.println("Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (InvalidProductException e) {

                System.out.println("Exception: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Invalid input: " + e.getMessage());
                sc.nextLine();
            }
        }
    }
}

