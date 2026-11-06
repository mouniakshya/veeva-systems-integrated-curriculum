package com.ecommerce.model.order;

/**
 * Lifecycle states of a customer order.
 * Satisfies Requirement 5.
 */
public enum OrderStatus {
    CREATED_AND_RECEIVED("Order Just Created and Received"),
    DISPATCHED("Order Dispatched"),
    IN_TRANSIT("Order With Courier Agent and In Transit"),
    DELIVERED("Order Delivered to Customer"),
    RETURNED_AND_RECEIVED("Order Returned by Customer and Received"),
    CANCELLED("Order Cancelled");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
