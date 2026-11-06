package com.ecommerce.model.order;

import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.payment.PaymentResult;
import com.ecommerce.model.tracking.TrackingInfo;
import com.ecommerce.model.user.Address;
import com.ecommerce.model.user.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a customer order in the e-commerce system.
 * Encapsulates order state, pricing calculation, tracking, and status transitions.
 * Satisfies Requirements 5, 6, 8, 9, 11, 12.
 */
public class Order {
    private final String orderId;
    private final Customer customer;
    private final List<OrderItem> items;
    private final Address shippingAddress;
    private final double orderAmount;
    private final double discountAmount;
    private final double shippingCharge;
    private final double totalAmount;
    private final LocalDateTime orderDate;
    private OrderStatus status;
    private PaymentResult paymentResult;
    private TrackingInfo trackingInfo;
    private LocalDateTime updatedAt;

    public Order(String orderId, Customer customer, List<OrderItem> items,
                 Address shippingAddress, double discountAmount, double shippingCharge,
                 PaymentResult paymentResult) {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new ValidationException("Order ID cannot be empty");
        }
        this.orderId = orderId.trim();
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null");
        if (items == null || items.isEmpty()) {
            throw new ValidationException("Order must contain at least one item");
        }
        this.items = new ArrayList<>(items);
        this.shippingAddress = shippingAddress != null ? shippingAddress : customer.getAddress();

        // Calculate orderAmount from items
        this.orderAmount = items.stream().mapToDouble(OrderItem::getSubtotal).sum();
        this.discountAmount = Math.max(0.0, discountAmount);
        this.shippingCharge = Math.max(0.0, shippingCharge);

        // Requirement 9: Order Total = Order Amount + Shipping Charge (- Discount)
        double net = (this.orderAmount - this.discountAmount) + this.shippingCharge;
        this.totalAmount = Math.max(0.0, net);

        this.paymentResult = paymentResult;
        this.status = OrderStatus.CREATED_AND_RECEIVED;
        this.orderDate = LocalDateTime.now();
        this.updatedAt = this.orderDate;
        this.trackingInfo = null;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getShippingCharge() {
        return shippingCharge;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public synchronized OrderStatus getStatus() {
        return status;
    }

    public synchronized void setStatus(OrderStatus status) {
        this.status = Objects.requireNonNull(status, "Status cannot be null");
        this.updatedAt = LocalDateTime.now();
    }

    public synchronized PaymentResult getPaymentResult() {
        return paymentResult;
    }

    public synchronized void setPaymentResult(PaymentResult paymentResult) {
        this.paymentResult = paymentResult;
    }

    public synchronized TrackingInfo getTrackingInfo() {
        return trackingInfo;
    }

    public synchronized void setTrackingInfo(TrackingInfo trackingInfo) {
        this.trackingInfo = trackingInfo;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getFormattedInvoice() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("======================== TAX INVOICE (BHARATMART INDIA) ========================\n");
        sb.append("GSTIN           : 07AABCB1234F1Z9 (Delhi State Code: 07)\n");
        sb.append(String.format("Order ID        : %s\n", orderId));
        sb.append(String.format("Invoice Date    : %s\n", orderDate.format(fmt)));
        sb.append(String.format("Customer Name   : %s\n", customer.getName()));
        sb.append(String.format("Contact Email   : %s | Phone: %s\n", customer.getEmail(), customer.getPhone()));
        sb.append(String.format("Delivery Address: %s\n", shippingAddress.getFormattedAddress()));
        sb.append(String.format("Order Status    : %s\n", status.getDescription()));
        if (paymentResult != null) {
            sb.append(String.format("Payment Mode    : %s (Txn Ref: %s | %s)\n",
                    paymentResult.getPaymentMethodType(), paymentResult.getTransactionId(), paymentResult.getStatus()));
        }
        sb.append("--------------------------------------------------------------------------------\n");
        sb.append(String.format("%-26s %-14s %-5s %-14s %-14s\n", "Item Description", "Brand", "Qty", "Unit Price(₹)", "Total (₹)"));
        sb.append("--------------------------------------------------------------------------------\n");
        for (OrderItem item : items) {
            sb.append(String.format("%-26s %-14s %-5d ₹%-13.2f ₹%-13.2f\n",
                    truncate(item.getProductName(), 25),
                    truncate(item.getCompanyName(), 13),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getSubtotal()));
        }
        sb.append("--------------------------------------------------------------------------------\n");
        sb.append(String.format("Subtotal Amount (Excl. Tax) : ₹%,.2f\n", orderAmount));
        if (discountAmount > 0) {
            sb.append(String.format("Festive / Loyalty Discount  : -₹%,.2f\n", discountAmount));
        }
        sb.append(String.format("Delivery / Shipping Charge  : ₹%,.2f%s\n",
                shippingCharge,
                customer.hasActiveSubscription() ? " (WAIVED - Prime Member Free Express Delivery)" : ""));
        sb.append(String.format("FINAL INVOICE TOTAL (INCL. GST): ₹%,.2f\n", totalAmount));
        sb.append("================================================================================");
        return sb.toString();
    }

    private String truncate(String text, int max) {
        if (text == null) return "";
        return text.length() <= max ? text : text.substring(0, max - 3) + "...";
    }

    @Override
    public String toString() {
        return String.format("Order[ID=%s, Customer=%s, Total=₹%,.2f, Status=%s]",
                orderId, customer.getName(), totalAmount, status.getDescription());
    }
}
