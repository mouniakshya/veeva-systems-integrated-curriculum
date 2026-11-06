
import java.time.LocalDate;

public class Product {
    private int productId;
    private String name;
    private double price;
    private LocalDate manufacturingDate;
    private int quantity;
    private String company;

    public Product(int productId, String name, double price,
                   LocalDate manufacturingDate, int quantity,
                   String company) {

        this.productId = productId;
        this.name = name;
        this.price = price;
        this.manufacturingDate = manufacturingDate;
        this.quantity = quantity;
        this.company = company;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCompany() {
        return company;
    }

    public void display() {
        System.out.printf(
                "%-5d %-15s %-10.2f %-15s %-12d %-15s%n",
                productId,
                name,
                price,
                manufacturingDate,
                quantity,
                company
        );
    }
}
