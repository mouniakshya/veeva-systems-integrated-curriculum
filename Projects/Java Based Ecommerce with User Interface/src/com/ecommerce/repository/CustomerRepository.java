package com.ecommerce.repository;

import com.ecommerce.model.user.Customer;

import java.util.List;
import java.util.Optional;

/**
 * Abstraction for Customer data persistence and querying.
 */
public interface CustomerRepository {
    void add(Customer customer);
    boolean deleteById(String customerId);
    boolean update(Customer customer);
    Optional<Customer> findById(String customerId);
    List<Customer> findByName(String name);
    List<Customer> findByCity(String city);
    Optional<Customer> findByPhone(String phone);
    Optional<Customer> findByEmail(String email);
    List<Customer> findAll();
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    long count();
}
