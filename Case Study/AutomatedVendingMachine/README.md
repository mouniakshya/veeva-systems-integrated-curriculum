# Automated Vending Machine Using Java

## Project Description

The Automated Vending Machine is a simple Java-based application that simulates the working of a real vending machine.

It manages product details, prices, stock quantities, customer payments, product selection, and transaction cancellation. It also handles errors using user-defined exceptions.

The program is implemented in a single Java file using basic OOP concepts and Java Collections.

## Features

1. **Product Catalog**
   - Stores multiple products such as Soda, Chocolate, Candy, and Chips.
   - Maintains product prices.

2. **Inventory Management**
   - Tracks the available quantity of each product.
   - Updates stock after successful purchases.

3. **Payment Processing**
   - Supports cash and simulated UPI payments.
   - Calculates the total purchase amount and change.

4. **Product Selection and Dispensing**
   - Allows customers to select products and quantities.
   - Dispenses products after successful payment.

5. **Transaction Cancellation**
   - Allows customers to cancel before confirming the purchase.

6. **Exception Handling**
   - Handles insufficient funds.
   - Handles insufficient change.
   - Handles sold-out products.
   - Handles insufficient product quantity.

## Technologies Used

- Java
- Java Collections Framework
- HashMap
- Scanner
- Switch Statement
- Exception Handling

## Concepts Used

| Concept | Usage |
|---|---|
| Classes and Objects | Main class and exception classes |
| HashMap | Stores product prices and stock |
| Scanner | Accepts user input |
| Switch | Handles menu operations |
| Loops | Repeats menu operations |
| User-Defined Exceptions | Handles vending machine errors |
| Try-Catch | Handles exceptions |

## User-Defined Exceptions

| Exception | Description |
|---|---|
| InsufficientFundsException | Raised when the entered money is less than the total price |
| InsufficientChangeException | Raised when the machine cannot provide the required change |
| SoldOutException | Raised when the selected product is out of stock |
| InsufficientQuantityException | Raised when the requested quantity exceeds available stock |

## Menu Options

```text
===== VENDING MACHINE =====
1. Display Products
2. Buy Product
3. Cancel Transaction
4. Exit
```

## Sample Product Details

| Product | Price | Stock |
|---|---:|---:|
| Soda | ₹30 | 5 |
| Chocolate | ₹20 | 3 |
| Candy | ₹10 | 10 |
| Chips | ₹25 | 5 |

## How to Run

1. Install Java JDK.
2. Open IntelliJ IDEA or any Java IDE.
3. Create a Java project.
4. Create a file named `Main.java`.
5. Paste the program code.
6. Run the `Main` class.

Alternatively, using the terminal:

```bash
javac Main.java
java Main
```

## Sample Output

```text
===== VENDING MACHINE =====
1. Display Products
2. Buy Product
3. Cancel Transaction
4. Exit

Enter choice: 1

--- Product List ---
Soda | Price: Rs.30 | Stock: 5
Chocolate | Price: Rs.20 | Stock: 3
Candy | Price: Rs.10 | Stock: 10
Chips | Price: Rs.25 | Stock: 5
```

## Limitations

- UPI payment is simulated and is not connected to a real payment gateway.
- Currency is maintained as a simplified total balance rather than individual coin and note counts.
- Change availability is checked using the total machine balance, not exact denominations.
- Product information is stored in memory and is reset when the program is restarted.

## Conclusion

This project demonstrates how Java Collections, control statements, and user-defined exception handling can be used to develop a simple automated vending machine application. It provides practical understanding of inventory management, payment processing, product dispensing, and error handling.
