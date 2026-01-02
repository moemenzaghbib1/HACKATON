package drest.test.hackaton.infrastructure.kafka;

import drest.test.hackaton.application.port.out.OrderEventPublisherPort;
import drest.test.hackaton.domain.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderEventPublisher implements OrderEventPublisherPort {

    private static final String TOPIC = "orders.events";
    private final KafkaTemplate<String, Object> kafka;

    public KafkaOrderEventPublisher(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    @Override
    public void publishOrderCreated(Order order) {
        kafka.send(TOPIC, new OrderEvent("ORDER_CREATED", order.getId()));
    }

    @Override
    public void publishOrderPaid(Order order) {
        kafka.send(TOPIC, new OrderEvent("ORDER_PAID", order.getId()));
    }

    @Override
    public void publishOrderCancelled(Order order) {
        kafka.send(TOPIC, new OrderEvent("ORDER_CANCELLED", order.getId()));
    }
}
