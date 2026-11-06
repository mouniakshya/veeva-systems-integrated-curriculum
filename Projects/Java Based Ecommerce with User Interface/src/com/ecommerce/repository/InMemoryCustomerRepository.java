package com.ecommerce.repository;

import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.user.Customer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Thread-safe in-memory pool (database) of customer records.
 */
public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> customersById = new ConcurrentHashMap<>();

    @Override
    public synchronized void add(Customer customer) {
        if (customer == null) {
            throw new ValidationException("Customer cannot be null");
        }
        if (customersById.containsKey(customer.getId())) {
            throw new ValidationException("Customer with ID '" + customer.getId() + "' already exists");
        }
        if (existsByEmail(customer.getEmail())) {
            throw new ValidationException("Customer with email '" + customer.getEmail() + "' already exists");
        }
        if (existsByPhone(customer.getPhone())) {
            throw new ValidationException("Customer with phone '" + customer.getPhone() + "' already exists");
        }
        customersById.put(customer.getId(), customer);
    }

    @Override
    public synchronized boolean deleteById(String customerId) {
        if (customerId == null) return false;
        return customersById.remove(customerId) != null;
    }

    @Override
    public synchronized boolean update(Customer customer) {
        if (customer == null || !customersById.containsKey(customer.getId())) {
            return false;
        }

        // Check if email is changed to one that belongs to another customer
        Optional<Customer> existingWithEmail = findByEmail(customer.getEmail());
        if (existingWithEmail.isPresent() && !existingWithEmail.get().getId().equals(customer.getId())) {
            throw new ValidationException("Email '" + customer.getEmail() + "' is already in use by another customer");
        }

        // Check if phone is changed to one that belongs to another customer
        Optional<Customer> existingWithPhone = findByPhone(customer.getPhone());
        if (existingWithPhone.isPresent() && !existingWithPhone.get().getId().equals(customer.getId())) {
            throw new ValidationException("Phone '" + customer.getPhone() + "' is already in use by another customer");
        }

        customersById.put(customer.getId(), customer);
        return true;
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        if (customerId == null) return Optional.empty();
        return Optional.ofNullable(customersById.get(customerId));
    }

    @Override
    public List<Customer> findByName(String name) {
        if (name == null || name.trim().isEmpty()) return Collections.emptyList();
        String query = name.trim().toLowerCase();
        return customersById.values().stream()
                .filter(c -> c.getName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findByCity(String city) {
        if (city == null || city.trim().isEmpty()) return Collections.emptyList();
        String query = city.trim().toLowerCase();
        return customersById.values().stream()
                .filter(c -> c.getAddress() != null && c.getAddress().getCity().toLowerCase().equalsIgnoreCase(query))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findByPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) return Optional.empty();
        String cleaned = phone.replaceAll("[^0-9]", "");
        return customersById.values().stream()
                .filter(c -> {
                    String custPhone = c.getPhone().replaceAll("[^0-9]", "");
                    return custPhone.equals(cleaned) || c.getPhone().equalsIgnoreCase(phone.trim());
                })
                .findFirst();
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) return Optional.empty();
        String query = email.trim().toLowerCase();
        return customersById.values().stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(query))
                .findFirst();
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customersById.values());
    }

    @Override
    public boolean existsByEmail(String email) {
        return findByEmail(email).isPresent();
    }

    @Override
    public boolean existsByPhone(String phone) {
        return findByPhone(phone).isPresent();
    }

    @Override
    public long count() {
        return customersById.size();
    }
}
