package drest.test.hackaton.application.port.in;

import drest.test.hackaton.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface ListOrdersUseCase {
    List<Order> list();
    Optional<Order> findById(String id);
}
