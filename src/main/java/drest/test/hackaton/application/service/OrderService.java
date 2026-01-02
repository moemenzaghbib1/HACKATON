package drest.test.hackaton.application.service;

import drest.test.hackaton.application.port.in.ChangeOrderStatusUseCase;
import drest.test.hackaton.application.port.in.CreateOrderCommand;
import drest.test.hackaton.application.port.in.CreateOrderUseCase;
import drest.test.hackaton.application.port.in.ListOrdersUseCase;
import drest.test.hackaton.application.port.out.OrderEventPublisherPort;
import drest.test.hackaton.application.port.out.OrderRepositoryPort;
import drest.test.hackaton.domain.model.Order;
import drest.test.hackaton.domain.model.OrderItem;
import drest.test.hackaton.domain.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements
        CreateOrderUseCase,
        ChangeOrderStatusUseCase,
        ListOrdersUseCase {

    private final OrderRepositoryPort repository;
    private final OrderEventPublisherPort publisher;

    public OrderService(OrderRepositoryPort repository,
                        OrderEventPublisherPort publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Override
    public Order create(CreateOrderCommand command) {

        var items = command.items()
                .stream()
                .map(i -> new OrderItem(i.productId(), i.quantity()))
                .collect(Collectors.toList());

        Order order = new Order(command.customerName(), items);

        repository.save(order);
        publisher.publishOrderCreated(order);

        return order;
    }

    @Override
    public Order changeStatus(String id, OrderStatus status) {

        Order order = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        switch (status) {
            case PAID -> {
                order.markAsPaid();
                publisher.publishOrderPaid(order);
            }
            case CANCELLED -> {
                order.cancel();
                publisher.publishOrderCancelled(order);
            }
            default -> throw new IllegalArgumentException("Unsupported status: " + status);
        }

        // ✅ correct repository field
        repository.save(order);

        return order;
    }

    @Override
    public java.util.List<Order> list() {
        return repository.findAll();
    }

    public Optional<Order> findById(String id) {
        return repository.findById(id);
    }
}
