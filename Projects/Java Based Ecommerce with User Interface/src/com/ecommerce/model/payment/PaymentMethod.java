package com.ecommerce.model.payment;

/**
 * Abstraction for all payment strategies.
 * Demonstrates Abstraction and Polymorphism.
 */
public interface PaymentMethod {
    /**
     * Executes the payment transaction for the specified order total.
     */
    PaymentResult pay(double amount);

    /**
     * Returns the name / type of the payment method.
     */
    String getMethodName();

    /**
     * Returns masked or sanitized details for display/receipt.
     */
    String getMaskedDetails();
}
