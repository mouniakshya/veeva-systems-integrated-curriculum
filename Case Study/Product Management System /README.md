# Product Management System – Java

## 📌 Project Overview

The Product Management System is a menu-driven Java application that stores and manages product details using the Java Collections Framework.

It allows users to add products, validate product quantities, sort products based on different criteria, search for products, and retrieve the Top K and Least K products using PriorityQueue.

## ✨ Features

- **Add Products:** Store product details as objects in an ArrayList.
- **Quantity Validation:** Accept products only when the quantity in hand is at least 100.
- **Custom Exception:** Raise a user-defined exception when the quantity is less than 100.
- **Display Products:** Display all stored product details in a properly formatted table.
- **Sorting Operations:**
  - Sort by price in descending order.
  - Sort products within a given price range.
  - Sort by price and manufacturing date in descending order.
- **Search Operations:**
  - Search by product name.
  - Search by manufacturing date.
  - Search by price.
  - Search by product name, company name, and price.
- **Priority Queue Operations:**
  - Find Top K expensive products.
  - Find Least K inexpensive products.

## 🛠️ Technologies Used

- Java
- Java Collections Framework
- ArrayList
- Comparator
- Java Streams API
- PriorityQueue
- User-Defined Exceptions
- LocalDate

## 📂 Project Structure

```text
ProductManagementSystem/
│
├── src/
│   ├── Main.java
│   ├── Product.java
│   ├── ProductService.java
│   └── InvalidProductException.java
│
└── README.md
```

## 📋 Product Details

Each product contains the following attributes:

| Attribute | Description |
|---|---|
| Product ID | Unique product identifier |
| Product Name | Name of the product |
| Price | Product price |
| Manufacturing Date | Date of manufacturing |
| Quantity | Available quantity |
| Company Name | Product manufacturer |

## ⚙️ Requirements

- JDK 8 or above
- IntelliJ IDEA / Eclipse / VS Code

## ▶️ How to Run

1. Clone the repository:

```bash
git clone YOUR_REPOSITORY_URL
```

2. Open the project in IntelliJ IDEA.
3. Ensure all Java files are inside the `src` folder.
4. Open `Main.java`.
5. Run the `main()` method.
6. Select an option from the menu to perform the required operation.

## 📌 Sample Validation

If the entered product quantity is less than 100, the application raises a custom exception and does not store the product.

```text
Enter Quantity: 50

Exception: Quantity is less than 100.
Product cannot be stored!
```

## 🎯 Learning Outcomes

- Understanding classes and objects in Java.
- Working with collections to manage multiple objects.
- Implementing custom exceptions.
- Sorting and filtering objects using Comparator and Streams.
- Understanding PriorityQueue and its applications.
- Performing menu-driven operations using Java.

## 👩‍💻 Author

Developed as a Core Java practice project to demonstrate Object-Oriented Programming and the Java Collections Framework.

---

**Note:** Product details are stored in an ArrayList during program execution. Data is not permanently stored in a database or file.
