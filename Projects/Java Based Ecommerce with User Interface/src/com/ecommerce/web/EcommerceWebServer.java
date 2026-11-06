package com.ecommerce.web;

import com.ecommerce.model.discount.CustomerHistoryThresholdDiscountPolicy;
import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderStatus;
import com.ecommerce.model.payment.*;
import com.ecommerce.model.product.Product;
import com.ecommerce.model.subscription.SubscriptionTier;
import com.ecommerce.model.user.Address;
import com.ecommerce.model.user.Customer;
import com.ecommerce.repository.*;
import com.ecommerce.service.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * Built-in zero-dependency Java HTTP Web Server.
 * Serves the HTML/CSS/JS frontend interface and provides API routes.
 */
public class EcommerceWebServer {
    private static final int DEFAULT_PORT = 8088;

    private final CustomerService customerService;
    private final ProductCatalogService catalogService;
    private final AuthService authService;
    private final OrderService orderService;
    private final TrackingService trackingService;
    private final SubscriptionService subscriptionService;
    private final GiftCardRepository giftCardRepository;
    private final Path webRoot;

    public EcommerceWebServer(Path webRoot) {
        this.webRoot = webRoot;

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

        seedData();
    }

    private void seedData() {
        catalogService.addProduct(new Product("P101", "iPhone 15 Pro", "Apple flagship smartphone with A17 Pro (Natural Titanium 128GB)", 127990.00, 20, "Apple", "Electronics"));
        catalogService.addProduct(new Product("P102", "iPhone 14", "Previous generation Apple phone 128GB (Starlight)", 56999.00, 30, "Apple", "Electronics"));
        catalogService.addProduct(new Product("P103", "Galaxy S24 Ultra", "Samsung flagship AI smartphone with S-Pen (Titanium Gray 512GB)", 129999.00, 15, "Samsung", "Electronics"));
        catalogService.addProduct(new Product("P104", "Galaxy Book4", "Samsung thin & light laptop Intel Core Ultra 7", 119990.00, 10, "Samsung", "Computers"));
        catalogService.addProduct(new Product("P105", "Sony WH-1000XM5", "Industry-leading noise cancelling wireless headphones", 26990.00, 40, "Sony", "Audio"));
        catalogService.addProduct(new Product("P106", "Sony Bravia 55 OLED", "4K HDR Smart Google TV with Dolby Vision (KD-55X74L)", 54990.00, 8, "Sony", "Electronics"));
        catalogService.addProduct(new Product("P107", "MacBook Air M3", "13.6-inch lightweight Apple laptop (8GB/256GB)", 104900.00, 12, "Apple", "Computers"));
        catalogService.addProduct(new Product("P108", "Echo Dot 5th Gen", "Smart speaker with Alexa voice assistant and deep bass", 4499.00, 60, "Amazon", "Smart Home"));
        catalogService.addProduct(new Product("P109", "OnePlus 12 5G", "Flagship Snapdragon 8 Gen 3 phone (Flowy Emerald 16GB/512GB)", 64999.00, 35, "OnePlus", "Electronics"));
        catalogService.addProduct(new Product("P110", "boAt Airdopes 141", "ANC True Wireless Earbuds with 42H Playtime", 1499.00, 100, "boAt", "Audio"));

        Address addr1 = new Address("Flat 402, Block B, Connaught Place", "New Delhi", "Delhi", "110001", "India");
        customerService.registerCustomer("CUST-001", "Aarav Sharma", "aarav.sharma@gmail.com", "+91 98112 34567", "pass123", addr1);

        Address addr2 = new Address("74, 4th Cross, Indiranagar", "Bengaluru", "Karnataka", "560038", "India");
        customerService.registerCustomer("CUST-002", "Priya Patel", "priya.patel@gmail.com", "+91 98450 12345", "pass123", addr2);

        Address addr3 = new Address("12/A, Sea Face, Bandra West", "Mumbai", "Maharashtra", "400050", "India");
        customerService.registerCustomer("CUST-003", "Rohan Mehta", "rohan.mehta@yahoo.in", "+91 98200 98765", "pass123", addr3);

        giftCardRepository.save(new GiftCard("DIWALI500", "1234", 500.00, LocalDateTime.now().plusYears(1)));
        giftCardRepository.save(new GiftCard("FESTIVE2000", "5678", 2000.00, LocalDateTime.now().plusYears(1)));
        giftCardRepository.save(new GiftCard("MEGA5000", "9999", 5000.00, LocalDateTime.now().plusYears(1)));
    }

    public void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor()); // Java 22 Virtual Threads!

        // Static Files Handler
        server.createContext("/", new StaticFileHandler(webRoot));

        // REST API Handlers
        server.createContext("/api/products", new ProductsApiHandler(catalogService));
        server.createContext("/api/health", exchange -> {
            String resp = "{\"status\":\"UP\",\"engine\":\"Java 22 Virtual Threads\",\"module\":\"E-Commerce Platform\"}";
            sendJsonResponse(exchange, 200, resp);
        });

        server.start();
        System.out.println("================================================================================");
        System.out.println("       E-COMMERCE WEB APPLICATION SERVER RUNNING (CORE JAVA 22)                ");
        System.out.println("================================================================================");
        System.out.println(" Web UI URL  : http://localhost:" + port);
        System.out.println(" Serving From: " + webRoot.toAbsolutePath());
        System.out.println(" Press Ctrl+C to stop the server");
        System.out.println("================================================================================");
    }

    private static class StaticFileHandler implements HttpHandler {
        private final Path root;

        public StaticFileHandler(Path root) {
            this.root = root;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String uriPath = exchange.getRequestURI().getPath();
            if (uriPath.equals("/")) {
                uriPath = "/index.html";
            }

            Path resolved = root.resolve(uriPath.substring(1)).normalize();
            if (!resolved.startsWith(root) || !Files.exists(resolved) || Files.isDirectory(resolved)) {
                String notFound = "<html><body><h1>404 Not Found</h1></body></html>";
                byte[] bytes = notFound.getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(404, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
                return;
            }

            String mimeType = getMimeType(resolved.toString());
            exchange.getResponseHeaders().set("Content-Type", mimeType);
            exchange.getResponseHeaders().set("Cache-Control", "no-cache");
            byte[] fileBytes = Files.readAllBytes(resolved);
            exchange.sendResponseHeaders(200, fileBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(fileBytes);
            }
        }

        private String getMimeType(String path) {
            if (path.endsWith(".html")) return "text/html; charset=UTF-8";
            if (path.endsWith(".css")) return "text/css; charset=UTF-8";
            if (path.endsWith(".js")) return "application/javascript; charset=UTF-8";
            if (path.endsWith(".json")) return "application/json; charset=UTF-8";
            if (path.endsWith(".png")) return "image/png";
            if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
            if (path.endsWith(".svg")) return "image/svg+xml";
            return "text/plain";
        }
    }

    private static class ProductsApiHandler implements HttpHandler {
        private final ProductCatalogService catalog;

        public ProductsApiHandler(ProductCatalogService catalog) {
            this.catalog = catalog;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            List<Product> products = catalog.getAllProducts();
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                sb.append(String.format("{\"productId\":\"%s\",\"name\":\"%s\",\"description\":\"%s\",\"price\":%.2f,\"stockQuantity\":%d,\"companyName\":\"%s\",\"category\":\"%s\"}",
                        escapeJson(p.getProductId()),
                        escapeJson(p.getName()),
                        escapeJson(p.getDescription()),
                        p.getPrice(),
                        p.getStockQuantity(),
                        escapeJson(p.getCompanyName()),
                        escapeJson(p.getCategory())));
                if (i < products.size() - 1) sb.append(",");
            }
            sb.append("]");
            sendJsonResponse(exchange, 200, sb.toString());
        }

        private String escapeJson(String s) {
            if (s == null) return "";
            return s.replace("\\", "\\\\").replace("\"", "\\\"");
        }
    }

    private static void sendJsonResponse(HttpExchange exchange, int code, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        Path webPath = Paths.get("web");
        if (!Files.exists(webPath)) {
            webPath = Paths.get("C:/Users/MOUNI/.gemini/antigravity/scratch/ecommerce-product-module/web");
        }

        try {
            new EcommerceWebServer(webPath).start(port);
        } catch (IOException e) {
            System.err.println("Failed to start web server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
