package com.ecommerce.service;

import com.ecommerce.exception.InvalidPaymentException;
import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.payment.PaymentMethod;
import com.ecommerce.model.payment.PaymentResult;
import com.ecommerce.model.subscription.CustomerSubscription;
import com.ecommerce.model.subscription.SubscriptionTier;
import com.ecommerce.model.user.Customer;
import com.ecommerce.repository.CustomerRepository;

import java.util.Objects;

/**
 * Service managing customer subscription lifecycle and free shipping perks.
 * Satisfies Requirement 11.
 */
public class SubscriptionService {
    private final CustomerRepository customerRepository;

    public SubscriptionService(CustomerRepository customerRepository) {
        this.customerRepository = Objects.requireNonNull(customerRepository, "CustomerRepository cannot be null");
    }

    public CustomerSubscription purchaseSubscription(Customer customer, SubscriptionTier tier, PaymentMethod paymentMethod) {
        if (customer == null) {
            throw new ValidationException("Customer cannot be null");
        }
        if (tier == null) {
            throw new ValidationException("Subscription tier cannot be null");
        }
        if (paymentMethod == null) {
            throw new ValidationException("Payment method cannot be null");
        }

        // Process payment for subscription fee
        double cost = tier.getCost();
        PaymentResult result = paymentMethod.pay(cost);
        if (!result.isSuccess()) {
            throw new InvalidPaymentException("Subscription purchase failed: " + result.getMessage());
        }

        // Activate subscription on customer
        customer.subscribe(tier);
        customerRepository.update(customer);

        return customer.getSubscription().orElseThrow();
    }

    public boolean isEligibleForFreeShipping(Customer customer) {
        return customer != null && customer.hasActiveSubscription();
    }
}
