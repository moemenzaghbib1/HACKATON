package drest.test.hackaton.infrastructure.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderConsumer {

    @KafkaListener(
            topics = "orders.events",
            groupId = "orders-group"
    )
    public void onMessage(Object message) {
        System.out.println("Received event: " + message);
    }
}
