package com.ecommerce.model.user;

import com.ecommerce.exception.ValidationException;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Abstract base class representing a platform user.
 * Demonstrates Abstraction and Encapsulation.
 */
public abstract class User {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[0-9+()\\-\\s]{7,15}$");

    private final String id;
    private String name;
    private String email;
    private String phone;
    private String password;

    public User(String id, String name, String email, String phone, String password) {
        if (id == null || id.trim().isEmpty()) {
            throw new ValidationException("User ID cannot be empty");
        }
        this.id = id.trim();
        setName(name);
        setEmail(email);
        setPhone(phone);
        setPassword(password);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new ValidationException("Invalid email format: " + email);
        }
        this.email = email.trim().toLowerCase();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || !PHONE_PATTERN.matcher(phone.trim()).matches()) {
            throw new ValidationException("Invalid phone number format: " + phone);
        }
        this.phone = phone.trim();
    }

    public boolean verifyPassword(String rawPassword) {
        return this.password != null && this.password.equals(rawPassword);
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 4) {
            throw new ValidationException("Password must be at least 4 characters long");
        }
        this.password = password;
    }

    /**
     * Abstract method defining the role description of the user.
     */
    public abstract String getRole();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
