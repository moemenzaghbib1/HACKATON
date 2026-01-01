# 🏗️ Hackathon — Orders Backend (Hexagonal + Kafka + Mongo)

A clean, production-ready backend implementing:

- **Hexagonal Architecture (Ports & Adapters)**
- **Spring Boot (REST API)**
- **MongoDB (Persistence)**
- **Apache Kafka (Event Streaming)**

The goal: manage customer orders while emitting events so other systems can react (billing, notifications, analytics, etc.).

This project prioritizes: **architecture, clarity, and maintainability** over raw feature count.

---

## 📚 Table of Contents

1. Architecture
2. Tech Stack
3. Project Structure
4. Prerequisites
5. Running the Project
6. Configuration
7. API Reference
8. Kafka Events
9. MongoDB Data
10. Troubleshooting
11. Why Hexagonal Architecture
12. Next Improvements

---

## 🧭 Architecture

**Hexagonal (Ports & Adapters)** separates business logic from frameworks.

```
           ┌────────────┐
           │   REST     │  ← HTTP API (adapter)
           └─────┬──────┘
                 │
         ┌───────▼────────┐
         │   APPLICATION  │ ← use cases (ports)
         └───────┬────────┘
                 │
         ┌───────▼────────┐
         │    DOMAIN      │ ← pure business (no Spring)
         └───────┬────────┘
     ┌───────────┼────────────┐
     │           │            │
 Mongo Adapter   │      Kafka Adapter
(persistence)    │    (events publishing)
```

Benefits:

✔ easy to test  
✔ technology-agnostic  
✔ easier to extend  
✔ realistic enterprise design

---

## ⚙️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **MongoDB**
- **Apache Kafka**
- **Docker Compose**
- **JUnit**

---

## 🗂️ Project Structure

```
src/main/java/...
└── drest.test.hackaton
    ├── domain/           # business logic (framework-free)
    ├── application/      # ports + use cases
    ├── infrastructure/
    │     ├── rest/       # REST controllers (Spring)
    │     ├── persistence # Mongo adapters
    │     └── kafka       # Kafka adapters
    └── HackatonApplication.java
```

---

## 🧰 Prerequisites

Required:

- Docker Desktop
- Java 17+

Optional (for UI later):

- Node + Angular CLI

---

## 🚀 Running the Project (Recommended: Docker)

### 1️⃣ Start infrastructure (Kafka + Mongo)

From project root:

```bash
docker compose up -d
```

This starts:

- Kafka → `localhost:9092`
- MongoDB → `localhost:27017`

Check status:

```bash
docker compose ps
```

---

### 2️⃣ Start backend

From IDE: run **HackatonApplication**  
or via CLI:

```bash
mvn spring-boot:run
```

Backend runs at:

👉 http://localhost:8080

---

## 🌍 Running Without Docker (External Services)

Edit:

`src/main/resources/application.properties`

### Mongo Cloud (MongoDB Atlas)

```properties
spring.data.mongodb.uri=mongodb+srv://USER:PASS@cluster.mongodb.net/orders-db
```

### Kafka on another server

```properties
spring.kafka.bootstrap-servers=KAFKA_HOST:9092
spring.kafka.consumer.group-id=orders-group
```

Restart the app after changes.

---

## ⚙️ Configuration

Defaults:

```properties
spring.application.name=orders

# Mongo
spring.data.mongodb.uri=mongodb://localhost:27017/orders-db

# Kafka
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=orders-group
spring.kafka.listener.missing-topics-fatal=false
```

---

## 📡 API Reference

### ▶️ Create Order
`POST /orders`

```json
{
  "customerName": "Moemen",
  "items": [
    { "productId": "ABC", "quantity": 2 }
  ]
}
```

Response: **201 Created**

---

### 📜 List Orders
`GET /orders` → **200 OK**

---

### 🔁 Change Order Status
`PATCH /orders/{id}/status?status=PAID`

Response: **200 OK**

Statuses:

```
CREATED, PAID, CANCELLED
```

---

## 📬 Kafka Events

Topic:

```
orders.events
```

Example payload:

```json
{
  "type": "ORDER_CREATED",
  "orderId": "123",
  "timestamp": 123456789
}
```

Consumer log:

```
Received event: ORDER_CREATED
```

---

## 💾 MongoDB Data

Check saved orders:

```bash
docker exec -it mongo mongosh
```

Then:

```js
use orders-db
db.orders.find()
```

---

## 🧯 Troubleshooting

### Kafka connection refused

```
localhost:9092 could not be reached
```

➡ Start Docker:

```bash
docker compose up -d
```

---

### No consumer group id

```properties
spring.kafka.consumer.group-id=orders-group
```

---

### Mongo auth issues

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/orders-db
```

---

## 🧠 Why Hexagonal Architecture?

> Frameworks change — business rules shouldn’t.

Hexagonal ensures:

- domain independent from frameworks
- easy to test
- replaceable infrastructure
- scalable design

---