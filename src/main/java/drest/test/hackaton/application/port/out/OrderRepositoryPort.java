package drest.test.hackaton.application.port.out;

import drest.test.hackaton.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {

    Order save(Order order);

    Optional<Order> findById(String id);

    // 🔹 backend pagination
    Page<Order> findAll(Pageable pageable);

    // (optional legacy fallback)
    List<Order> findAll();
}
