package com.ecommerce.model.subscription;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Encapsulates the customer subscription details.
 * Offers free shipping benefits when active.
 */
public class CustomerSubscription {
    private final SubscriptionTier tier;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private boolean autoRenew;

    public CustomerSubscription(SubscriptionTier tier, LocalDateTime startDate) {
        this.tier = Objects.requireNonNull(tier, "Tier cannot be null");
        this.startDate = Objects.requireNonNull(startDate, "Start date cannot be null");
        this.endDate = startDate.plusDays(tier.getDurationDays());
        this.autoRenew = false;
    }

    public SubscriptionTier getTier() {
        return tier;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    /**
     * Checks if the subscription is currently active.
     */
    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return !now.isBefore(startDate) && !now.isAfter(endDate);
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%s (Valid: %s to %s | Status: %s)",
                tier.getDisplayName(),
                startDate.format(fmt),
                endDate.format(fmt),
                isActive() ? "ACTIVE (Free Shipping Eligible)" : "EXPIRED");
    }
}
