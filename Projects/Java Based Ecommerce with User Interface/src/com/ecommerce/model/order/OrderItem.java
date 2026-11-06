package com.ecommerce.model.order;

import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.product.Product;

import java.util.Objects;

/**
 * Snapshot of a purchased item within an order.
 * Immutable to preserve historical order records even if catalog changes.
 */
public final class OrderItem {
    private final String productId;
    private final String productName;
    private final String companyName;
    private final double unitPrice;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        Objects.requireNonNull(product, "Product cannot be null");
        if (quantity <= 0) {
            throw new ValidationException("Quantity must be positive");
        }
        this.productId = product.getProductId();
        this.productName = product.getName();
        this.companyName = product.getCompanyName();
        this.unitPrice = product.getPrice();
        this.quantity = quantity;
    }

    public OrderItem(String productId, String productName, String companyName, double unitPrice, int quantity) {
        if (productId == null || productName == null || quantity <= 0 || unitPrice < 0) {
            throw new ValidationException("Invalid OrderItem parameters");
        }
        this.productId = productId;
        this.productName = productName;
        this.companyName = companyName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) x %d @ ₹%,.2f = ₹%,.2f",
                productName, companyName, quantity, unitPrice, getSubtotal());
    }
}
