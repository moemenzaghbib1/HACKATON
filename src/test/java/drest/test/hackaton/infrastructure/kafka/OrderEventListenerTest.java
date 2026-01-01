package drest.test.hackaton.infrastructure.kafka;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class OrderEventListenerTest {

    @Test
    void handlesEventWithoutCrashing() {

        OrderEventsListener listener = new OrderEventsListener();

        assertDoesNotThrow(() -> listener.onMessage("TEST_EVENT"));
    }
}
