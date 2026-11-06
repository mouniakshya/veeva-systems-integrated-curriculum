package com.ecommerce.exception;

/**
 * Thrown when an order or cart request exceeds available product stock.
 */
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
