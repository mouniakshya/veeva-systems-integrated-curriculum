package com.ecommerce.model.payment;

import com.ecommerce.exception.ValidationException;

import java.util.Objects;
import java.util.UUID;

/**
 * Gift Card payment strategy implementing PaymentMethod.
 * Demonstrates polymorphism. Satisfies Requirement 10.
 */
public class GiftCardPayment implements PaymentMethod {
    private final GiftCard giftCard;
    private final String enteredPin;

    public GiftCardPayment(GiftCard giftCard, String enteredPin) {
        this.giftCard = Objects.requireNonNull(giftCard, "GiftCard cannot be null");
        this.enteredPin = enteredPin != null ? enteredPin.trim() : "";
    }

    @Override
    public PaymentResult pay(double amount) {
        if (!giftCard.getPin().equals(enteredPin)) {
            return PaymentResult.failure(
                    "GC-ERR-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
                    getMethodName(),
                    amount,
                    "Invalid Gift Card PIN"
            );
        }

        if (!giftCard.isActive()) {
            return PaymentResult.failure(
                    "GC-ERR-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
                    getMethodName(),
                    amount,
                    "Gift card is inactive or expired"
            );
        }

        if (!giftCard.hasSufficientBalance(amount)) {
            return PaymentResult.failure(
                    "GC-ERR-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
                    getMethodName(),
                    amount,
                    String.format("Insufficient gift card balance. Available: ₹%,.2f, Required: ₹%,.2f",
                            giftCard.getBalance(), amount)
            );
        }

        boolean deducted = giftCard.deduct(amount);
        if (deducted) {
            String txnId = "TXN-GC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            return PaymentResult.success(
                    txnId,
                    getMethodName(),
                    amount,
                    String.format("Successfully redeemed ₹%,.2f from Gift Card %s. Remaining balance: ₹%,.2f",
                            amount, getMaskedDetails(), giftCard.getBalance())
            );
        } else {
            return PaymentResult.failure(
                    "GC-ERR-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(),
                    getMethodName(),
                    amount,
                    "Failed to deduct gift card balance"
            );
        }
    }

    @Override
    public String getMethodName() {
        return "Gift Card";
    }

    @Override
    public String getMaskedDetails() {
        String code = giftCard.getCardCode().replaceAll("-", "");
        if (code.length() > 4) {
            return "****-****-" + code.substring(code.length() - 4);
        }
        return "****-" + code;
    }

    public GiftCard getGiftCard() {
        return giftCard;
    }
}
