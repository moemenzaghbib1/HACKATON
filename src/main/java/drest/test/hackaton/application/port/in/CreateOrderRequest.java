package drest.test.hackaton.application.port.in;

import java.util.List;

/**
 * DTO used by REST to receive JSON
 * Converted to a CreateOrderCommand before hitting the use case.
 */
public class CreateOrderRequest {

    public String customerName;
    public List<ItemDto> items;

    public CreateOrderCommand toCommand() {
        return new CreateOrderCommand(
                customerName,
                items.stream()
                        .map(i -> new CreateOrderCommand.ItemCommand(i.productId, i.quantity))
                        .toList()
        );
    }

    public static class ItemDto {
        public String productId;
        public int quantity;
    }
}
