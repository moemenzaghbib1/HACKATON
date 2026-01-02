package drest.test.hackaton.application.port.in;

import java.util.List;

/**
 * Application-level command representing the intent:
 * "Create a new order"
 */
public record CreateOrderCommand(
        String customerName,
        List<ItemCommand> items
) {
    public record ItemCommand(String productId, int quantity) {}
}
