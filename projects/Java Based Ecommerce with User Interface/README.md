# E-Commerce Product Module (Core Java & OOP Principles)

A comprehensive, realistic E-Commerce Product Module built using pure Core Java and object-oriented design principles.

---

## 🏛️ Architecture & OOP Principles

This module was engineered to demonstrate the 4 pillars of Object-Oriented Programming (OOP) and clean architecture:

| OOP Principle | Implementation in Codebase |
|---|---|
| **Encapsulation** | Strict private fields with defensive validation, value object immutability (`Address`, `TrackingCheckpoint`), defensive copying of collections, domain invariants preserved inside entities. |
| **Abstraction** | Interfaces separating contract from implementation (`CustomerRepository`, `ProductRepository`, `OrderRepository`, `PaymentMethod`, `DiscountPolicy`). Abstract class `User` hiding platform user mechanics. |
| **Inheritance** | Class hierarchy extending `User` into `Customer` and `Admin`. Specialized policy implementations and enum-driven behaviors. |
| **Polymorphism** | Dynamic strategy dispatch for payments (`CardPayment`, `UpiPayment`, `CodPayment`, `GiftCardPayment`) and discount calculation policies (`CustomerHistoryThresholdDiscountPolicy`, `OrderThresholdDiscountPolicy`). |

---

## 📋 Requirements Mapping

| # | Requirement | Implementation |
|---|---|---|
| **1** | Customer representation (name, email, address, phone no) using encapsulation and abstraction | `com.ecommerce.model.user.Customer`, `User`, `Address` |
| **2** | Customer pool database: add, delete, update, and search by name, city, phone, email | `CustomerRepository`, `InMemoryCustomerRepository`, `CustomerService` |
| **3** | Product catalog CRUD + 6 Query Variants:<br>i. By ID<br>ii. By Name<br>iii. By Name & Price<br>iv. By Name & Price Range<br>v. By Name & Company<br>vi. By Name, Company & Price | `ProductRepository`, `InMemoryProductRepository`, `ProductCatalogService` |
| **4** | Customer register & login authentication | `AuthService`, `Customer`, `Admin` |
| **5** | Basket / cart, order confirmation, stored in database, admin intervals (`CREATED_AND_RECEIVED`, `DISPATCHED`, `IN_TRANSIT`, `DELIVERED`, `RETURNED_AND_RECEIVED` with inventory restocking) | `ShoppingCart`, `CartItem`, `Order`, `OrderStatus`, `OrderService` |
| **6** | Courier tracking visible to customer upon dispatch | `TrackingInfo`, `TrackingCheckpoint`, `TrackingService` |
| **7** | Polymorphic payments: UPI, Debit/Credit Card, COD | `PaymentMethod`, `UpiPayment`, `CardPayment`, `CodPayment` |
| **8** | OOP principles (Abstraction, Inheritance, Encapsulation, Polymorphism) | Applied across all layers and classes |
| **9** | Order Total = Order Amount + Shipping Charge | `Order` constructor & `OrderService.checkout()` |
| **10** | Gift card payment option | `GiftCard`, `GiftCardPayment`, `GiftCardRepository` |
| **11** | Customer subscription (Monthly, Quarterly, Half-Yearly, Annually) with $0 Free Shipping | `SubscriptionTier`, `CustomerSubscription`, `SubscriptionService` |
| **12** | Threshold / Volume discounts for multiple orders exceeding threshold value | `DiscountPolicy`, `CustomerHistoryThresholdDiscountPolicy` |

---

## 📁 Project Directory Structure

```text
ecommerce-product-module/
├── bin/                             # Compiled bytecode classes
├── src/
│   └── com/
│       └── ecommerce/
│           ├── ECommerceApp.java    # Interactive Console CLI Application
│           ├── demo/
│           │   └── EcommerceSimulationDemo.java  # Automated verification suite (Reqs 1-12)
│           ├── model/
│           │   ├── user/
│           │   │   ├── User.java                 # Abstract Base User
│           │   │   ├── Customer.java             # Customer entity
│           │   │   ├── Admin.java                # Admin entity
│           │   │   └── Address.java              # Immutable Address value object
│           │   ├── product/
│           │   │   └── Product.java              # Product entity
│           │   ├── cart/
│           │   │   ├── CartItem.java             # Individual cart item
│           │   │   └── ShoppingCart.java         # Basket logic
│           │   ├── order/
│           │   │   ├── Order.java                # Order aggregate root
│           │   │   ├── OrderItem.java            # Immutable historical snapshot
│           │   │   └── OrderStatus.java          # Lifecycle status enum
│           │   ├── tracking/
│           │   │   ├── TrackingInfo.java         # Courier tracking state
│           │   │   └── TrackingCheckpoint.java   # Milestone entry
│           │   ├── payment/
│           │   │   ├── PaymentMethod.java        # Strategy interface
│           │   │   ├── PaymentResult.java        # Receipt value object
│           │   │   ├── PaymentStatus.java        # Status enum
│           │   │   ├── UpiPayment.java           # UPI implementation
│           │   │   ├── CardPayment.java          # Credit/Debit Card implementation
│           │   │   ├── CardType.java             # Card type enum
│           │   │   ├── CodPayment.java           # Cash on Delivery
│           │   │   ├── GiftCard.java             # Gift card entity
│           │   │   └── GiftCardPayment.java      # Gift card payment implementation
│           │   ├── subscription/
│           │   │   ├── SubscriptionTier.java     # Monthly, Quarterly, Half-Yearly, Annually
│           │   │   └── CustomerSubscription.java # Active subscription entity
│           │   └── discount/
│           │       ├── DiscountPolicy.java       # Strategy interface
│           │       ├── OrderThresholdDiscountPolicy.java
│           │       └── CustomerHistoryThresholdDiscountPolicy.java
│           ├── repository/
│           │   ├── CustomerRepository.java
│           │   ├── InMemoryCustomerRepository.java
│           │   ├── ProductRepository.java
│           │   ├── InMemoryProductRepository.java
│           │   ├── OrderRepository.java
│           │   ├── InMemoryOrderRepository.java
│           │   ├── GiftCardRepository.java
│           │   └── InMemoryGiftCardRepository.java
│           ├── service/
│           │   ├── AuthService.java
│           │   ├── CustomerService.java
│           │   ├── ProductCatalogService.java
│           │   ├── OrderService.java
│           │   ├── SubscriptionService.java
│           │   └── TrackingService.java
│           └── exception/
│               ├── AuthenticationException.java
│               ├── InsufficientStockException.java
│               ├── InvalidPaymentException.java
│               ├── ResourceNotFoundException.java
│               └── ValidationException.java
├── run-demo.bat                     # Windows batch script for automated demo
├── run-demo.ps1                     # PowerShell script for automated demo
├── run-app.bat                      # Windows batch script for interactive CLI
├── run-app.ps1                      # PowerShell script for interactive CLI
└── README.md
```

---

## 🚀 Running the Project

### Option 1: Modern HTML/CSS Web Application (Interactive Browser Interface)
```powershell
.\run-web.ps1
```
*(or double-click `run-web.bat` in Windows Explorer)*
This launches the built-in Java 22 HTTP server and automatically opens **http://localhost:8080** in your web browser.

You can also view the standalone web interface directly by opening `web/index.html` in any browser!

### Option 2: Automated Simulation Suite (Verifies all 12 Requirements)
```powershell
.\run-demo.ps1
```
or via command line:
```cmd
java -ea -cp bin com.ecommerce.demo.EcommerceSimulationDemo
```

### Option 3: Interactive Console Platform (CLI)
```powershell
.\run-app.ps1
```
or via command line:
```cmd
java -cp bin com.ecommerce.ECommerceApp
```
