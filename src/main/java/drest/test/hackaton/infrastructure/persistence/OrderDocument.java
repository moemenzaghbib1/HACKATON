package drest.test.hackaton.infrastructure.persistence;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;

@Document("orders")
public class OrderDocument {

    @Id
    private String id;
    private String customerName;
    private List<ItemDocument> items;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<ItemDocument> getItems() {
        return items;
    }

    public void setItems(List<ItemDocument> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public record ItemDocument(String productId, int quantity) {}
}
