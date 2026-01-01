package drest.test.hackaton.application.port.out;

import drest.test.hackaton.domain.model.Order;

public interface OrderEventPublisherPort {

    void publishOrderCreated(Order order);
}
