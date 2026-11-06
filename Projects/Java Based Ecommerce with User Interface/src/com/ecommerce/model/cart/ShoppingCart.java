package com.ecommerce.model.cart;

import com.ecommerce.exception.InsufficientStockException;
import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.product.Product;

import java.util.*;

/**
 * Represents the customer's shopping basket.
 * Encapsulates cart manipulation and calculation of cart subtotal.
 */
public class ShoppingCart {
    private final String customerId;
    private final Map<String, CartItem> items = new LinkedHashMap<>();

    public ShoppingCart(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new ValidationException("Customer ID cannot be empty");
        }
        this.customerId = customerId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public synchronized void addItem(Product product, int quantity) {
        if (product == null) {
            throw new ValidationException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new ValidationException("Quantity must be greater than zero");
        }

        CartItem existing = items.get(product.getProductId());
        int totalQtyNeeded = (existing != null ? existing.getQuantity() : 0) + quantity;

        if (!product.hasSufficientStock(totalQtyNeeded)) {
            throw new InsufficientStockException(String.format(
                    "Cannot add %d items. Only %d in stock for '%s' (Already in cart: %d)",
                    quantity, product.getStockQuantity(), product.getName(),
                    existing != null ? existing.getQuantity() : 0));
        }

        if (existing != null) {
            existing.incrementQuantity(quantity);
        } else {
            items.put(product.getProductId(), new CartItem(product, quantity));
        }
    }

    public synchronized void updateQuantity(String productId, int newQuantity) {
        if (!items.containsKey(productId)) {
            throw new ValidationException("Product not found in cart: " + productId);
        }
        if (newQuantity <= 0) {
            removeItem(productId);
            return;
        }

        CartItem item = items.get(productId);
        if (!item.getProduct().hasSufficientStock(newQuantity)) {
            throw new InsufficientStockException(String.format(
                    "Cannot set quantity to %d. Only %d available for '%s'",
                    newQuantity, item.getProduct().getStockQuantity(), item.getProduct().getName()));
        }
        item.setQuantity(newQuantity);
    }

    public synchronized void removeItem(String productId) {
        items.remove(productId);
    }

    public synchronized void clear() {
        items.clear();
    }

    public synchronized List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public synchronized double calculateSubtotal() {
        return items.values().stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public synchronized int getTotalItemCount() {
        return items.values().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public synchronized boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Cart for Customer %s (%d items):\n", customerId, getTotalItemCount()));
        for (CartItem item : items.values()) {
            sb.append(String.format("  - %s (Unit: ₹%,.2f) x %d = ₹%,.2f\n",
                    item.getProduct().getName(), item.getProduct().getPrice(), item.getQuantity(), item.getSubtotal()));
        }
        sb.append(String.format("Cart Subtotal: ₹%,.2f", calculateSubtotal()));
        return sb.toString();
    }
}
