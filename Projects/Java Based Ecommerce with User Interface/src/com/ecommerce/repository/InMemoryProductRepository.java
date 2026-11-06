package com.ecommerce.repository;

import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.product.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * In-memory thread-safe implementation of ProductRepository.
 */
public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> productsById = new ConcurrentHashMap<>();

    @Override
    public synchronized void add(Product product) {
        if (product == null) {
            throw new ValidationException("Product cannot be null");
        }
        if (productsById.containsKey(product.getProductId())) {
            throw new ValidationException("Product with ID '" + product.getProductId() + "' already exists");
        }
        productsById.put(product.getProductId(), product);
    }

    @Override
    public synchronized boolean deleteById(String productId) {
        if (productId == null) return false;
        return productsById.remove(productId) != null;
    }

    @Override
    public synchronized boolean update(Product product) {
        if (product == null || !productsById.containsKey(product.getProductId())) {
            return false;
        }
        productsById.put(product.getProductId(), product);
        return true;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productsById.values());
    }

    @Override
    public Optional<Product> findById(String productId) {
        if (productId == null) return Optional.empty();
        return Optional.ofNullable(productsById.get(productId));
    }

    @Override
    public List<Product> findByName(String name) {
        if (name == null || name.trim().isEmpty()) return Collections.emptyList();
        String query = name.trim().toLowerCase();
        return productsById.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByNameAndPrice(String name, double price) {
        if (name == null) return Collections.emptyList();
        String query = name.trim().toLowerCase();
        return productsById.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(query) && Math.abs(p.getPrice() - price) < 0.001)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByNameAndPriceRange(String name, double minPrice, double maxPrice) {
        if (name == null) return Collections.emptyList();
        if (minPrice > maxPrice) {
            throw new ValidationException("Minimum price cannot be greater than maximum price");
        }
        String query = name.trim().toLowerCase();
        return productsById.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(query) &&
                             p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByNameAndCompany(String name, String companyName) {
        if (name == null || companyName == null) return Collections.emptyList();
        String nameQuery = name.trim().toLowerCase();
        String compQuery = companyName.trim().toLowerCase();
        return productsById.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(nameQuery) &&
                             p.getCompanyName().toLowerCase().contains(compQuery))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByNameCompanyAndPrice(String name, String companyName, double price) {
        if (name == null || companyName == null) return Collections.emptyList();
        String nameQuery = name.trim().toLowerCase();
        String compQuery = companyName.trim().toLowerCase();
        return productsById.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(nameQuery) &&
                             p.getCompanyName().toLowerCase().contains(compQuery) &&
                             Math.abs(p.getPrice() - price) < 0.001)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCompany(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) return Collections.emptyList();
        String query = companyName.trim().toLowerCase();
        return productsById.values().stream()
                .filter(p -> p.getCompanyName().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCategory(String category) {
        if (category == null || category.trim().isEmpty()) return Collections.emptyList();
        String query = category.trim().toLowerCase();
        return productsById.values().stream()
                .filter(p -> p.getCategory().toLowerCase().contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> filter(Predicate<Product> predicate) {
        if (predicate == null) return findAll();
        return productsById.values().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return productsById.size();
    }
}
