package drest.test.hackaton.application.port.in;

import drest.test.hackaton.application.port.in.CreateOrderCommand;
import drest.test.hackaton.domain.model.Order;

public interface CreateOrderUseCase {
    Order create(CreateOrderCommand command);
}
