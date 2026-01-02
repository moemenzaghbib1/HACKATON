package drest.test.hackaton.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void createsOrderWithCreatedStatus() {
        Order order = new Order(
                "Moemen",
                List.of(new OrderItem("p1", 2))
        );

        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void failsWhenNoCustomerName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order("", List.of(new OrderItem("p1", 1))));
    }

    @Test
    void failsWhenEmptyItems() {
        assertThrows(IllegalArgumentException.class,
                () -> new Order("Moemen", List.of()));
    }

    @Test
    void cannotPayOrderTwice() {
        Order order = new Order("Moemen", List.of(new OrderItem("p1", 1)));

        order.markAsPaid();

        assertThrows(IllegalStateException.class, order::markAsPaid);
    }

    @Test
    void cannotCancelPaidOrder() {
        Order order = new Order("Moemen", List.of(new OrderItem("p1", 1)));

        order.markAsPaid();

        assertThrows(IllegalStateException.class, order::cancel);
    }
}
