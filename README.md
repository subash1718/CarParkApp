# 🚗 CarParkApp — Java 21 OOP Parking Management System

A fully object-oriented parking management application developed as part of the **OOP1 Assignment (MSc Software Design – Cloud Native Computing, 2025)**.

This project demonstrates both **fundamental and advanced features of Java 21**, including classes, inheritance, interfaces, records, sealed types, lambdas, varargs, defensive copying, and switch expressions.

---

## 📌 Features Overview

### 🎫 Vehicle & Ticket Management
- Supports **Cars** and **Motorcycles**
- Vehicles inherit from an abstract `Vehicle` class (polymorphism)
- Each vehicle gets a **Ticket** (Java `record`)
- Prevents duplicate parking (same registration number)

### 🅿️ Parking Lot Operations
- Park vehicle  
- List active vehicles  
- Remove vehicle and automatic payment  
- History of all tickets  
- Filter history by number of hours  
- Add notes using **varargs**

### 💳 Payment Handling
- `PaymentService` implements `Payable` interface
- Calculates fees using duration + vehicle type multiplier
- Returns:
  - `PaymentSuccess`
  - `PaymentFailure`
- Generates immutable receipt IDs

---

## 🌟 Java 21 Features Demonstrated

### ✔ Fundamentals
- Classes & methods  
- Encapsulation  
- Method overloading  
- `this()` vs `this.`  
- Interfaces  
- Inheritance & polymorphism  
- `super()` vs `super.`  
- Checked & unchecked exceptions  
- Enums  
- Arrays  
- Java Core APIs  
  - Strings  
  - ArrayList  
  - Date/Time (Instant, Duration)

### ✔ Advanced
- Call-by-value  
- **Defensive copying**  
- Interface default & static methods  
- **Records** → `Ticket`  
- **Custom immutable type** → `ImmutableReceipt`  
- **Lambdas** (`Predicate<T>`)  
- **Method references** → `System.out::println`  
- **Switch expressions**  
- **Sealed classes**  
- **Varargs** (`String... notes`)  
- **LVTI (`var`)**  
- Pattern matching where appropriate  

---
