package com.ecommerce.model.payment;

import com.ecommerce.exception.ValidationException;

import java.util.UUID;

/**
 * UPI (Unified Payments Interface) payment implementation.
 */
public class UpiPayment implements PaymentMethod {
    private final String upiId;
    private final String appName;

    public UpiPayment(String upiId, String appName) {
        if (upiId == null || !upiId.contains("@") || upiId.trim().length() < 5) {
            throw new ValidationException("Invalid UPI ID: " + upiId);
        }
        this.upiId = upiId.trim().toLowerCase();
        this.appName = (appName != null && !appName.trim().isEmpty()) ? appName.trim() : "UPI App";
    }

    public String getUpiId() {
        return upiId;
    }

    public String getAppName() {
        return appName;
    }

    @Override
    public PaymentResult pay(double amount) {
        if (amount < 0) {
            return PaymentResult.failure(
                    "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                    getMethodName(),
                    amount,
                    "Invalid payment amount: " + amount
            );
        }

        String txnId = "TXN-UPI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return PaymentResult.success(
                txnId,
                getMethodName(),
                amount,
                String.format("Payment of ₹%,.2f completed instantly via %s (%s)", amount, appName, upiId)
        );
    }

    @Override
    public String getMethodName() {
        return "UPI (" + appName + ")";
    }

    @Override
    public String getMaskedDetails() {
        int atIdx = upiId.indexOf('@');
        if (atIdx > 3) {
            return upiId.substring(0, 2) + "***" + upiId.substring(atIdx);
        }
        return upiId;
    }
}
