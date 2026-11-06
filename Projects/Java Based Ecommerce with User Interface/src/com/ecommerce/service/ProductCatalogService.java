package com.ecommerce.service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.product.Product;
import com.ecommerce.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service orchestrating Product Catalog management and all specified query variants.
 * Satisfies Requirement 3 (CRUD & Queries i through vi).
 */
public class ProductCatalogService {
    private final ProductRepository productRepository;

    public ProductCatalogService(ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository, "ProductRepository cannot be null");
    }

    // Product Management (CRUD)
    public void addProduct(Product product) {
        productRepository.add(product);
    }

    public boolean deleteProduct(String productId) {
        if (!productRepository.deleteById(productId)) {
            throw new ResourceNotFoundException("Cannot delete: Product with ID '" + productId + "' not found");
        }
        return true;
    }

    public boolean updateProduct(Product product) {
        if (!productRepository.update(product)) {
            throw new ResourceNotFoundException("Cannot update: Product with ID '" + product.getProductId() + "' not found");
        }
        return true;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ==================== Requirement 3 Queries ====================

    /**
     * Query i: When a product id is given return the corresponding product details.
     */
    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    /**
     * Query ii: When a product name is given return the list of products corresponding to name.
     */
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    /**
     * Query iii: When a product name, price is given return products matching those.
     */
    public List<Product> getProductsByNameAndPrice(String name, double price) {
        return productRepository.findByNameAndPrice(name, price);
    }

    /**
     * Query iv: When a product name, range of price is given return the list of products within the range.
     */
    public List<Product> getProductsByNameAndPriceRange(String name, double minPrice, double maxPrice) {
        return productRepository.findByNameAndPriceRange(name, minPrice, maxPrice);
    }

    /**
     * Query v: When a product name, company name is given return list of products produced by that company.
     */
    public List<Product> getProductsByNameAndCompany(String name, String companyName) {
        return productRepository.findByNameAndCompany(name, companyName);
    }

    /**
     * Query vi: When a product name, company name, price is given return the products matching those.
     */
    public List<Product> getProductsByNameCompanyAndPrice(String name, String companyName, double price) {
        return productRepository.findByNameCompanyAndPrice(name, companyName, price);
    }

    // Additional helper queries
    public List<Product> getProductsByCompany(String companyName) {
        return productRepository.findByCompany(companyName);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public long getProductCount() {
        return productRepository.count();
    }
}
