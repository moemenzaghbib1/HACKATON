package drest.test.hackaton.domain.model;

import java.util.List;
import java.util.UUID;

/**
 * Domain entity — pure business logic
 * (independent of Spring, Mongo, Kafka, etc.)
 */
public class Order {

    private final String id;
    private final String customerName;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(String customerName, List<OrderItem> items) {

        // --- Domain validation rules ---
        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        boolean invalidItem = items.stream()
                .anyMatch(i -> i.getQuantity() <= 0);

        if (invalidItem) {
            throw new IllegalArgumentException("Item quantity must be greater than zero");
        }

        // --- Initialization ---
        this.id = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.items = items;
        this.status = OrderStatus.CREATED;
    }

    // --- Business behaviors ---
    public void markAsPaid() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Order cannot be paid from current status");
        }
        status = OrderStatus.PAID;
    }

    public void cancel() {
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("Paid orders cannot be cancelled");
        }
        status = OrderStatus.CANCELLED;
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getCustomerName() { return customerName; }
    public List<OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
}
