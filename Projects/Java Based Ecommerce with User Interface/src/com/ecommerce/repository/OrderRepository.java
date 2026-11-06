package com.ecommerce.repository;

import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderStatus;

import java.util.List;
import java.util.Optional;

/**
 * Abstraction for Order persistence and queries.
 */
public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(String orderId);
    List<Order> findByCustomerId(String customerId);
    List<Order> findByStatus(OrderStatus status);
    List<Order> findAll();
    boolean updateStatus(String orderId, OrderStatus newStatus);
    long count();
}
