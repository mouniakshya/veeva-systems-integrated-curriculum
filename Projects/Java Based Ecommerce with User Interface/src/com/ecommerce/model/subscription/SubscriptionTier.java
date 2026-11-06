package com.ecommerce.model.subscription;

/**
 * Represents the available subscription tiers with their duration and pricing in Indian Rupees (₹).
 */
public enum SubscriptionTier {
    MONTHLY("Monthly Prime Membership", 30, 199.00),
    QUARTERLY("Quarterly Prime Membership", 90, 499.00),
    HALF_YEARLY("Half-Yearly Prime Membership", 180, 899.00),
    ANNUALLY("Annual VIP Prime Membership", 365, 1499.00);

    private final String displayName;
    private final int durationDays;
    private final double cost;

    SubscriptionTier(String displayName, int durationDays, double cost) {
        this.displayName = displayName;
        this.durationDays = durationDays;
        this.cost = cost;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public double getCost() {
        return cost;
    }
}
