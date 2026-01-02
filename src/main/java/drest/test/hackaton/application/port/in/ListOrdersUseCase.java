package drest.test.hackaton.application.port.in;

import drest.test.hackaton.domain.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ListOrdersUseCase {
    Page<Order> list(int page, int size);
    Optional<Order> findById(String id);
}
