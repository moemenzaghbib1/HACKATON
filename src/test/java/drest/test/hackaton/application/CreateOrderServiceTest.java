package drest.test.hackaton.application;

import drest.test.hackaton.application.port.in.CreateOrderCommand;
import drest.test.hackaton.application.port.out.OrderRepositoryPort;
import drest.test.hackaton.application.port.out.OrderEventPublisherPort;
import drest.test.hackaton.application.service.OrderService;
import drest.test.hackaton.domain.model.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateOrderServiceTest {

    OrderRepositoryPort repo = mock(OrderRepositoryPort.class);
    OrderEventPublisherPort events = mock(OrderEventPublisherPort.class);

    OrderService service = new OrderService(repo, events);

    @Test
    void createsOrderAndPersistsIt() {

        CreateOrderCommand cmd = new CreateOrderCommand(
                "Moemen",
                List.of(new CreateOrderCommand.ItemCommand("p1", 2))
        );

        Order result = service.create(cmd);

        verify(repo).save(result);
        verify(events).publishOrderCreated(result);

        assertEquals("Moemen", result.getCustomerName());
    }
}
