import java.time.LocalDate;
import java.util.*;

/**
 * ============================================================================
 *                 SIMPLE REALISTIC E-COMMERCE PRODUCT MODULE
 *                 Core Java & Object-Oriented Programming (OOP)
 * ============================================================================
 * 
 * Demonstrates all 12 requirements in a clear, self-contained Core Java program:
 *  1. Customer Representation (Encapsulation, Abstraction, Address)
 *  2. Customer Pool Database (CRUD & Multi-query by Name, City, Phone, Email)
 *  3. Product Catalog Management (CRUD & 6 specific query modes)
 *  4. Customer Registration and Login Authentication
 *  5. Basket, Order Confirmation, and Admin Order Status Intervals
 *  6. Courier Tracking Details once Dispatched
 *  7. Polymorphic Payment Processing (UPI, Debit/Credit Card, COD)
 *  8. OOP Principles (Abstraction, Inheritance, Encapsulation, Polymorphism)
 *  9. Order Total Formula: Order Amount + Shipping Charge
 * 10. Gift Card Payment Option with PIN & Balance Validation
 * 11. Customer Membership Subscription (Monthly, Quarterly, Half-Yearly, Annually) -> ₹0 Shipping
 * 12. Multiple Orders Exceeding Threshold Discount Policy
 */
public class SimpleEcommerceApp {

    // ========================================================================
    // REQUIREMENT 1 & 8: Encapsulation, Abstraction, Inheritance
    // ========================================================================

    /**
     * Address Value Object (Encapsulated and Immutable)
     */
    public static class Address {
        private final String street;
        private final String city;
        private final String state;
        private final String pinCode;

        public Address(String street, String city, String state, String pinCode) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.pinCode = pinCode;
        }

        public String getStreet() { return street; }
        public String getCity() { return city; }
        public String getState() { return state; }
        public String getPinCode() { return pinCode; }

        @Override
        public String toString() {
            return street + ", " + city + ", " + state + " - " + pinCode;
        }
    }

    /**
     * Abstract User Class (Abstraction & Inheritance Root)
     */
    public static abstract class User {
        private final String id;
        private String name;
        private String email;
        private String phone;
        private String password;

        public User(String id, String name, String email, String phone, String password) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.password = password;
        }

        // Encapsulation: controlled getters and setters
        public String getId() { return id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public boolean checkPassword(String pwd) { return this.password != null && this.password.equals(pwd); }

        public abstract String getRole();
    }

    /**
     * Subscription Tier (Requirement 11)
     */
    public enum SubscriptionTier {
        MONTHLY(199.0, 30),
        QUARTERLY(499.0, 90),
        HALF_YEARLY(899.0, 180),
        ANNUALLY(1499.0, 365);

        private final double fee;
        private final int durationDays;

        SubscriptionTier(double fee, int durationDays) {
            this.fee = fee;
            this.durationDays = durationDays;
        }

        public double getFee() { return fee; }
        public int getDurationDays() { return durationDays; }
    }

    /**
     * Customer Representation (Inheritance & Encapsulation)
     */
    public static class Customer extends User {
        private Address address;
        private SubscriptionTier subscription;
        private LocalDate subscriptionExpiry;

        public Customer(String id, String name, String email, String phone, String password, Address address) {
            super(id, name, email, phone, password);
            this.address = address;
            this.subscription = null;
            this.subscriptionExpiry = null;
        }

        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }

        public SubscriptionTier getSubscription() { return subscription; }
        public boolean hasActiveSubscription() {
            return subscription != null && subscriptionExpiry != null && !LocalDate.now().isAfter(subscriptionExpiry);
        }

        public void subscribe(SubscriptionTier tier) {
            this.subscription = tier;
            this.subscriptionExpiry = LocalDate.now().plusDays(tier.getDurationDays());
        }

        @Override
        public String getRole() { return "CUSTOMER"; }

        @Override
        public String toString() {
            String subInfo = hasActiveSubscription() ? (" [Subscribed: " + subscription + "]") : " [No Subscription]";
            return "Customer#" + getId() + " (" + getName() + ", " + getEmail() + ", " + getPhone() + ", City: " + (address != null ? address.getCity() : "N/A") + subInfo + ")";
        }
    }

    // ========================================================================
    // REQUIREMENT 2: Customer Pool Database (CRUD & Multi-Query)
    // ========================================================================
    public static class CustomerDatabase {
        private final Map<String, Customer> customers = new HashMap<>();

        public void addCustomer(Customer customer) {
            customers.put(customer.getId(), customer);
        }

        public boolean deleteCustomer(String customerId) {
            return customers.remove(customerId) != null;
        }

        public Customer getCustomerById(String id) {
            return customers.get(id);
        }

        // Query by Name (case-insensitive partial match)
        public List<Customer> findByName(String name) {
            List<Customer> result = new ArrayList<>();
            for (Customer c : customers.values()) {
                if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                    result.add(c);
                }
            }
            return result;
        }

        // Query by City
        public List<Customer> findByCity(String city) {
            List<Customer> result = new ArrayList<>();
            for (Customer c : customers.values()) {
                if (c.getAddress() != null && c.getAddress().getCity().equalsIgnoreCase(city)) {
                    result.add(c);
                }
            }
            return result;
        }

        // Query by Phone
        public Customer findByPhone(String phone) {
            for (Customer c : customers.values()) {
                if (c.getPhone().replaceAll("\\s+", "").equals(phone.replaceAll("\\s+", ""))) {
                    return c;
                }
            }
            return null;
        }

        // Query by Email
        public Customer findByEmail(String email) {
            for (Customer c : customers.values()) {
                if (c.getEmail().equalsIgnoreCase(email.trim())) {
                    return c;
                }
            }
            return null;
        }

        // Requirement 4: Register & Login
        public Customer register(String id, String name, String email, String phone, String password, Address address) {
            if (findByEmail(email) != null) {
                throw new IllegalArgumentException("Customer with email " + email + " already exists!");
            }
            Customer customer = new Customer(id, name, email, phone, password, address);
            addCustomer(customer);
            return customer;
        }

        public Customer login(String email, String password) {
            Customer c = findByEmail(email);
            if (c != null && c.checkPassword(password)) {
                return c;
            }
            return null; // Login failed
        }

        public Collection<Customer> getAll() {
            return customers.values();
        }
    }

    // ========================================================================
    // REQUIREMENT 3: Product Catalog Management & 6 Specific Queries
    // ========================================================================
    public static class Product {
        private final String productId;
        private String name;
        private String companyName;
        private double price;
        private int stockQuantity;

        public Product(String productId, String name, String companyName, double price, int stockQuantity) {
            this.productId = productId;
            this.name = name;
            this.companyName = companyName;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }

        public String getProductId() { return productId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCompanyName() { return companyName; }
        public void setCompanyName(String companyName) { this.companyName = companyName; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        public int getStockQuantity() { return stockQuantity; }

        public synchronized void deductStock(int quantity) {
            if (stockQuantity < quantity) {
                throw new IllegalStateException("Insufficient stock for product " + name + ". Available: " + stockQuantity);
            }
            stockQuantity -= quantity;
        }

        public synchronized void restoreStock(int quantity) {
            stockQuantity += quantity;
        }

        @Override
        public String toString() {
            return "[" + productId + "] " + name + " (" + companyName + ") - Rs. " + String.format("%.2f", price) + " [Stock: " + stockQuantity + "]";
        }
    }

    public static class ProductCatalog {
        private final Map<String, Product> catalog = new LinkedHashMap<>();

        public void addProduct(Product product) {
            catalog.put(product.getProductId(), product);
        }

        public boolean deleteProduct(String productId) {
            return catalog.remove(productId) != null;
        }

        public void updatePrice(String productId, double newPrice) {
            Product p = catalog.get(productId);
            if (p != null) p.setPrice(newPrice);
        }

        // Query (i): By Product ID
        public Product getProductById(String id) {
            return catalog.get(id);
        }

        // Query (ii): By Product Name
        public List<Product> getProductsByName(String name) {
            List<Product> list = new ArrayList<>();
            for (Product p : catalog.values()) {
                if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                    list.add(p);
                }
            }
            return list;
        }

        // Query (iii): By Product Name and Price
        public List<Product> getProductsByNameAndPrice(String name, double price) {
            List<Product> list = new ArrayList<>();
            for (Product p : catalog.values()) {
                if (p.getName().toLowerCase().contains(name.toLowerCase()) && Math.abs(p.getPrice() - price) < 0.01) {
                    list.add(p);
                }
            }
            return list;
        }

        // Query (iv): By Product Name and Range of Price
        public List<Product> getProductsByNameAndPriceRange(String name, double minPrice, double maxPrice) {
            List<Product> list = new ArrayList<>();
            for (Product p : catalog.values()) {
                if (p.getName().toLowerCase().contains(name.toLowerCase()) && p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                    list.add(p);
                }
            }
            return list;
        }

        // Query (v): By Product Name and Company Name
        public List<Product> getProductsByNameAndCompany(String name, String companyName) {
            List<Product> list = new ArrayList<>();
            for (Product p : catalog.values()) {
                if (p.getName().toLowerCase().contains(name.toLowerCase()) && p.getCompanyName().equalsIgnoreCase(companyName)) {
                    list.add(p);
                }
            }
            return list;
        }

        // Query (vi): By Product Name, Company Name, and Price
        public List<Product> getProductsByNameCompanyAndPrice(String name, String companyName, double price) {
            List<Product> list = new ArrayList<>();
            for (Product p : catalog.values()) {
                if (p.getName().toLowerCase().contains(name.toLowerCase()) &&
                    p.getCompanyName().equalsIgnoreCase(companyName) &&
                    Math.abs(p.getPrice() - price) < 0.01) {
                    list.add(p);
                }
            }
            return list;
        }

        public Collection<Product> getAll() {
            return catalog.values();
        }
    }

    // ========================================================================
    // REQUIREMENT 7, 8 & 10: Polymorphic Payment Processing
    // ========================================================================
    public interface PaymentMethod {
        String getMethodName();
        boolean processPayment(double amount);
    }

    public static class UpiPayment implements PaymentMethod {
        private final String upiId;
        private final String app; // e.g. "GPay", "PhonePe", "Paytm"

        public UpiPayment(String upiId, String app) {
            this.upiId = upiId;
            this.app = app;
        }

        @Override
        public String getMethodName() { return "UPI (" + app + " - " + upiId + ")"; }

        @Override
        public boolean processPayment(double amount) {
            System.out.println("  [Payment-Gateway] Debiting Rs. " + String.format("%.2f", amount) + " via UPI (" + app + ") ID: " + upiId + "... SUCCESS");
            return true;
        }
    }

    public static class CardPayment implements PaymentMethod {
        private final String cardNumber;
        private final String cardHolder;
        private final String cardType; // "Debit Card" or "Credit Card"

        public CardPayment(String cardNumber, String cardHolder, String cardType) {
            this.cardNumber = cardNumber;
            this.cardHolder = cardHolder;
            this.cardType = cardType;
        }

        @Override
        public String getMethodName() {
            String masked = "****-****-****-" + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
            return cardType + " (" + masked + ", " + cardHolder + ")";
        }

        @Override
        public boolean processPayment(double amount) {
            System.out.println("  [Payment-Gateway] Authorizing Rs. " + String.format("%.2f", amount) + " via " + getMethodName() + "... 2FA Verified. SUCCESS");
            return true;
        }
    }

    public static class CodPayment implements PaymentMethod {
        private final String deliveryNotes;

        public CodPayment(String deliveryNotes) {
            this.deliveryNotes = deliveryNotes;
        }

        @Override
        public String getMethodName() { return "Cash on Delivery (COD)"; }

        @Override
        public boolean processPayment(double amount) {
            System.out.println("  [Payment-Gateway] COD Order Confirmed for Rs. " + String.format("%.2f", amount) + ". Note: " + deliveryNotes + ". SUCCESS");
            return true;
        }
    }

    /**
     * Requirement 10: Gift Card Payment
     */
    public static class GiftCard {
        private final String code;
        private final String pin;
        private double balance;

        public GiftCard(String code, String pin, double balance) {
            this.code = code;
            this.pin = pin;
            this.balance = balance;
        }

        public String getCode() { return code; }
        public double getBalance() { return balance; }

        public synchronized boolean redeem(String enteredPin, double amount) {
            if (!this.pin.equals(enteredPin)) {
                System.out.println("  [GiftCard Error] Invalid PIN for Gift Card " + code);
                return false;
            }
            if (this.balance < amount) {
                System.out.println("  [GiftCard Error] Insufficient balance! Available: Rs. " + balance + ", Needed: Rs. " + amount);
                return false;
            }
            this.balance -= amount;
            return true;
        }
    }

    public static class GiftCardPayment implements PaymentMethod {
        private final GiftCard giftCard;
        private final String enteredPin;

        public GiftCardPayment(GiftCard giftCard, String enteredPin) {
            this.giftCard = giftCard;
            this.enteredPin = enteredPin;
        }

        @Override
        public String getMethodName() { return "Gift Card Voucher (" + giftCard.getCode() + ")"; }

        @Override
        public boolean processPayment(double amount) {
            boolean success = giftCard.redeem(enteredPin, amount);
            if (success) {
                System.out.println("  [Payment-Gateway] Redeemed Rs. " + String.format("%.2f", amount) + " from Gift Card. Remaining Balance: Rs. " + String.format("%.2f", giftCard.getBalance()));
            }
            return success;
        }
    }

    // ========================================================================
    // REQUIREMENT 5 & 6: Shopping Basket, Order Intervals & Courier Tracking
    // ========================================================================
    public enum OrderStatus {
        CREATED_AND_RECEIVED("Order Just Created and Received"),
        DISPATCHED("Order Dispatched"),
        IN_TRANSIT("Order with Courier Agent and In Transit"),
        DELIVERED("Order Delivered to Customer"),
        RETURNED_AND_RECEIVED("Order Returned by Customer and Received");

        private final String description;
        OrderStatus(String description) { this.description = description; }
        public String getDescription() { return description; }
    }

    public static class CartItem {
        private final Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() { return product; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
        public double getTotalPrice() { return product.getPrice() * quantity; }
    }

    public static class ShoppingBasket {
        private final List<CartItem> items = new ArrayList<>();

        public void addItem(Product product, int quantity) {
            for (CartItem it : items) {
                if (it.getProduct().getProductId().equals(product.getProductId())) {
                    it.setQuantity(it.getQuantity() + quantity);
                    return;
                }
            }
            items.add(new CartItem(product, quantity));
        }

        public void removeItem(String productId) {
            items.removeIf(it -> it.getProduct().getProductId().equals(productId));
        }

        public List<CartItem> getItems() { return items; }

        public double calculateSubtotal() {
            double sum = 0.0;
            for (CartItem it : items) {
                sum += it.getTotalPrice();
            }
            return sum;
        }

        public void clear() { items.clear(); }
        public boolean isEmpty() { return items.isEmpty(); }
    }

    /**
     * Requirement 6: Tracking Details
     */
    public static class TrackingDetails {
        private final String trackingNumber;
        private final String courierPartner;
        private String currentHub;
        private String eta;
        private final List<String> checkpoints = new ArrayList<>();

        public TrackingDetails(String trackingNumber, String courierPartner, String initialHub, String eta) {
            this.trackingNumber = trackingNumber;
            this.courierPartner = courierPartner;
            this.currentHub = initialHub;
            this.eta = eta;
            addCheckpoint("Shipment label created and picked up at " + initialHub);
        }

        public void addCheckpoint(String checkpoint) {
            checkpoints.add(checkpoint);
        }

        public void updateLocation(String newHub, String checkpointText) {
            this.currentHub = newHub;
            addCheckpoint(checkpointText);
        }

        public void printTrackingReport() {
            System.out.println("  ================ COURIER LIVE TRACKING ================");
            System.out.println("  Tracking ID   : " + trackingNumber);
            System.out.println("  Courier Agent : " + courierPartner);
            System.out.println("  Current Hub   : " + currentHub);
            System.out.println("  Estimated ETA : " + eta);
            System.out.println("  Checkpoint History:");
            for (String cp : checkpoints) {
                System.out.println("   -> " + cp);
            }
            System.out.println("  =======================================================");
        }
    }

    /**
     * Requirement 5, 9, 11, 12: Order with Price Breakdown & Lifecycle
     */
    public static class Order {
        private final String orderId;
        private final Customer customer;
        private final List<CartItem> items;
        private final double orderAmount;
        private final double discountAmount;
        private final double shippingCharge;
        private final double totalAmount;
        private OrderStatus status;
        private final PaymentMethod paymentMethod;
        private TrackingDetails trackingDetails;

        public Order(String orderId, Customer customer, List<CartItem> items,
                     double orderAmount, double discountAmount, double shippingCharge,
                     PaymentMethod paymentMethod) {
            this.orderId = orderId;
            this.customer = customer;
            this.items = new ArrayList<>(items);
            this.orderAmount = orderAmount;
            this.discountAmount = discountAmount;
            this.shippingCharge = shippingCharge;
            // Requirement 9: order total = (order amount - discount) + shipping charge
            this.totalAmount = (orderAmount - discountAmount) + shippingCharge;
            this.status = OrderStatus.CREATED_AND_RECEIVED;
            this.paymentMethod = paymentMethod;
            this.trackingDetails = null;
        }

        public String getOrderId() { return orderId; }
        public Customer getCustomer() { return customer; }
        public List<CartItem> getItems() { return items; }
        public double getOrderAmount() { return orderAmount; }
        public double getDiscountAmount() { return discountAmount; }
        public double getShippingCharge() { return shippingCharge; }
        public double getTotalAmount() { return totalAmount; }
        public OrderStatus getStatus() { return status; }
        public PaymentMethod getPaymentMethod() { return paymentMethod; }
        public TrackingDetails getTrackingDetails() { return trackingDetails; }

        /**
         * Update status through intervals (Requirement 5 & 6)
         */
        public void setStatus(OrderStatus newStatus, ProductCatalog catalog) {
            this.status = newStatus;
            System.out.println("  [Admin Interval Update] Order " + orderId + " -> " + newStatus.getDescription());

            // When order is dispatched, initialize live courier tracking
            if (newStatus == OrderStatus.DISPATCHED && trackingDetails == null) {
                this.trackingDetails = new TrackingDetails(
                    "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                    "Delhivery Express (Delhi Air Hub)",
                    "Bilaspur Mega Hub, Haryana",
                    "Tomorrow 11:00 AM"
                );
            } else if (newStatus == OrderStatus.IN_TRANSIT && trackingDetails != null) {
                trackingDetails.updateLocation("Delhi Central Sortation Center, Okhla", "Out for transit with Delhi delivery courier executive");
            } else if (newStatus == OrderStatus.DELIVERED && trackingDetails != null) {
                trackingDetails.updateLocation("Connaught Place Delivery Center", "Delivered to customer at doorstep");
            } else if (newStatus == OrderStatus.RETURNED_AND_RECEIVED) {
                // Restock products into catalog
                System.out.println("  [Restock] Restoring inventory back to catalog for returned order " + orderId + ":");
                for (CartItem it : items) {
                    Product p = catalog.getProductById(it.getProduct().getProductId());
                    if (p != null) {
                        p.restoreStock(it.getQuantity());
                        System.out.println("    + Restocked " + it.getQuantity() + " unit(s) of " + p.getName() + " (New Stock: " + p.getStockQuantity() + ")");
                    }
                }
            }
        }

        public void printTaxInvoice() {
            System.out.println("  ==================== TAX INVOICE ====================");
            System.out.println("  Invoice ID    : " + orderId);
            System.out.println("  Customer      : " + customer.getName() + " (" + customer.getEmail() + ")");
            System.out.println("  Delivery City : " + (customer.getAddress() != null ? customer.getAddress().getCity() : "N/A"));
            System.out.println("  Payment Mode  : " + paymentMethod.getMethodName());
            System.out.println("  Order Status  : " + status.getDescription());
            System.out.println("  -----------------------------------------------------");
            for (CartItem it : items) {
                System.out.println(String.format("   %-22s x%d   Rs. %10.2f", it.getProduct().getName(), it.getQuantity(), it.getTotalPrice()));
            }
            System.out.println("  -----------------------------------------------------");
            System.out.println(String.format("  Items Subtotal           : Rs. %10.2f", orderAmount));
            if (discountAmount > 0) {
                System.out.println(String.format("  Loyalty Discount (Req 12): -Rs. %9.2f", discountAmount));
            }
            System.out.println(String.format("  Shipping Charge (Req 9)  : Rs. %10.2f %s", shippingCharge, (shippingCharge == 0 ? "(WAIVED - Prime Member)" : "")));
            System.out.println(String.format("  FINAL PAYABLE TOTAL      : Rs. %10.2f", totalAmount));
            System.out.println("  =====================================================");
        }
    }

    // ========================================================================
    // ORDER SERVICE (Handles Basket -> Order Confirmation, Threshold Discount, Shipping)
    // ========================================================================
    public static class OrderService {
        private final List<Order> orderDatabase = new ArrayList<>();
        private final ProductCatalog catalog;
        private static final double STANDARD_SHIPPING_FEE = 99.0;
        private static final double THRESHOLD_ORDER_VALUE = 5000.0; // Qualifying past order value
        private static final int QUALIFYING_ORDER_COUNT = 2;       // Need 2 past orders > 5000 for discount
        private static final double LOYALTY_DISCOUNT_PERCENT = 10.0; // 10% discount

        public OrderService(ProductCatalog catalog) {
            this.catalog = catalog;
        }

        /**
         * Check if customer qualifies for multiple order threshold discount (Requirement 12)
         */
        public boolean qualifiesForThresholdDiscount(Customer customer) {
            int highValueOrdersCount = 0;
            for (Order o : orderDatabase) {
                if (o.getCustomer().getId().equals(customer.getId()) && o.getOrderAmount() >= THRESHOLD_ORDER_VALUE) {
                    highValueOrdersCount++;
                }
            }
            return highValueOrdersCount >= QUALIFYING_ORDER_COUNT;
        }

        /**
         * Confirm and place order from Shopping Basket
         */
        public Order confirmOrder(Customer customer, ShoppingBasket basket, PaymentMethod paymentMethod) {
            if (basket.isEmpty()) {
                throw new IllegalStateException("Cannot create order from an empty basket!");
            }

            // Verify and deduct stock
            for (CartItem it : basket.getItems()) {
                Product p = catalog.getProductById(it.getProduct().getProductId());
                if (p == null || p.getStockQuantity() < it.getQuantity()) {
                    throw new IllegalStateException("Not enough stock for " + it.getProduct().getName());
                }
            }
            for (CartItem it : basket.getItems()) {
                Product p = catalog.getProductById(it.getProduct().getProductId());
                p.deductStock(it.getQuantity());
            }

            double subtotal = basket.calculateSubtotal();

            // Requirement 12: Check Multiple Order Threshold Discount
            double discount = 0.0;
            if (qualifiesForThresholdDiscount(customer)) {
                discount = (subtotal * LOYALTY_DISCOUNT_PERCENT) / 100.0;
                System.out.println("  [Discount Policy] Customer " + customer.getName() + " qualifies for " + LOYALTY_DISCOUNT_PERCENT + "% Loyalty Discount! Saved: Rs. " + String.format("%.2f", discount));
            }

            // Requirement 11 & 9: Shipping Charge (Free if subscribed)
            double shipping = STANDARD_SHIPPING_FEE;
            if (customer.hasActiveSubscription()) {
                shipping = 0.0; // 100% Free shipping for members!
                System.out.println("  [Membership Benefit] Active Prime Subscription detected! Shipping charge waived (Rs. 0.00).");
            }

            double totalPayable = (subtotal - discount) + shipping;

            // Execute payment
            boolean paid = paymentMethod.processPayment(totalPayable);
            if (!paid) {
                // Rollback stock if payment fails
                for (CartItem it : basket.getItems()) {
                    Product p = catalog.getProductById(it.getProduct().getProductId());
                    p.restoreStock(it.getQuantity());
                }
                throw new IllegalStateException("Payment failed for order! Rolled back catalog stock.");
            }

            // Generate Order
            String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Order order = new Order(orderId, customer, basket.getItems(), subtotal, discount, shipping, paymentMethod);
            orderDatabase.add(order);
            basket.clear(); // Empty basket after successful order confirmation
            return order;
        }

        public List<Order> getOrdersByCustomer(String customerId) {
            List<Order> list = new ArrayList<>();
            for (Order o : orderDatabase) {
                if (o.getCustomer().getId().equals(customerId)) {
                    list.add(o);
                }
            }
            return list;
        }

        public List<Order> getAllOrders() { return orderDatabase; }
    }

    // ========================================================================
    // MAIN DEMO SIMULATION (Runs and Verifies All 12 Requirements Step-by-Step)
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("           SIMPLE REALISTIC E-COMMERCE PRODUCT MODULE (CORE JAVA)               ");
        System.out.println("================================================================================\n");

        // --------------------------------------------------------------------
        // REQUIREMENT 1: Customer Representation (Encapsulation & Abstraction)
        // --------------------------------------------------------------------
        System.out.println("--- [1] REPRESENT CUSTOMERS (ENCAPSULATION & ABSTRACTION) ---");
        Address addr1 = new Address("Flat 402, Block B, Connaught Place", "New Delhi", "Delhi", "110001");
        Customer aarav = new Customer("CUST-001", "Aarav Sharma", "aarav@delhi.in", "+91 9811234567", "pass123", addr1);
        System.out.println("Created Customer: " + aarav);
        System.out.println("Encapsulated Address: " + aarav.getAddress());
        System.out.println("Role via Abstraction: " + aarav.getRole());
        System.out.println();

        // --------------------------------------------------------------------
        // REQUIREMENT 2: Customer Pool Database (CRUD & Multi-Query)
        // --------------------------------------------------------------------
        System.out.println("--- [2] CUSTOMER DATABASE POOL (CRUD & MULTI-QUERY) ---");
        CustomerDatabase customerDb = new CustomerDatabase();
        customerDb.addCustomer(aarav);

        Address addr2 = new Address("12, Indiranagar 100ft Road", "Bengaluru", "Karnataka", "560038");
        Customer priya = new Customer("CUST-002", "Priya Patel", "priya@bengaluru.in", "+91 9845012345", "pass456", addr2);
        customerDb.addCustomer(priya);

        Address addr3 = new Address("88, Bandra West Sea Face", "Mumbai", "Maharashtra", "400050");
        Customer rohan = new Customer("CUST-003", "Rohan Mehta", "rohan@mumbai.in", "+91 9820098765", "pass789", addr3);
        customerDb.addCustomer(rohan);

        System.out.println("Total Customers in Pool: " + customerDb.getAll().size());
        System.out.println("Query by Name ('Aarav')  : " + customerDb.findByName("Aarav"));
        System.out.println("Query by City ('Bengaluru'): " + customerDb.findByCity("Bengaluru"));
        System.out.println("Query by Phone ('+91 9820098765'): " + customerDb.findByPhone("+91 9820098765"));
        System.out.println("Query by Email ('priya@bengaluru.in'): " + customerDb.findByEmail("priya@bengaluru.in"));

        // Manipulation: update phone
        priya.setPhone("+91 9845099999");
        System.out.println("Manipulated Priya's Phone: " + customerDb.getCustomerById("CUST-002").getPhone());
        System.out.println();

        // --------------------------------------------------------------------
        // REQUIREMENT 3: Product Catalog Management & 6 Specific Queries
        // --------------------------------------------------------------------
        System.out.println("--- [3] PRODUCT CATALOG MANAGEMENT & 6 SPECIFIC QUERIES ---");
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new Product("P101", "iPhone 15 Pro", "Apple", 127990.00, 20));
        catalog.addProduct(new Product("P102", "iPhone 14", "Apple", 56999.00, 30));
        catalog.addProduct(new Product("P103", "Galaxy S24 Ultra", "Samsung", 129999.00, 15));
        catalog.addProduct(new Product("P104", "Galaxy Book4 Pro", "Samsung", 119990.00, 10));
        catalog.addProduct(new Product("P105", "Sony WH-1000XM5", "Sony", 26990.00, 40));
        catalog.addProduct(new Product("P106", "boAt Airdopes 141", "boAt", 1499.00, 100));

        System.out.println("Query (i)   By Product ID 'P101':\n   " + catalog.getProductById("P101"));
        System.out.println("Query (ii)  By Product Name 'iPhone':\n   " + catalog.getProductsByName("iPhone"));
        System.out.println("Query (iii) By Name 'iPhone 14' & Price Rs. 56,999:\n   " + catalog.getProductsByNameAndPrice("iPhone 14", 56999.00));
        System.out.println("Query (iv)  By Name 'Galaxy' & Price Range [1,00,000 to 1,35,000]:\n   " + catalog.getProductsByNameAndPriceRange("Galaxy", 100000.00, 135000.00));
        System.out.println("Query (v)   By Name 'Sony' & Company 'Sony':\n   " + catalog.getProductsByNameAndCompany("Sony", "Sony"));
        System.out.println("Query (vi)  By Name 'Sony WH-1000XM5', Company 'Sony' & Price Rs. 26,990:\n   " + catalog.getProductsByNameCompanyAndPrice("Sony WH-1000XM5", "Sony", 26990.00));
        System.out.println();

        // --------------------------------------------------------------------
        // REQUIREMENT 4: Customer Registration & Login Authentication
        // --------------------------------------------------------------------
        System.out.println("--- [4] CUSTOMER REGISTRATION & LOGIN ---");
        Address addr4 = new Address("55, Civil Lines", "New Delhi", "Delhi", "110054");
        Customer newCust = customerDb.register("CUST-004", "Virender Sehwag", "viru@delhi.in", "+91 9811000000", "viru123", addr4);
        System.out.println("Registered New Customer: " + newCust.getName());

        Customer loggedIn = customerDb.login("viru@delhi.in", "viru123");
        System.out.println("Login Success: " + (loggedIn != null ? "Welcome " + loggedIn.getName() + "!" : "Failed"));

        Customer failedLogin = customerDb.login("viru@delhi.in", "wrongpass");
        System.out.println("Login Failure Check (Wrong Password): " + (failedLogin == null ? "Rejected correctly (null)" : "Error"));
        System.out.println();

        // --------------------------------------------------------------------
        // REQUIREMENT 5, 7, 8, 9: Basket, Order Confirmation, Payment, Total Formula
        // --------------------------------------------------------------------
        System.out.println("--- [5, 7, 8, 9] BASKET, CONFIRM ORDER, POLYMORPHIC PAYMENT & TOTAL FORMULA ---");
        OrderService orderService = new OrderService(catalog);
        ShoppingBasket basket = new ShoppingBasket();
        basket.addItem(catalog.getProductById("P105"), 1); // Sony Headphones Rs. 26,990
        basket.addItem(catalog.getProductById("P106"), 1); // boAt Earbuds Rs. 1,499
        System.out.println("Added items to Aarav's Basket. Subtotal: Rs. " + basket.calculateSubtotal());

        // Polymorphic Payment Method 1: UPI
        PaymentMethod upi = new UpiPayment("aarav@oksbi", "GooglePay");
        Order order1 = orderService.confirmOrder(aarav, basket, upi);
        System.out.println("Order 1 Confirmed! Order ID: " + order1.getOrderId());
        order1.printTaxInvoice();
        System.out.println();

        // --------------------------------------------------------------------
        // REQUIREMENT 5 (Intervals) & 6: Courier Tracking Details
        // --------------------------------------------------------------------
        System.out.println("--- [5 & 6] ORDER INTERVAL PROGRESSION & COURIER TRACKING ---");
        System.out.println("Current Order Status: " + order1.getStatus().getDescription());

        // Admin updates status interval to DISPATCHED
        order1.setStatus(OrderStatus.DISPATCHED, catalog);
        System.out.println("Customer views live tracking once dispatched:");
        order1.getTrackingDetails().printTrackingReport();

        // Admin updates to IN_TRANSIT
        order1.setStatus(OrderStatus.IN_TRANSIT, catalog);
        order1.getTrackingDetails().printTrackingReport();

        // Admin updates to DELIVERED
        order1.setStatus(OrderStatus.DELIVERED, catalog);

        // Optional Return & Restocking demonstration
        System.out.println("\nSimulation of Customer Return interval:");
        int stockBeforeReturn = catalog.getProductById("P105").getStockQuantity();
        System.out.println("Catalog stock of Sony WH-1000XM5 before return: " + stockBeforeReturn);
        order1.setStatus(OrderStatus.RETURNED_AND_RECEIVED, catalog);
        System.out.println("Catalog stock of Sony WH-1000XM5 after return restock: " + catalog.getProductById("P105").getStockQuantity());
        System.out.println();

        // --------------------------------------------------------------------
        // REQUIREMENT 10: Gift Card Payment Option
        // --------------------------------------------------------------------
        System.out.println("--- [10] GIFT CARD PAYMENT OPTION ---");
        GiftCard diwaliCard = new GiftCard("DIWALI-2026", "4321", 60000.00);
        System.out.println("Issued Gift Card DIWALI-2026 with Balance: Rs. " + diwaliCard.getBalance());

        basket.addItem(catalog.getProductById("P102"), 1); // iPhone 14 Rs. 56,999
        PaymentMethod gcPayment = new GiftCardPayment(diwaliCard, "4321");
        Order order2 = orderService.confirmOrder(priya, basket, gcPayment);
        System.out.println("Gift Card Order Placed Successfully! Remaining Gift Card Balance: Rs. " + diwaliCard.getBalance());
        System.out.println();

        // --------------------------------------------------------------------
        // REQUIREMENT 11: Customer Membership Subscription (Rs. 0 Shipping)
        // --------------------------------------------------------------------
        System.out.println("--- [11] MEMBERSHIP SUBSCRIPTIONS & FREE SHIPPING ---");
        System.out.println("Aarav Sharma subscribing to ANNUAL VIP Prime Membership (Rs. 1,499)...");
        aarav.subscribe(SubscriptionTier.ANNUALLY);
        System.out.println("Has active subscription? " + aarav.hasActiveSubscription());

        basket.addItem(catalog.getProductById("P106"), 2); // boAt Earbuds Rs. 1499 x 2 = Rs. 2,998
        PaymentMethod cardPayment = new CardPayment("5123456789012345", "Aarav Sharma", "Debit Card");
        Order order3 = orderService.confirmOrder(aarav, basket, cardPayment);
        order3.printTaxInvoice(); // Shipping charge is Rs. 0.00!
        System.out.println();

        // --------------------------------------------------------------------
        // REQUIREMENT 12: Multiple Orders Exceeding Threshold Discount Policy
        // --------------------------------------------------------------------
        System.out.println("--- [12] MULTIPLE ORDERS EXCEEDING THRESHOLD DISCOUNT POLICY ---");
        System.out.println("Rule: If a customer has at least 2 previous orders >= Rs. 5,000, grant 10% discount on subsequent orders.");

        // Place qualifying order 1 for Rohan (> Rs. 5,000)
        basket.addItem(catalog.getProductById("P105"), 1); // Rs. 26,990
        Order rohanOrder1 = orderService.confirmOrder(rohan, basket, new UpiPayment("rohan@okaxis", "PhonePe"));
        System.out.println("Rohan Order 1 Total: Rs. " + rohanOrder1.getTotalAmount() + " (Qualifying Order #1 > Rs. 5,000)");

        // Place qualifying order 2 for Rohan (> Rs. 5,000)
        basket.addItem(catalog.getProductById("P105"), 1); // Rs. 26,990
        Order rohanOrder2 = orderService.confirmOrder(rohan, basket, new CodPayment("Leave with security guard"));
        System.out.println("Rohan Order 2 Total: Rs. " + rohanOrder2.getTotalAmount() + " (Qualifying Order #2 > Rs. 5,000)");

        // Now Rohan places Order 3 (iPhone 15 Pro Rs. 1,27,990) -> Qualifies for 10% discount!
        System.out.println("Does Rohan qualify for Multiple Order Threshold Discount? " + orderService.qualifiesForThresholdDiscount(rohan));
        basket.addItem(catalog.getProductById("P101"), 1); // iPhone 15 Pro Rs. 1,27,990
        Order rohanOrder3 = orderService.confirmOrder(rohan, basket, new CardPayment("4111222233334444", "Rohan Mehta", "Credit Card"));
        rohanOrder3.printTaxInvoice();

        System.out.println("\n================================================================================");
        System.out.println("          ALL 12 REQUIREMENTS SIMULATED AND VERIFIED SUCCESSFULLY!              ");
        System.out.println("================================================================================");
    }
}
