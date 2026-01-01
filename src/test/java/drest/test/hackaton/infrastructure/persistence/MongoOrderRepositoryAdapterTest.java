package drest.test.hackaton.infrastructure.persistence;

import drest.test.hackaton.domain.model.Order;
import drest.test.hackaton.domain.model.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class MongoOrderRepositoryAdapterTest {

    @Autowired
    SpringDataOrderRepository repo;

    @Test
    void savesAndLoadsOrder() {

        MongoOrderRepositoryAdapter adapter = new MongoOrderRepositoryAdapter(repo);

        Order saved = adapter.save(
                new Order("Moemen", List.of(new OrderItem("p1", 2)))
        );

        assertTrue(adapter.findById(saved.getId()).isPresent());
    }
}
