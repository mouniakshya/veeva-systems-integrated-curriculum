# Inventory and Sales Management System

A console-based Java application for managing companies, products, inventory, sales, and transaction history.

## Features

- Add, update, view, and delete companies
- Add, update, view, and delete products
- Search for products by ID or name
- Track product stock levels
- Restock inventory
- Detect low-stock and out-of-stock products
- Process multi-product sales
- Record and view transaction history
- Validate duplicate IDs, invalid quantities, insufficient stock, and missing references
- Perform monetary calculations with `BigDecimal`

## Project Structure

```text
InventoryandSalesManagementSystem/
├── Main.java
├── InventorySystem.java
├── Company.java
├── Product.java
├── Transaction.java
└── TransactionItem.java
