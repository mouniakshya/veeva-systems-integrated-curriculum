package com.ecommerce.model.cart;

import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.product.Product;

import java.util.Objects;

/**
 * Represents an individual item in a customer's shopping cart.
 */
public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = Objects.requireNonNull(product, "Product cannot be null");
        setQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new ValidationException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
    }

    public void incrementQuantity(int delta) {
        if (delta <= 0) {
            throw new ValidationException("Delta must be positive");
        }
        this.quantity += delta;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x %d = $%.2f", product.getName(), quantity, getSubtotal());
    }
}
