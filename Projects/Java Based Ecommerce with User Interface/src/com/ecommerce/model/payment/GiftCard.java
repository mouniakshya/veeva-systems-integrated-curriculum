package com.ecommerce.model.payment;

import com.ecommerce.exception.ValidationException;

import java.time.LocalDateTime;

/**
 * Represents a digital Gift Card.
 * Satisfies Requirement 10.
 */
public class GiftCard {
    private final String cardCode;
    private final String pin;
    private double balance;
    private final LocalDateTime expiryDate;
    private boolean active;

    public GiftCard(String cardCode, String pin, double initialBalance, LocalDateTime expiryDate) {
        if (cardCode == null || cardCode.trim().isEmpty()) {
            throw new ValidationException("Gift card code cannot be empty");
        }
        if (initialBalance < 0) {
            throw new ValidationException("Initial balance cannot be negative");
        }
        this.cardCode = cardCode.trim().toUpperCase();
        this.pin = pin != null ? pin.trim() : "0000";
        this.balance = initialBalance;
        this.expiryDate = expiryDate != null ? expiryDate : LocalDateTime.now().plusYears(1);
        this.active = true;
    }

    public String getCardCode() {
        return cardCode;
    }

    public String getPin() {
        return pin;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public boolean isActive() {
        return active && LocalDateTime.now().isBefore(expiryDate);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public synchronized boolean hasSufficientBalance(double amount) {
        return isActive() && this.balance >= amount;
    }

    public synchronized boolean deduct(double amount) {
        if (amount < 0) {
            throw new ValidationException("Deduction amount cannot be negative");
        }
        if (!hasSufficientBalance(amount)) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public synchronized void reload(double amount) {
        if (amount <= 0) {
            throw new ValidationException("Reload amount must be positive");
        }
        this.balance += amount;
    }

    @Override
    public String toString() {
        return String.format("GiftCard[%s, Balance=₹%,.2f, Active=%s, Expires=%s]",
                cardCode, balance, isActive(), expiryDate.toLocalDate());
    }
}
