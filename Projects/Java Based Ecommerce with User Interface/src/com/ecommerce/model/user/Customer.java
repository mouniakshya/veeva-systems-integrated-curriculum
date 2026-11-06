package com.ecommerce.model.user;

import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.subscription.CustomerSubscription;
import com.ecommerce.model.subscription.SubscriptionTier;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an e-commerce customer.
 * Adheres strictly to Encapsulation and Abstraction.
 */
public class Customer extends User {
    private Address address;
    private CustomerSubscription subscription;
    private final LocalDateTime registeredAt;

    public Customer(String customerId, String name, String email, String phone, String password, Address address) {
        super(customerId, name, email, phone, password);
        setAddress(address);
        this.registeredAt = LocalDateTime.now();
        this.subscription = null;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address == null) {
            throw new ValidationException("Address cannot be null");
        }
        this.address = address;
    }

    public Optional<CustomerSubscription> getSubscription() {
        return Optional.ofNullable(subscription);
    }

    public void setSubscription(CustomerSubscription subscription) {
        this.subscription = subscription;
    }

    public void subscribe(SubscriptionTier tier) {
        Objects.requireNonNull(tier, "Subscription tier cannot be null");
        this.subscription = new CustomerSubscription(tier, LocalDateTime.now());
    }

    public boolean hasActiveSubscription() {
        return subscription != null && subscription.isActive();
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    @Override
    public String getRole() {
        return "CUSTOMER";
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id='%s', name='%s', email='%s', phone='%s', city='%s', subscription=%s]",
                getId(), getName(), getEmail(), getPhone(),
                address != null ? address.getCity() : "N/A",
                hasActiveSubscription() ? subscription.getTier().getDisplayName() : "None"
        );
    }
}
