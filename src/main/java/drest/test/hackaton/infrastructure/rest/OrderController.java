package drest.test.hackaton.infrastructure.rest;

import drest.test.hackaton.application.port.in.*;
import drest.test.hackaton.domain.model.Order;
import drest.test.hackaton.domain.model.OrderStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrder;
    private final ListOrdersUseCase listOrders;
    private final ChangeOrderStatusUseCase changeStatus;

    public OrderController(CreateOrderUseCase createOrder,
                           ListOrdersUseCase listOrders,
                           ChangeOrderStatusUseCase changeStatus) {
        this.createOrder = createOrder;
        this.listOrders = listOrders;
        this.changeStatus = changeStatus;
    }

    @PostMapping
    public Order create(@RequestBody CreateOrderCommand command) {
        return createOrder.create(command);
    }

    @GetMapping
    public List<Order> list() {
        return listOrders.list();
    }

    @PatchMapping("/{id}/status")
    public Order changeStatus(@PathVariable String id,
                              @RequestParam OrderStatus status) {
        return changeStatus.changeStatus(id, status);
    }
}
