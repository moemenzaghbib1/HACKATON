package drest.test.hackaton.infrastructure.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsListener {

    private static final Logger log = LoggerFactory.getLogger(OrderEventsListener.class);

    @KafkaListener(topics = "orders.events", groupId = "debug-listener")
    public void listen(OrderEvent event) {
        log.info("🎧 Event: {} - {}",
                event.getOrderId(),
                event.getType()
        );
    }
}
