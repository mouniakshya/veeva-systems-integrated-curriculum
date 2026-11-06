package com.ecommerce.service;

import com.ecommerce.exception.AuthenticationException;
import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.user.Address;
import com.ecommerce.model.user.Admin;
import com.ecommerce.model.user.Customer;
import com.ecommerce.model.user.User;
import com.ecommerce.repository.CustomerRepository;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service managing user registration, authentication, and session.
 * Satisfies Requirement 4.
 */
public class AuthService {
    private final CustomerRepository customerRepository;
    private final Map<String, Admin> adminsByEmail = new ConcurrentHashMap<>();
    private User currentLoggedInUser = null;

    public AuthService(CustomerRepository customerRepository) {
        this.customerRepository = Objects.requireNonNull(customerRepository, "CustomerRepository cannot be null");

        // Seed a default administrator in New Delhi Central Hub
        Admin defaultAdmin = new Admin("ADM-001", "Platform Administrator", "admin@ecommerce.com", "+91-11-23456789", "admin123", "Delhi Central Operations");
        adminsByEmail.put(defaultAdmin.getEmail().toLowerCase(), defaultAdmin);
    }

    public synchronized Customer registerCustomer(String id, String name, String email, String phone, String password, Address address) {
        if (customerRepository.existsByEmail(email) || adminsByEmail.containsKey(email.toLowerCase())) {
            throw new ValidationException("An account with email '" + email + "' already exists");
        }
        if (customerRepository.existsByPhone(phone)) {
            throw new ValidationException("An account with phone '" + phone + "' already exists");
        }

        Customer customer = new Customer(id, name, email, phone, password, address);
        customerRepository.add(customer);
        return customer;
    }

    public synchronized User login(String email, String password) {
        if (email == null || password == null) {
            throw new AuthenticationException("Email and password are required");
        }
        String cleanEmail = email.trim().toLowerCase();

        // Check if Admin
        Admin admin = adminsByEmail.get(cleanEmail);
        if (admin != null) {
            if (admin.verifyPassword(password)) {
                this.currentLoggedInUser = admin;
                return admin;
            } else {
                throw new AuthenticationException("Invalid admin password");
            }
        }

        // Check if Customer
        Optional<Customer> customerOpt = customerRepository.findByEmail(cleanEmail);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (customer.verifyPassword(password)) {
                this.currentLoggedInUser = customer;
                return customer;
            } else {
                throw new AuthenticationException("Invalid password for customer: " + email);
            }
        }

        throw new AuthenticationException("No account found for email: " + email);
    }

    public synchronized void logout() {
        this.currentLoggedInUser = null;
    }

    public synchronized Optional<User> getCurrentLoggedInUser() {
        return Optional.ofNullable(currentLoggedInUser);
    }

    public synchronized boolean isLoggedIn() {
        return currentLoggedInUser != null;
    }

    public synchronized boolean isCustomerLoggedIn() {
        return currentLoggedInUser instanceof Customer;
    }

    public synchronized boolean isAdminLoggedIn() {
        return currentLoggedInUser instanceof Admin;
    }

    public synchronized Customer getLoggedInCustomer() {
        if (currentLoggedInUser instanceof Customer customer) {
            return customer;
        }
        throw new AuthenticationException("No customer is currently logged in. Please log in first.");
    }

    public synchronized Admin getLoggedInAdmin() {
        if (currentLoggedInUser instanceof Admin admin) {
            return admin;
        }
        throw new AuthenticationException("Admin authorization required.");
    }
}
