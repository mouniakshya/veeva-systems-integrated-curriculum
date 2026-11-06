package com.ecommerce.model.discount;

import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderStatus;
import com.ecommerce.model.user.Customer;

import java.util.List;

/**
 * Satisfies Requirement 12:
 * "if a customer has multiple order having exceeding threshold value provide discounts"
 * Checks if the customer has multiple previous successful orders that each exceeded a threshold value (e.g. ₹5,000).
 */
public class CustomerHistoryThresholdDiscountPolicy implements DiscountPolicy {
    private final int minQualifyingOrders;
    private final double qualifyingOrderThreshold;
    private final double loyaltyDiscountPercentage;

    public CustomerHistoryThresholdDiscountPolicy(int minQualifyingOrders, double qualifyingOrderThreshold, double loyaltyDiscountPercentage) {
        this.minQualifyingOrders = minQualifyingOrders;
        this.qualifyingOrderThreshold = qualifyingOrderThreshold;
        this.loyaltyDiscountPercentage = loyaltyDiscountPercentage;
    }

    public CustomerHistoryThresholdDiscountPolicy() {
        this(2, 5000.0, 15.0); // Default: 2 past orders >= ₹5,000 grants 15% discount
    }

    @Override
    public double calculateDiscount(Customer customer, double orderAmount, List<Order> pastOrders) {
        if (pastOrders == null || pastOrders.isEmpty()) {
            return 0.0;
        }

        // Count past non-cancelled orders that exceeded threshold
        long qualifyingCount = pastOrders.stream()
                .filter(o -> o.getStatus() != OrderStatus.CANCELLED)
                .filter(o -> o.getOrderAmount() >= qualifyingOrderThreshold)
                .count();

        if (qualifyingCount >= minQualifyingOrders) {
            return (orderAmount * loyaltyDiscountPercentage) / 100.0;
        }

        return 0.0;
    }

    @Override
    public String getDescription() {
        return String.format("Loyalty Reward: %.1f%% off for customers with at least %d past orders exceeding ₹%,.2f",
                loyaltyDiscountPercentage, minQualifyingOrders, qualifyingOrderThreshold);
    }
}
