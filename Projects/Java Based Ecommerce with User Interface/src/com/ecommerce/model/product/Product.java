package com.ecommerce.model.product;

import com.ecommerce.exception.InsufficientStockException;
import com.ecommerce.exception.ValidationException;

import java.util.Objects;

/**
 * Represents a product in the e-commerce catalog.
 * Follows encapsulation with private attributes, validation, and controlled state mutations.
 */
public class Product {
    private final String productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String companyName;
    private String category;

    public Product(String productId, String name, String description, double price, int stockQuantity, String companyName, String category) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new ValidationException("Product ID cannot be empty");
        }
        this.productId = productId.trim();
        setName(name);
        setDescription(description);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setCompanyName(companyName);
        setCategory(category);
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Product name cannot be empty");
        }
        this.name = name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description != null ? description.trim() : "";
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0.0) {
            throw new ValidationException("Price cannot be negative: " + price);
        }
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new ValidationException("Stock quantity cannot be negative: " + stockQuantity);
        }
        this.stockQuantity = stockQuantity;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new ValidationException("Company name cannot be empty");
        }
        this.companyName = companyName.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category != null ? category.trim() : "General";
    }

    public synchronized boolean hasSufficientStock(int requestedQty) {
        return requestedQty > 0 && this.stockQuantity >= requestedQty;
    }

    public synchronized void deductStock(int quantity) {
        if (quantity <= 0) {
            throw new ValidationException("Quantity to deduct must be positive");
        }
        if (this.stockQuantity < quantity) {
            throw new InsufficientStockException(
                    String.format("Insufficient stock for '%s' (ID: %s). Available: %d, Requested: %d",
                            name, productId, stockQuantity, quantity));
        }
        this.stockQuantity -= quantity;
    }

    public synchronized void addStock(int quantity) {
        if (quantity <= 0) {
            throw new ValidationException("Quantity to add must be positive");
        }
        this.stockQuantity += quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return String.format("Product[ID=%s, Name='%s', Company='%s', Price=₹%,.2f, Stock=%d, Category='%s']",
                productId, name, companyName, price, stockQuantity, category);
    }
}
