# Inventory Management System

A simple console-based Java application for tracking and managing inventory items and quantities.

## Features

- Add inventory items
- Remove inventory items
- Check item quantities
- Display all available items
- Prevent negative or invalid quantities
- Detect insufficient stock
- Automatically remove items when their quantity reaches zero

## Project Structure

```text
Inventory Managemnt System/
├── Inventory.java
└── InventoryManagement.java
```

- `Inventory.java` — Contains inventory operations and stores items in a `HashMap`.
- `InventoryManagement.java` — Provides the interactive console menu and handles user input.

## Technologies Used

- Java
- `java.util.HashMap`
- `java.util.Scanner`

## Requirements

- Java Development Kit (JDK) 8 or later
- A terminal or command prompt

## How to Run

Navigate to this directory and compile both Java files:

```bash
javac Inventory.java InventoryManagement.java
```

Start the application:

```bash
java InventoryManagement
```

## Menu Options

```text
===== INVENTORY MANAGEMENT SYSTEM =====
1. Add Item
2. Remove Item
3. Check Quantity
4. Display All Items
5. Exit
```

## Example Usage

```text
Enter your choice: 1
Enter item name: Pen
Enter quantity: 50
50 Pen added successfully.

Enter your choice: 3
Enter item name: Pen
Available quantity of Pen : 50
```

## Usage Notes

- Item names are case-sensitive.
- Quantities must be greater than zero.
- Removing more units than are available reports insufficient stock.
- HashMap iteration order is not guaranteed.

## Learning Objectives

This case study demonstrates:

- Java classes and objects
- HashMap collections
- Console input with `Scanner`
- Control flow using `switch` and `do-while`
- Basic inventory operations
- Input and stock validation
