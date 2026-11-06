package com.ecommerce.exception;

/**
 * Thrown when user authentication fails.
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
