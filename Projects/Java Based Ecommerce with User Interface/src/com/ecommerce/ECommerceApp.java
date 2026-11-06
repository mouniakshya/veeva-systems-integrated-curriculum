package com.ecommerce;

import com.ecommerce.model.cart.CartItem;
import com.ecommerce.model.cart.ShoppingCart;
import com.ecommerce.model.discount.CustomerHistoryThresholdDiscountPolicy;
import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderStatus;
import com.ecommerce.model.payment.*;
import com.ecommerce.model.product.Product;
import com.ecommerce.model.subscription.SubscriptionTier;
import com.ecommerce.model.tracking.TrackingInfo;
import com.ecommerce.model.user.Address;
import com.ecommerce.model.user.Customer;
import com.ecommerce.model.user.User;
import com.ecommerce.repository.*;
import com.ecommerce.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Interactive Console Application for the E-Commerce Platform.
 * Demonstrates all Core Java OOP principles and system requirements.
 */
public class ECommerceApp {
    private final CustomerService customerService;
    private final ProductCatalogService catalogService;
    private final AuthService authService;
    private final OrderService orderService;
    private final TrackingService trackingService;
    private final SubscriptionService subscriptionService;
    private final GiftCardRepository giftCardRepository;
    private final Scanner scanner;

    public ECommerceApp() {
        CustomerRepository customerRepo = new InMemoryCustomerRepository();
        ProductRepository productRepo = new InMemoryProductRepository();
        OrderRepository orderRepo = new InMemoryOrderRepository();
        this.giftCardRepository = new InMemoryGiftCardRepository();

        this.customerService = new CustomerService(customerRepo);
        this.catalogService = new ProductCatalogService(productRepo);
        this.authService = new AuthService(customerRepo);
        this.orderService = new OrderService(orderRepo, productRepo, customerRepo,
                new CustomerHistoryThresholdDiscountPolicy(2, 5000.0, 15.0));
        this.trackingService = new TrackingService(orderRepo);
        this.subscriptionService = new SubscriptionService(customerRepo);
        this.scanner = new Scanner(System.in);

        seedSampleData();
    }

    private void seedSampleData() {
        // Sample Products (Indian Market with INR ₹)
        catalogService.addProduct(new Product("P101", "iPhone 15 Pro", "Flagship Apple smartphone with A17 Pro (Natural Titanium)", 127990.00, 20, "Apple", "Electronics"));
        catalogService.addProduct(new Product("P102", "iPhone 14", "Previous gen Apple phone 128GB (Starlight)", 56999.00, 30, "Apple", "Electronics"));
        catalogService.addProduct(new Product("P103", "Galaxy S24 Ultra", "Samsung flagship AI phone with S-Pen (Titanium Gray)", 129999.00, 15, "Samsung", "Electronics"));
        catalogService.addProduct(new Product("P104", "Sony WH-1000XM5", "Noise cancelling wireless headphones (Black)", 26990.00, 25, "Sony", "Audio"));
        catalogService.addProduct(new Product("P105", "MacBook Air M3", "13.6-inch lightweight laptop (8GB/256GB Midnight)", 104900.00, 10, "Apple", "Computers"));
        catalogService.addProduct(new Product("P106", "Echo Dot 5th Gen", "Alexa Smart Speaker with Deep Bass", 4499.00, 50, "Amazon", "Smart Home"));
        catalogService.addProduct(new Product("P107", "OnePlus 12 5G", "Snapdragon 8 Gen 3 Flagship (Flowy Emerald 16GB/512GB)", 64999.00, 25, "OnePlus", "Electronics"));
        catalogService.addProduct(new Product("P108", "boAt Airdopes 141", "ANC True Wireless Earbuds 42H Playtime", 1499.00, 100, "boAt", "Audio"));

        // Sample Customer (Delhi)
        Address addr = new Address("Flat 402, Block B, Connaught Place", "New Delhi", "Delhi", "110001", "India");
        customerService.registerCustomer("CUST-100", "Aarav Sharma", "aarav@test.in", "+91 98112 34567", "pass123", addr);

        // Sample Gift Cards (Indian Festive Vouchers)
        giftCardRepository.save(new GiftCard("DIWALI500", "1234", 500.00, LocalDateTime.now().plusYears(1)));
        giftCardRepository.save(new GiftCard("FESTIVE2000", "5678", 2000.00, LocalDateTime.now().plusYears(1)));
        giftCardRepository.save(new GiftCard("MEGA5000", "9999", 5000.00, LocalDateTime.now().plusYears(1)));
    }

    public void start() {
        System.out.println("===============================================================");
        System.out.println("     WELCOME TO BHARATMART INDIA E-COMMERCE PLATFORM (JAVA OOP) ");
        System.out.println("===============================================================");

        boolean running = true;
        while (running) {
            Optional<User> currentUser = authService.getCurrentLoggedInUser();
            if (currentUser.isEmpty()) {
                running = showGuestMenu();
            } else if (authService.isAdminLoggedIn()) {
                running = showAdminMenu();
            } else {
                running = showCustomerMenu();
            }
        }
        System.out.println("Thank you for using the E-Commerce Platform. Goodbye!");
    }

    private boolean showGuestMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Login (Customer / Admin)");
        System.out.println("2. Register New Customer");
        System.out.println("3. Quick Demo Login (Customer: aarav@test.in / pass123 - Delhi)");
        System.out.println("4. Quick Demo Login (Admin: admin@ecommerce.com / admin123)");
        System.out.println("5. Exit");
        System.out.print("Select an option (1-5): ");

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                handleLogin();
                break;
            case "2":
                handleCustomerRegistration();
                break;
            case "3":
                try {
                    authService.login("aarav@test.in", "pass123");
                    System.out.println("Logged in as Aarav Sharma (New Delhi)!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "4":
                try {
                    authService.login("admin@ecommerce.com", "admin123");
                    System.out.println("Logged in as Admin!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "5":
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        return true;
    }

    private boolean showCustomerMenu() {
        Customer customer = authService.getLoggedInCustomer();
        System.out.println("\n===============================================================");
        System.out.println("CUSTOMER DASHBOARD | User: " + customer.getName() + " (" + customer.getEmail() + ")");
        System.out.println("Delivery Location: " + customer.getAddress().getCity() + ", " + customer.getAddress().getState());
        System.out.println("Membership: " + (customer.hasActiveSubscription() ? customer.getSubscription().get().getTier().getDisplayName() + " [FREE EXPRESS DELIVERY]" : "Standard (Delivery Fee: ₹99.00)"));
        System.out.println("===============================================================");
        System.out.println("1. Browse & Search Product Catalog (6 Query Modes)");
        System.out.println("2. View Shopping Cart / Basket");
        System.out.println("3. Add Product to Basket");
        System.out.println("4. Checkout & Place Order (UPI / Card / COD / Gift Card)");
        System.out.println("5. View My Order History");
        System.out.println("6. Track Order via Courier Agent");
        System.out.println("7. Manage Subscription (Get Free Shipping)");
        System.out.println("8. View / Update Profile & Address");
        System.out.println("9. Logout");
        System.out.print("Select an option (1-9): ");

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                handleCatalogSearch();
                break;
            case "2":
                showCart(customer.getId());
                break;
            case "3":
                handleAddToCart(customer.getId());
                break;
            case "4":
                handleCheckout(customer);
                break;
            case "5":
                handleViewOrders(customer.getId());
                break;
            case "6":
                handleTrackOrder();
                break;
            case "7":
                handleSubscription(customer);
                break;
            case "8":
                handleProfile(customer);
                break;
            case "9":
                authService.logout();
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid option.");
        }
        return true;
    }

    private boolean showAdminMenu() {
        System.out.println("\n===============================================================");
        System.out.println("ADMINISTRATOR CONSOLE | Role: System Admin");
        System.out.println("===============================================================");
        System.out.println("1. View All Products");
        System.out.println("2. Add New Product");
        System.out.println("3. Update Product Stock / Price");
        System.out.println("4. Delete Product");
        System.out.println("5. View All Customer Orders");
        System.out.println("6. Update Order Status (Intervals: Dispatched -> In Transit -> Delivered -> Returned)");
        System.out.println("7. View All Registered Customers");
        System.out.println("8. Issue New Gift Card");
        System.out.println("9. Logout");
        System.out.print("Select an option (1-9): ");

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
                showAllProducts();
                break;
            case "2":
                handleAdminAddProduct();
                break;
            case "3":
                handleAdminUpdateProduct();
                break;
            case "4":
                handleAdminDeleteProduct();
                break;
            case "5":
                handleAdminViewOrders();
                break;
            case "6":
                handleAdminProgressOrder();
                break;
            case "7":
                handleAdminViewCustomers();
                break;
            case "8":
                handleAdminIssueGiftCard();
                break;
            case "9":
                authService.logout();
                System.out.println("Admin logged out.");
                break;
            default:
                System.out.println("Invalid option.");
        }
        return true;
    }

    // ==================== Customer Handlers ====================

    private void handleCatalogSearch() {
        System.out.println("\n--- PRODUCT SEARCH (Requirement 3) ---");
        System.out.println("1. List All Products");
        System.out.println("2. Query (i): By Product ID");
        System.out.println("3. Query (ii): By Product Name");
        System.out.println("4. Query (iii): By Product Name and Exact Price");
        System.out.println("5. Query (iv): By Product Name and Price Range");
        System.out.println("6. Query (v): By Product Name and Company");
        System.out.println("7. Query (vi): By Product Name, Company, and Price");
        System.out.print("Choose query mode (1-7): ");

        String mode = scanner.nextLine().trim();
        switch (mode) {
            case "1":
                showProducts(catalogService.getAllProducts());
                break;
            case "2":
                System.out.print("Enter Product ID: ");
                String pid = scanner.nextLine().trim();
                Optional<Product> prod = catalogService.getProductById(pid);
                prod.ifPresentOrElse(p -> showProducts(List.of(p)),
                        () -> System.out.println("No product found with ID: " + pid));
                break;
            case "3":
                System.out.print("Enter Product Name: ");
                String name = scanner.nextLine().trim();
                showProducts(catalogService.getProductsByName(name));
                break;
            case "4":
                System.out.print("Enter Product Name: ");
                String n4 = scanner.nextLine().trim();
                System.out.print("Enter Exact Price: ");
                double p4 = Double.parseDouble(scanner.nextLine().trim());
                showProducts(catalogService.getProductsByNameAndPrice(n4, p4));
                break;
            case "5":
                System.out.print("Enter Product Name: ");
                String n5 = scanner.nextLine().trim();
                System.out.print("Enter Min Price: ");
                double min = Double.parseDouble(scanner.nextLine().trim());
                System.out.print("Enter Max Price: ");
                double max = Double.parseDouble(scanner.nextLine().trim());
                showProducts(catalogService.getProductsByNameAndPriceRange(n5, min, max));
                break;
            case "6":
                System.out.print("Enter Product Name: ");
                String n6 = scanner.nextLine().trim();
                System.out.print("Enter Company Name: ");
                String c6 = scanner.nextLine().trim();
                showProducts(catalogService.getProductsByNameAndCompany(n6, c6));
                break;
            case "7":
                System.out.print("Enter Product Name: ");
                String n7 = scanner.nextLine().trim();
                System.out.print("Enter Company Name: ");
                String c7 = scanner.nextLine().trim();
                System.out.print("Enter Exact Price: ");
                double p7 = Double.parseDouble(scanner.nextLine().trim());
                showProducts(catalogService.getProductsByNameCompanyAndPrice(n7, c7, p7));
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    private void showProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products found matching criteria.");
            return;
        }
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-25s %-12s %-14s %-8s %-15s\n", "ID", "Name", "Company", "Price (₹)", "Stock", "Category");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Product p : products) {
            System.out.printf("%-8s %-25s %-12s ₹%-13.2f %-8d %-15s\n",
                    p.getProductId(), truncate(p.getName(), 24), truncate(p.getCompanyName(), 11),
                    p.getPrice(), p.getStockQuantity(), p.getCategory());
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private void showCart(String customerId) {
        ShoppingCart cart = orderService.getCart(customerId);
        System.out.println("\n" + cart);
    }

    private void handleAddToCart(String customerId) {
        System.out.print("Enter Product ID to add: ");
        String pid = scanner.nextLine().trim();
        System.out.print("Enter Quantity: ");
        try {
            int qty = Integer.parseInt(scanner.nextLine().trim());
            orderService.addToCart(customerId, pid, qty);
            System.out.println("Added to cart successfully!");
        } catch (Exception e) {
            System.out.println("Failed to add to cart: " + e.getMessage());
        }
    }

    private void handleCheckout(Customer customer) {
        ShoppingCart cart = orderService.getCart(customer.getId());
        if (cart.isEmpty()) {
            System.out.println("Your basket is empty. Please add products first.");
            return;
        }

        System.out.println("\n--- ORDER CHECKOUT ---");
        System.out.println(cart);
        double subtotal = cart.calculateSubtotal();
        double shipping = customer.hasActiveSubscription() ? 0.0 : orderService.getStandardShippingFee();
        System.out.printf("Delivery Charge: ₹%.2f %s\n", shipping, customer.hasActiveSubscription() ? "(FREE via Prime Membership)" : "");
        System.out.printf("Estimated Total: ₹%.2f\n", subtotal + shipping);

        System.out.println("\nSelect Payment Method (Requirement 7 & 10):");
        System.out.println("1. UPI (GooglePay / PhonePe / Paytm / BHIM)");
        System.out.println("2. RuPay / Credit / Debit Card");
        System.out.println("3. Cash on Delivery (COD)");
        System.out.println("4. Festive Gift Card");
        System.out.print("Choose payment option (1-4): ");

        String pChoice = scanner.nextLine().trim();
        PaymentMethod paymentMethod = null;

        try {
            switch (pChoice) {
                case "1":
                    System.out.print("Enter UPI ID (e.g. name@oksbi or name@ybl): ");
                    String upi = scanner.nextLine().trim();
                    System.out.print("Enter UPI App Name (e.g. GooglePay, PhonePe): ");
                    String app = scanner.nextLine().trim();
                    paymentMethod = new UpiPayment(upi, app);
                    break;
                case "2":
                    System.out.print("Card Type (1 for Credit Card, 2 for RuPay/Debit Card): ");
                    CardType cType = "1".equals(scanner.nextLine().trim()) ? CardType.CREDIT_CARD : CardType.DEBIT_CARD;
                    System.out.print("Card Number (16 digits): ");
                    String cardNo = scanner.nextLine().trim();
                    System.out.print("Cardholder Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Expiry (MM/YY): ");
                    String exp = scanner.nextLine().trim();
                    System.out.print("CVV (3 digits): ");
                    String cvv = scanner.nextLine().trim();
                    paymentMethod = new CardPayment(cType, cardNo, name, exp, cvv);
                    break;
                case "3":
                    System.out.print("Delivery Instructions (Optional, e.g. Call before delivery): ");
                    String inst = scanner.nextLine().trim();
                    paymentMethod = new CodPayment(inst);
                    break;
                case "4":
                    System.out.print("Enter Gift Card Code (e.g. DIWALI500, FESTIVE2000, MEGA5000): ");
                    String code = scanner.nextLine().trim();
                    System.out.print("Enter 4-digit PIN: ");
                    String pin = scanner.nextLine().trim();
                    Optional<GiftCard> gc = giftCardRepository.findByCode(code);
                    if (gc.isEmpty()) {
                        System.out.println("Gift card not found!");
                        return;
                    }
                    paymentMethod = new GiftCardPayment(gc.get(), pin);
                    break;
                default:
                    System.out.println("Invalid payment choice.");
                    return;
            }

            Order order = orderService.checkout(customer.getId(), paymentMethod);
            System.out.println("\n" + order.getFormattedInvoice());
            System.out.println("\nOrder placed successfully! Order ID: " + order.getOrderId());
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

    private void handleViewOrders(String customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        if (orders.isEmpty()) {
            System.out.println("You have no past orders.");
            return;
        }

        System.out.println("\n--- YOUR ORDER HISTORY ---");
        for (Order o : orders) {
            System.out.printf("[%s] Total: $%.2f | Status: %s | Tracking: %s\n",
                    o.getOrderId(), o.getTotalAmount(), o.getStatus().getDescription(),
                    o.getTrackingInfo() != null ? o.getTrackingInfo().getTrackingNumber() : "Pending Dispatch");
        }
    }

    private void handleTrackOrder() {
        System.out.print("Enter Order ID to track: ");
        String oid = scanner.nextLine().trim();
        try {
            TrackingInfo info = trackingService.getTrackingForOrder(oid);
            System.out.println("\n" + info.getFormattedTrackingReport());
        } catch (Exception e) {
            System.out.println("Tracking error: " + e.getMessage());
        }
    }

    private void handleSubscription(Customer customer) {
        System.out.println("\n--- PLATFORM PRIME MEMBERSHIP SUBSCRIPTIONS (Requirement 11) ---");
        System.out.println("Enjoy 100% Free Express Shipping on all orders during your active membership!");
        System.out.println("1. Monthly     : ₹199.00  (30 days)");
        System.out.println("2. Quarterly   : ₹499.00  (90 days - Save ₹98)");
        System.out.println("3. Half-Yearly : ₹899.00  (180 days - Save ₹295)");
        System.out.println("4. Annually    : ₹1,499.00 (365 days - Best Value, Save ₹889)");
        System.out.print("Select tier to purchase (1-4) or 0 to back: ");

        String t = scanner.nextLine().trim();
        SubscriptionTier tier;
        switch (t) {
            case "1": tier = SubscriptionTier.MONTHLY; break;
            case "2": tier = SubscriptionTier.QUARTERLY; break;
            case "3": tier = SubscriptionTier.HALF_YEARLY; break;
            case "4": tier = SubscriptionTier.ANNUALLY; break;
            default: return;
        }

        PaymentMethod dummyPayment = new UpiPayment("member@upi", "AutoPay");
        try {
            subscriptionService.purchaseSubscription(customer, tier, dummyPayment);
            System.out.println("Prime Subscription activated! You now have FREE EXPRESS SHIPPING on all orders across India!");
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
    }

    private void handleProfile(Customer customer) {
        System.out.println("\n--- CUSTOMER PROFILE ---");
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Name       : " + customer.getName());
        System.out.println("Email      : " + customer.getEmail());
        System.out.println("Phone      : " + customer.getPhone());
        System.out.println("Address    : " + customer.getAddress().getFormattedAddress());
    }

    // ==================== Admin Handlers ====================

    private void showAllProducts() {
        showProducts(catalogService.getAllProducts());
    }

    private void handleAdminAddProduct() {
        try {
            System.out.print("Product ID (e.g. P110): ");
            String id = scanner.nextLine().trim();
            System.out.print("Product Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Description: ");
            String desc = scanner.nextLine().trim();
            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Stock Quantity: ");
            int qty = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Company / Brand: ");
            String company = scanner.nextLine().trim();
            System.out.print("Category: ");
            String cat = scanner.nextLine().trim();

            catalogService.addProduct(new Product(id, name, desc, price, qty, company, cat));
            System.out.println("Product added successfully to catalog!");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private void handleAdminUpdateProduct() {
        System.out.print("Enter Product ID to update: ");
        String id = scanner.nextLine().trim();
        Optional<Product> opt = catalogService.getProductById(id);
        if (opt.isEmpty()) {
            System.out.println("Product not found.");
            return;
        }
        Product p = opt.get();
        System.out.println("Current: " + p);
        System.out.print("New Price (or press ENTER to keep $" + p.getPrice() + "): ");
        String pr = scanner.nextLine().trim();
        if (!pr.isEmpty()) p.setPrice(Double.parseDouble(pr));

        System.out.print("Add Stock (+qty or press ENTER to skip): ");
        String st = scanner.nextLine().trim();
        if (!st.isEmpty()) p.addStock(Integer.parseInt(st));

        catalogService.updateProduct(p);
        System.out.println("Product updated: " + p);
    }

    private void handleAdminDeleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        String id = scanner.nextLine().trim();
        try {
            catalogService.deleteProduct(id);
            System.out.println("Product deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleAdminViewOrders() {
        List<Order> orders = orderService.getAllOrders();
        System.out.println("\n--- ALL PLATFORM ORDERS ---");
        for (Order o : orders) {
            System.out.printf("[%s] Cust: %s | Total: ₹%,.2f | Status: %s\n",
                    o.getOrderId(), o.getCustomer().getName(), o.getTotalAmount(), o.getStatus().getDescription());
        }
    }

    private void handleAdminProgressOrder() {
        System.out.print("Enter Order ID to progress: ");
        String oid = scanner.nextLine().trim();
        Order order;
        try {
            order = orderService.getOrderById(oid);
        } catch (Exception e) {
            System.out.println("Order not found: " + e.getMessage());
            return;
        }

        System.out.println("Current Status: " + order.getStatus().getDescription());
        System.out.println("Progress to Interval (Requirement 5):");
        System.out.println("1. Dispatch Order & Assign Courier Agent (DISPATCHED)");
        System.out.println("2. Order With Courier Agent & In Transit (IN_TRANSIT)");
        System.out.println("3. Order Delivered to Customer (DELIVERED)");
        System.out.println("4. Order Returned by Customer & Received (RETURNED_AND_RECEIVED)");
        System.out.print("Select interval (1-4): ");

        String step = scanner.nextLine().trim();
        try {
            switch (step) {
                case "1":
                    System.out.print("Courier Partner (e.g. Delhivery Express, Blue Dart, DTDC, India Post): ");
                    String cp = scanner.nextLine().trim();
                    System.out.print("Dispatch Fulfillment Hub (e.g. Delhi Central Hub, Bilaspur): ");
                    String hub = scanner.nextLine().trim();
                    orderService.markOrderDispatched(oid, cp, hub);
                    System.out.println("Order marked as DISPATCHED. Courier tracking initialized!");
                    break;
                case "2":
                    System.out.print("Current Transit Hub / City (e.g. Okhla Hub, New Delhi): ");
                    String thub = scanner.nextLine().trim();
                    System.out.print("Transit Status Message: ");
                    String msg = scanner.nextLine().trim();
                    orderService.markOrderInTransit(oid, thub, msg);
                    System.out.println("Order marked as IN_TRANSIT with live tracking update!");
                    break;
                case "3":
                    System.out.print("Delivery Confirmation Note: ");
                    String note = scanner.nextLine().trim();
                    orderService.markOrderDelivered(oid, note);
                    System.out.println("Order marked as DELIVERED!");
                    break;
                case "4":
                    System.out.print("Return Reason: ");
                    String rrn = scanner.nextLine().trim();
                    orderService.markOrderReturnedAndReceived(oid, rrn);
                    System.out.println("Order marked as RETURNED_AND_RECEIVED. Items restocked back into catalog!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Error updating order status: " + e.getMessage());
        }
    }

    private void handleAdminViewCustomers() {
        List<Customer> list = customerService.getAllCustomers();
        System.out.println("\n--- REGISTERED CUSTOMERS (Pool) ---");
        for (Customer c : list) {
            System.out.println(c + " | Address: " + c.getAddress().getFormattedAddress());
        }
    }

    private void handleAdminIssueGiftCard() {
        System.out.print("Gift Card Code (e.g. DIWALI1000): ");
        String code = scanner.nextLine().trim();
        System.out.print("4-digit PIN: ");
        String pin = scanner.nextLine().trim();
        System.out.print("Initial Amount (₹): ");
        double amt = Double.parseDouble(scanner.nextLine().trim());
        giftCardRepository.save(new GiftCard(code, pin, amt, LocalDateTime.now().plusYears(1)));
        System.out.println("Gift card issued successfully!");
    }

    // ==================== Registration / Login Handlers ====================

    private void handleCustomerRegistration() {
        try {
            System.out.println("\n--- NEW CUSTOMER REGISTRATION ---");
            System.out.print("Customer ID (e.g. CUST-201): ");
            String id = scanner.nextLine().trim();
            System.out.print("Full Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Email Address: ");
            String email = scanner.nextLine().trim();
            System.out.print("Phone Number: ");
            String phone = scanner.nextLine().trim();
            System.out.print("Password (min 4 chars): ");
            String pwd = scanner.nextLine().trim();

            System.out.println("-- Address Information --");
            System.out.print("Street: ");
            String street = scanner.nextLine().trim();
            System.out.print("City: ");
            String city = scanner.nextLine().trim();
            System.out.print("State: ");
            String state = scanner.nextLine().trim();
            System.out.print("Zip Code: ");
            String zip = scanner.nextLine().trim();
            System.out.print("Country: ");
            String country = scanner.nextLine().trim();

            Address addr = new Address(street, city, state, zip, country);
            Customer cust = authService.registerCustomer(id, name, email, phone, pwd, addr);
            System.out.println("Registered successfully! Welcome, " + cust.getName());
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private void handleLogin() {
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password: ");
        String pass = scanner.nextLine().trim();
        try {
            User user = authService.login(email, pass);
            System.out.println("Login successful! Logged in as: " + user.getName() + " (" + user.getRole() + ")");
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private String truncate(String text, int max) {
        if (text == null) return "";
        return text.length() <= max ? text : text.substring(0, max - 3) + "...";
    }

    public static void main(String[] args) {
        new ECommerceApp().start();
    }
}
