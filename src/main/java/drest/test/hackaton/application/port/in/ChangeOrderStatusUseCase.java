package drest.test.hackaton.application.port.in;

import drest.test.hackaton.domain.model.Order;
import drest.test.hackaton.domain.model.OrderStatus;

public interface ChangeOrderStatusUseCase {
    Order changeStatus(String orderId, OrderStatus status);
}
