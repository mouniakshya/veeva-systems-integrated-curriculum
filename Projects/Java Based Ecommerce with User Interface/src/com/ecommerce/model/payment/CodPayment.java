package com.ecommerce.model.payment;

import java.util.Random;
import java.util.UUID;

/**
 * Cash on Delivery (COD) payment method.
 */
public class CodPayment implements PaymentMethod {
    private final String deliveryInstructions;
    private final String cashCollectionPin;

    public CodPayment(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions != null ? deliveryInstructions : "Exact cash preferred upon delivery";
        this.cashCollectionPin = String.format("%04d", new Random().nextInt(10000));
    }

    public CodPayment() {
        this("Exact cash preferred upon delivery");
    }

    public String getCashCollectionPin() {
        return cashCollectionPin;
    }

    @Override
    public PaymentResult pay(double amount) {
        String txnId = "COD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return PaymentResult.pending(
                txnId,
                getMethodName(),
                amount,
                String.format("Cash on delivery confirmed for ₹%,.2f. Delivery verification PIN: %s", amount, cashCollectionPin)
        );
    }

    @Override
    public String getMethodName() {
        return "Cash on Delivery (COD)";
    }

    @Override
    public String getMaskedDetails() {
        return "COD Verification PIN: " + cashCollectionPin + " | " + deliveryInstructions;
    }
}
