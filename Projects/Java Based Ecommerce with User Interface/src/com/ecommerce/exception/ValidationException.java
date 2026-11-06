package com.ecommerce.exception;

/**
 * Thrown when data validation fails.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
