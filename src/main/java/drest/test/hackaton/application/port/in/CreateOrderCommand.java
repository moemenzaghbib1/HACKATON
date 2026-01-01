package drest.test.hackaton.application.port.in;

import java.util.List;

public record CreateOrderCommand(String customerName, List<ItemRequest> items) {

    public record ItemRequest(String productId, int quantity) {}
}
