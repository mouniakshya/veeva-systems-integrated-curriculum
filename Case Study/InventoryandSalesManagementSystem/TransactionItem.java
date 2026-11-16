
import java.math.BigDecimal;

public class TransactionItem {

    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;

    public TransactionItem(String productId, String productName,
                           int quantity, BigDecimal unitPrice) {

        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {

        return unitPrice.multiply(
                BigDecimal.valueOf(quantity)
        );
    }

    @Override
    public String toString() {

        return productName +
                " | Quantity: " + quantity +
                " | Unit Price: $" + unitPrice +
                " | Subtotal: $" + getSubtotal();
    }
}
