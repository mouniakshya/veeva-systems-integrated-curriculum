package com.ecommerce.exception;

/**
 * Thrown when a requested resource (Customer, Product, Order, etc.) is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
