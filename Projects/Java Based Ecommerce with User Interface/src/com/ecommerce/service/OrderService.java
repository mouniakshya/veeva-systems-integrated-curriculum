package com.ecommerce.service;

import com.ecommerce.exception.InsufficientStockException;
import com.ecommerce.exception.InvalidPaymentException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.exception.ValidationException;
import com.ecommerce.model.cart.CartItem;
import com.ecommerce.model.cart.ShoppingCart;
import com.ecommerce.model.discount.CustomerHistoryThresholdDiscountPolicy;
import com.ecommerce.model.discount.DiscountPolicy;
import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderItem;
import com.ecommerce.model.order.OrderStatus;
import com.ecommerce.model.payment.PaymentMethod;
import com.ecommerce.model.payment.PaymentResult;
import com.ecommerce.model.product.Product;
import com.ecommerce.model.tracking.TrackingInfo;
import com.ecommerce.model.user.Address;
import com.ecommerce.model.user.Customer;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Core order lifecycle management service.
 * Handles basket management, pricing, discounts, shipping, payments, and admin status intervals.
 * Satisfies Requirements 5, 6, 7, 8, 9, 10, 11, 12.
 */
public class OrderService {
    public static final double DEFAULT_SHIPPING_FEE = 99.0; // ₹99 standard shipping across India

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final Map<String, ShoppingCart> activeCarts = new ConcurrentHashMap<>();
    private DiscountPolicy discountPolicy;
    private double standardShippingFee = DEFAULT_SHIPPING_FEE;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        CustomerRepository customerRepository,
                        DiscountPolicy discountPolicy) {
        this.orderRepository = Objects.requireNonNull(orderRepository, "OrderRepository cannot be null");
        this.productRepository = Objects.requireNonNull(productRepository, "ProductRepository cannot be null");
        this.customerRepository = Objects.requireNonNull(customerRepository, "CustomerRepository cannot be null");
        this.discountPolicy = discountPolicy != null ? discountPolicy : new CustomerHistoryThresholdDiscountPolicy(2, 5000.0, 15.0);
    }

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        CustomerRepository customerRepository) {
        this(orderRepository, productRepository, customerRepository, new CustomerHistoryThresholdDiscountPolicy(2, 5000.0, 15.0));
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = Objects.requireNonNull(discountPolicy, "DiscountPolicy cannot be null");
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public void setStandardShippingFee(double standardShippingFee) {
        this.standardShippingFee = Math.max(0.0, standardShippingFee);
    }

    public double getStandardShippingFee() {
        return standardShippingFee;
    }

    // ==================== Shopping Cart / Basket Management ====================

    public ShoppingCart getCart(String customerId) {
        return activeCarts.computeIfAbsent(customerId, ShoppingCart::new);
    }

    public void addToCart(String customerId, String productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));

        ShoppingCart cart = getCart(customerId);
        cart.addItem(product, quantity);
    }

    public void updateCartItemQuantity(String customerId, String productId, int quantity) {
        ShoppingCart cart = getCart(customerId);
        cart.updateQuantity(productId, quantity);
    }

    public void removeFromCart(String customerId, String productId) {
        ShoppingCart cart = getCart(customerId);
        cart.removeItem(productId);
    }

    public void clearCart(String customerId) {
        ShoppingCart cart = activeCarts.get(customerId);
        if (cart != null) {
            cart.clear();
        }
    }

    // ==================== Checkout & Order Creation ====================

    public synchronized Order checkout(String customerId, PaymentMethod paymentMethod, Address customDeliveryAddress) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId));

        ShoppingCart cart = getCart(customerId);
        if (cart.isEmpty()) {
            throw new ValidationException("Cannot checkout: Shopping basket is empty");
        }

        // Verify stock for all items before placing order
        for (CartItem item : cart.getItems()) {
            Product currentProduct = productRepository.findById(item.getProduct().getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product no longer available: " + item.getProduct().getName()));
            if (!currentProduct.hasSufficientStock(item.getQuantity())) {
                throw new InsufficientStockException(String.format(
                        "Not enough stock for '%s'. Available: %d, Requested: %d",
                        currentProduct.getName(), currentProduct.getStockQuantity(), item.getQuantity()));
            }
        }

        // Build OrderItems
        List<OrderItem> orderItems = cart.getItems().stream()
                .map(ci -> new OrderItem(ci.getProduct(), ci.getQuantity()))
                .collect(Collectors.toList());

        double orderAmount = cart.calculateSubtotal();

        // Calculate discount using strategy policy (Requirement 12)
        List<Order> customerPastOrders = orderRepository.findByCustomerId(customerId);
        double discountAmount = discountPolicy.calculateDiscount(customer, orderAmount, customerPastOrders);

        // Requirement 11 & Requirement 9:
        // Shipping is FREE ($0.0) if customer has active subscription; otherwise standard shipping fee.
        double shippingCharge = customer.hasActiveSubscription() ? 0.0 : standardShippingFee;

        // Order Total = (orderAmount - discountAmount) + shippingCharge
        double netPayable = Math.max(0.0, (orderAmount - discountAmount) + shippingCharge);

        // Requirement 7 & 10: Polymorphic payment execution
        PaymentResult paymentResult = paymentMethod.pay(netPayable);
        if (!paymentResult.isSuccess()) {
            throw new InvalidPaymentException("Payment processing failed: " + paymentResult.getMessage());
        }

        // Deduct inventory
        for (CartItem item : cart.getItems()) {
            Product p = productRepository.findById(item.getProduct().getProductId()).get();
            p.deductStock(item.getQuantity());
            productRepository.update(p);
        }

        // Create Order with initial status CREATED_AND_RECEIVED (Requirement 5)
        String orderId = "ORD-" + System.currentTimeMillis() % 1000000 + "-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        Address deliveryAddress = customDeliveryAddress != null ? customDeliveryAddress : customer.getAddress();
        Order order = new Order(orderId, customer, orderItems, deliveryAddress, discountAmount, shippingCharge, paymentResult);

        // Save order to database pool
        orderRepository.save(order);

        // Clear shopping basket
        cart.clear();

        return order;
    }

    public Order checkout(String customerId, PaymentMethod paymentMethod) {
        return checkout(customerId, paymentMethod, null);
    }

    // ==================== Admin Order Progression Intervals (Requirement 5 & 6) ====================

    /**
     * Admin interval 1 -> 2: Mark order as Dispatched and assign courier tracking.
     */
    public synchronized Order markOrderDispatched(String orderId, String courierPartner, String hubLocation) {
        Order order = getOrderById(orderId);
        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new ValidationException("Cannot dispatch order currently in status: " + order.getStatus());
        }

        order.setStatus(OrderStatus.DISPATCHED);
        String trackingNo = "TRK-" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
        TrackingInfo trackingInfo = new TrackingInfo(trackingNo, courierPartner, hubLocation);
        order.setTrackingInfo(trackingInfo);

        orderRepository.save(order);
        return order;
    }

    /**
     * Admin interval 2 -> 3: Order with courier agent and in transit.
     */
    public synchronized Order markOrderInTransit(String orderId, String currentCityHub, String message) {
        Order order = getOrderById(orderId);
        if (order.getStatus() != OrderStatus.DISPATCHED && order.getStatus() != OrderStatus.IN_TRANSIT) {
            throw new ValidationException("Order must be dispatched before moving into transit. Current: " + order.getStatus());
        }

        order.setStatus(OrderStatus.IN_TRANSIT);
        if (order.getTrackingInfo() != null) {
            order.getTrackingInfo().updateStatus(OrderStatus.IN_TRANSIT, currentCityHub, message);
        }

        orderRepository.save(order);
        return order;
    }

    /**
     * Admin interval 3 -> 4: Order delivered to customer.
     */
    public synchronized Order markOrderDelivered(String orderId, String destinationMessage) {
        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.DELIVERED);
        if (order.getTrackingInfo() != null) {
            order.getTrackingInfo().updateStatus(OrderStatus.DELIVERED,
                    order.getShippingAddress().getCity(),
                    destinationMessage != null ? destinationMessage : "Package successfully delivered to customer");
        }

        orderRepository.save(order);
        return order;
    }

    /**
     * Admin interval 4 -> 5: Order returned by customer and received at warehouse.
     * Restocks items back into product catalog!
     */
    public synchronized Order markOrderReturnedAndReceived(String orderId, String returnReason) {
        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.RETURNED_AND_RECEIVED);
        if (order.getTrackingInfo() != null) {
            order.getTrackingInfo().updateStatus(OrderStatus.RETURNED_AND_RECEIVED,
                    "Central Warehouse",
                    "Return received and inspected: " + (returnReason != null ? returnReason : "Customer return"));
        }

        // Restock returned items into inventory
        for (OrderItem item : order.getItems()) {
            productRepository.findById(item.getProductId()).ifPresent(p -> {
                p.addStock(item.getQuantity());
                productRepository.update(p);
            });
        }

        orderRepository.save(order);
        return order;
    }

    // ==================== Queries ====================

    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with ID '" + orderId + "' not found"));
    }

    public List<Order> getOrdersByCustomer(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
}
