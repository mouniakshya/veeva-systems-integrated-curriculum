package com.ecommerce.service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.user.Address;
import com.ecommerce.model.user.Customer;
import com.ecommerce.repository.CustomerRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service managing customer pool operations and queries.
 * Satisfies Requirements 1 & 2.
 */
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = Objects.requireNonNull(customerRepository, "CustomerRepository cannot be null");
    }

    public Customer registerCustomer(String id, String name, String email, String phone, String password, Address address) {
        Customer customer = new Customer(id, name, email, phone, password, address);
        customerRepository.add(customer);
        return customer;
    }

    public void addCustomer(Customer customer) {
        customerRepository.add(customer);
    }

    public boolean deleteCustomer(String customerId) {
        if (!customerRepository.deleteById(customerId)) {
            throw new ResourceNotFoundException("Cannot delete: Customer with ID '" + customerId + "' not found");
        }
        return true;
    }

    public boolean updateCustomer(Customer customer) {
        if (!customerRepository.update(customer)) {
            throw new ResourceNotFoundException("Cannot update: Customer with ID '" + customer.getId() + "' not found");
        }
        return true;
    }

    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID '" + customerId + "' not found"));
    }

    public Optional<Customer> findCustomerById(String customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByName(name);
    }

    public List<Customer> getCustomersByCity(String city) {
        return customerRepository.findByCity(city);
    }

    public Optional<Customer> getCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public long getCustomerCount() {
        return customerRepository.count();
    }
}
