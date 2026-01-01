package drest.test.hackaton.infrastructure.persistence;

import drest.test.hackaton.application.port.out.OrderRepositoryPort;
import drest.test.hackaton.domain.model.Order;
import drest.test.hackaton.domain.model.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoOrderRepositoryAdapter implements OrderRepositoryPort {

    private final SpringDataOrderRepository repo;

    public MongoOrderRepositoryAdapter(SpringDataOrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public Order save(Order order) {

        OrderDocument doc = new OrderDocument();
        doc.setId(order.getId());
        doc.setCustomerName(order.getCustomerName());
        doc.setStatus(order.getStatus().name());
        doc.setItems(
                order.getItems()
                        .stream()
                        .map(i -> new OrderDocument.ItemDocument(i.getProductId(), i.getQuantity()))
                        .toList()
        );

        repo.save(doc);
        return order;
    }

    @Override
    public Optional<Order> findById(String id) {

        return repo.findById(id)
                .map(d -> new Order(
                        d.getCustomerName(),
                        d.getItems().stream()
                                .map(i -> new OrderItem(i.productId(), i.quantity()))
                                .toList()
                ));
    }

    @Override
    public List<Order> findAll() {

        return repo.findAll().stream()
                .map(d -> new Order(
                        d.getCustomerName(),
                        d.getItems().stream()
                                .map(i -> new OrderItem(i.productId(), i.quantity()))
                                .toList()
                ))
                .toList();
    }
}
