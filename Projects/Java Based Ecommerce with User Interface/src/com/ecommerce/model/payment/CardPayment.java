package com.ecommerce.model.payment;

import com.ecommerce.exception.ValidationException;

import java.util.UUID;

/**
 * Debit/Credit Card payment implementation.
 */
public class CardPayment implements PaymentMethod {
    private final CardType cardType;
    private final String cardNumber;
    private final String cardHolderName;
    private final String expiryDate; // MM/YY
    private final String cvv;

    public CardPayment(CardType cardType, String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        if (cardType == null) {
            throw new ValidationException("Card type cannot be null");
        }
        if (cardNumber == null || cardNumber.replaceAll("\\s+", "").length() < 12) {
            throw new ValidationException("Invalid card number length");
        }
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            throw new ValidationException("Card holder name cannot be empty");
        }
        if (cvv == null || cvv.trim().length() < 3) {
            throw new ValidationException("Invalid CVV");
        }

        this.cardType = cardType;
        this.cardNumber = cardNumber.replaceAll("\\s+", "");
        this.cardHolderName = cardHolderName.trim();
        this.expiryDate = expiryDate != null ? expiryDate.trim() : "12/28";
        this.cvv = cvv.trim();
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

        String txnId = "TXN-CARD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return PaymentResult.success(
                txnId,
                getMethodName(),
                amount,
                String.format("Payment of ₹%,.2f authorized via %s (%s)", amount, cardType, getMaskedDetails())
        );
    }

    @Override
    public String getMethodName() {
        return cardType == CardType.CREDIT_CARD ? "Credit Card" : "Debit Card";
    }

    @Override
    public String getMaskedDetails() {
        int len = cardNumber.length();
        String last4 = len > 4 ? cardNumber.substring(len - 4) : cardNumber;
        return String.format("****-****-****-%s (Exp: %s)", last4, expiryDate);
    }
}
