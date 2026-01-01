package drest.test.hackaton.application.port.in;

import drest.test.hackaton.domain.model.Order;
import java.util.List;

public interface ListOrdersUseCase {
    List<Order> list();
}
