package drest.test.hackaton.infrastructure.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsListener {

    @KafkaListener(topics = "orders.events", groupId = "orders-group")
    public void onMessage(String message) {
        // In a real app: log, trigger workflow, etc.
        System.out.println("Received event: " + message);
    }
}
