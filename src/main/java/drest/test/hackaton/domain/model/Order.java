package drest.test.hackaton.domain.model;

import java.util.List;
import java.util.UUID;

public class Order {

    private String id;
    private String customerName;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String customerName, List<OrderItem> items) {
        this.id = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.items = items;
        this.status = OrderStatus.CREATED;
    }

    public void markAsPaid() {
        if (status != OrderStatus.CREATED) {
            throw new IllegalStateException("Order cannot be paid in current status");
        }
        status = OrderStatus.PAID;
    }

    public void cancel() {
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("Paid orders cannot be cancelled");
        }
        status = OrderStatus.CANCELLED;
    }

    public String getId() { return id; }
    public String getCustomerName() { return customerName; }
    public List<OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }
}
