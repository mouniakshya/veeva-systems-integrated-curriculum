package com.ecommerce.model.discount;

import com.ecommerce.model.order.Order;
import com.ecommerce.model.user.Customer;

import java.util.List;

/**
 * Strategy interface for calculating order discounts.
 * Demonstrates Abstraction and Polymorphism. Satisfies Requirement 12.
 */
public interface DiscountPolicy {
    /**
     * Calculates discount amount applicable to this order.
     */
    double calculateDiscount(Customer customer, double orderAmount, List<Order> pastOrders);

    /**
     * Human-readable description of the discount scheme.
     */
    String getDescription();
}
