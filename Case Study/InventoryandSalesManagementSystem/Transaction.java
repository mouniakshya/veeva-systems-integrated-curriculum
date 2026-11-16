
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Transaction {

    private String transactionId;
    private List<TransactionItem> items;
    private BigDecimal totalAmount;
    private LocalDateTime timestamp;

    public Transaction(String transactionId,
                       List<TransactionItem> items,
                       BigDecimal totalAmount) {

        this.transactionId = transactionId;
        this.items = List.copyOf(items);
        this.totalAmount = totalAmount;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public List<TransactionItem> getItems() {
        return items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void displayTransaction() {

        System.out.println("\nTransaction ID: " + transactionId);

        for (TransactionItem item : items) {
            System.out.println(item);
        }

        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Timestamp: " + timestamp);
    }
}
