package drest.test.hackaton.infrastructure.kafka;

import drest.test.hackaton.application.port.out.OrderEventPublisherPort;
import drest.test.hackaton.domain.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderPublisher implements OrderEventPublisherPort {

    private final KafkaTemplate<String, Object> template;

    public KafkaOrderPublisher(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    @Override
    public void publishOrderCreated(Order order) {
        template.send("orders.events", order.getId(), order);
    }
}
