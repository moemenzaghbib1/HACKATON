package drest.test.hackaton.infrastructure.rest;

import drest.test.hackaton.application.port.in.ChangeOrderStatusUseCase;
import drest.test.hackaton.application.port.in.CreateOrderRequest;
import drest.test.hackaton.application.port.in.CreateOrderUseCase;
import drest.test.hackaton.application.port.in.ListOrdersUseCase;
import drest.test.hackaton.domain.model.Order;
import drest.test.hackaton.domain.model.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Adapter
 * Exposes HTTP endpoints and delegates work to application use cases.
 * (Domain does not depend on Spring)
 */
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
    public ResponseEntity<Order> create(@RequestBody CreateOrderRequest request) {
        Order order = createOrder.create(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> list() {
        return ResponseEntity.ok(listOrders.list());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable String id) {
        return listOrders.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> changeStatus(
            @PathVariable String id,
            @RequestParam OrderStatus status
    ) {
        return ResponseEntity.ok(changeStatus.changeStatus(id, status));
    }
}
