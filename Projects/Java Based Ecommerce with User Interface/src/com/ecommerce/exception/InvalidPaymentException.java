package com.ecommerce.exception;

/**
 * Thrown when a payment cannot be completed due to invalid parameters or balance.
 */
public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}
