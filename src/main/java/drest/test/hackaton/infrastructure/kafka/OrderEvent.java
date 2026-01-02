package drest.test.hackaton.infrastructure.kafka;

public class OrderEvent {

    private String type;
    private String orderId;

    public OrderEvent() {}

    public OrderEvent(String type, String orderId) {
        this.type = type;
        this.orderId = orderId;
    }

    public String getType() { return type; }
    public String getOrderId() { return orderId; }

    public void setType(String type) { this.type = type; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
}
