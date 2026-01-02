package drest.test.hackaton.infrastructure.kafka;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class OrderEventListenerTest {

    @Test
    void handlesEventWithoutCrashing() {

        OrderEventsListener listener = new OrderEventsListener();

        OrderEvent event = new OrderEvent(
                "ORDER_CREATED",
                "test-order-id"
        );

        assertDoesNotThrow(() -> listener.listen(event));
    }
}
