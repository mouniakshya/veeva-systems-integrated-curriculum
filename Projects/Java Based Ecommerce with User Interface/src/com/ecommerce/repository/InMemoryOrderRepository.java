package com.ecommerce.repository;

import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderStatus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Thread-safe in-memory pool of customer orders.
 */
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> ordersById = new ConcurrentHashMap<>();

    @Override
    public synchronized void save(Order order) {
        if (order == null) {
            throw new ValidationException("Order cannot be null");
        }
        ordersById.put(order.getOrderId(), order);
    }

    @Override
    public Optional<Order> findById(String orderId) {
        if (orderId == null) return Optional.empty();
        return Optional.ofNullable(ordersById.get(orderId));
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        if (customerId == null) return Collections.emptyList();
        return ordersById.values().stream()
                .filter(o -> o.getCustomer().getId().equals(customerId))
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        if (status == null) return Collections.emptyList();
        return ordersById.values().stream()
                .filter(o -> o.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAll() {
        return ordersById.values().stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public synchronized boolean updateStatus(String orderId, OrderStatus newStatus) {
        Order order = ordersById.get(orderId);
        if (order != null && newStatus != null) {
            order.setStatus(newStatus);
            return true;
        }
        return false;
    }

    @Override
    public long count() {
        return ordersById.size();
    }
}
