package com.ecommerce.repository;

import com.ecommerce.model.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Repository interface defining CRUD and rich query capabilities for the Product Catalog.
 * Satisfies Requirement 3.
 */
public interface ProductRepository {
    void add(Product product);
    boolean deleteById(String productId);
    boolean update(Product product);
    List<Product> findAll();

    // Query i: When a product id is given return the corresponding product details
    Optional<Product> findById(String productId);

    // Query ii: When a product name is given return the list of products corresponding to name
    List<Product> findByName(String name);

    // Query iii: When a product name, price is given return products matching those
    List<Product> findByNameAndPrice(String name, double price);

    // Query iv: When a product name, range of price is given return the list of products within the range
    List<Product> findByNameAndPriceRange(String name, double minPrice, double maxPrice);

    // Query v: When a product name, company name is given return list of products produced by that company
    List<Product> findByNameAndCompany(String name, String companyName);

    // Query vi: When a product name, company name, price is given return the products matching those
    List<Product> findByNameCompanyAndPrice(String name, String companyName, double price);

    // Additional query helpers
    List<Product> findByCompany(String companyName);
    List<Product> findByCategory(String category);
    List<Product> filter(Predicate<Product> predicate);
    long count();
}
