package com.ecommerce.model.discount;

import com.ecommerce.model.order.Order;
import com.ecommerce.model.user.Customer;

import java.util.List;

/**
 * Applies a percentage discount when the current order amount exceeds a configured threshold in Indian Rupees.
 */
public class OrderThresholdDiscountPolicy implements DiscountPolicy {
    private final double thresholdAmount;
    private final double discountPercentage; // e.g., 10.0 for 10%

    public OrderThresholdDiscountPolicy(double thresholdAmount, double discountPercentage) {
        this.thresholdAmount = thresholdAmount;
        this.discountPercentage = discountPercentage;
    }

    public OrderThresholdDiscountPolicy() {
        this(5000.0, 10.0); // 10% off on orders exceeding ₹5,000
    }

    @Override
    public double calculateDiscount(Customer customer, double orderAmount, List<Order> pastOrders) {
        if (orderAmount >= thresholdAmount) {
            return (orderAmount * discountPercentage) / 100.0;
        }
        return 0.0;
    }

    @Override
    public String getDescription() {
        return String.format("%.1f%% off on orders exceeding ₹%,.2f", discountPercentage, thresholdAmount);
    }
}
