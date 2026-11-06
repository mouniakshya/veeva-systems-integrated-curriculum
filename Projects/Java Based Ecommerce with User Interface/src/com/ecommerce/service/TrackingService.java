package com.ecommerce.service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderStatus;
import com.ecommerce.model.tracking.TrackingInfo;
import com.ecommerce.repository.OrderRepository;

import java.util.Objects;
import java.util.Optional;

/**
 * Service providing courier tracking visibility and status updates.
 * Satisfies Requirement 6.
 */
public class TrackingService {
    private final OrderRepository orderRepository;

    public TrackingService(OrderRepository orderRepository) {
        this.orderRepository = Objects.requireNonNull(orderRepository, "OrderRepository cannot be null");
    }

    public TrackingInfo getTrackingForOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        if (order.getTrackingInfo() == null) {
            throw new ResourceNotFoundException(
                    "Order " + orderId + " is currently in status '" + order.getStatus().getDescription() +
                    "' and has not yet been assigned to a courier agent for dispatch.");
        }
        return order.getTrackingInfo();
    }

    public Optional<TrackingInfo> findTrackingForOrder(String orderId) {
        return orderRepository.findById(orderId).map(Order::getTrackingInfo);
    }

    public void updateCourierCheckpoint(String orderId, OrderStatus status, String location, String checkpointMessage) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));

        TrackingInfo trackingInfo = order.getTrackingInfo();
        if (trackingInfo == null) {
            throw new ResourceNotFoundException("No tracking info exists for order " + orderId);
        }

        trackingInfo.updateStatus(status, location, checkpointMessage);
        order.setStatus(status);
        orderRepository.save(order);
    }
}
