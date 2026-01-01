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
            case PAID -> order.markAsPaid();
            case CANCELLED -> order.cancel();
        }

        return repository.save(order);
    }

    @Override
    public java.util.List<Order> list() {
        return repository.findAll();
    }

    public Optional<Order> findById(String id) {
        return repository.findById(id);
    }

}
