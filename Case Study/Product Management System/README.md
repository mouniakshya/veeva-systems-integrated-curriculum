# Product Management System

A Java console application for managing and analyzing products using collections, streams, sorting, searching, and priority queues.

## Features

- Add products with validation
- Display all stored products
- Sort products by:
  - Price in descending order
  - Price range
  - Price and manufacturing date
- Search products by:
  - Product name
  - Manufacturing date
  - Price
  - Name, company, and price
- Find the highest- or lowest-priced products
- Handle invalid inputs and product quantity exceptions

## Technology Used

- Java
- Java Collections Framework
- Java Streams
- Priority Queue
- IntelliJ IDEA

## Product Information

Each product contains:

- Product ID
- Name
- Price
- Manufacturing date
- Quantity
- Company name

## Validation Rules

A product can only be added when its quantity is at least `100`. If the quantity is lower than `100`, the application throws `InvalidProductException`.

## Project Structure

```text
Case Study/Product Management System/
├── Main.java
├── Product.java
├── ProductService.java
├── InvalidProductException.java
├── ProductManagementSystem.iml
└── .gitignore
```

## How to Run

### Prerequisites

- Java JDK 8 or later
- An IDE such as IntelliJ IDEA or VS Code
- Terminal or command-line access

### Using a Terminal

```bash
cd "Case Study/Product Management System"
javac *.java
java Main
```

### Using IntelliJ IDEA

1. Open the project directory in IntelliJ IDEA.
2. Open `Main.java`.
3. Run the `main` method.

## Application Menu

```text
===== PRODUCT MANAGEMENT SYSTEM =====
1. Add Product
2. Display All Products
3. Sort by Price (Descending)
4. Sort by Price Range
5. Sort by Price and Manufacturing Date
6. Search by Product Name
7. Search by Manufacturing Date
8. Search by Price
9. Search by Name, Company and Price
10. Top K Products
11. Least K Products
12. Exit
```

## Example Input

```text
Enter Product ID: 101
Enter Product Name: Laptop
Enter Price: 75000
Enter Manufacturing Date (yyyy-mm-dd): 2025-10-15
Enter Quantity: 150
Enter Company Name: TechCompany
```

Manufacturing dates must use the `yyyy-mm-dd` format.

## License

This project is part of the Veeva Systems Integrated Curriculum repository.
