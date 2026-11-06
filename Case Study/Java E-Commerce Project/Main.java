public class Main {
    public static void main(String[] args) {

        CustomerService cs = new CustomerService();

        cs.addCustomer(new Customer(
                1, "Ravi", "ravi@gmail.com",
                "Guntur", "9876543210", "ravi123"
        ));

        System.out.println(cs.login("ravi@gmail.com", "ravi123"));
        System.out.println(cs.findByEmail("ravi@gmail.com").getName());

        ProductCatalog catalog = new ProductCatalog();

        catalog.addProduct(new Product(
                101, "Chair", 1500, "Nilkamal"
        ));

        catalog.addProduct(new Product(
                102, "Office Chair", 2500, "GreenSoul"
        ));

        catalog.addProduct(new Product(
                103, "Wooden Table", 3500, "Urban Ladder"
        ));

        System.out.println("\nAll Products:");
        catalog.showAllProducts();

        System.out.println("\nSearch by ID:");
        System.out.println(catalog.findById(101));

        System.out.println("\nSearch chairs between 1000 and 2000:");
        for (Product p :
                catalog.findByPriceRange("Chair", 1000, 2000)) {
            System.out.println(p);
        }
    }
}