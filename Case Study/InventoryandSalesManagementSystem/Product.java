
import java.math.BigDecimal;

public class Product {

    private String productId;
    private String productName;
    private BigDecimal price;
    private String category;
    private String companyId;

    public Product(String productId, String productName,
                   BigDecimal price, String category,
                   String companyId) {

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.companyId = companyId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void updateDetails(String name, BigDecimal price,
                              String category, String companyId) {

        this.productName = name;
        this.price = price;
        this.category = category;
        this.companyId = companyId;
    }

    @Override
    public String toString() {

        return "Product ID: " + productId +
                "\nProduct Name: " + productName +
                "\nPrice: $" + price +
                "\nCategory: " + category +
                "\nCompany ID: " + companyId;
    }
}
