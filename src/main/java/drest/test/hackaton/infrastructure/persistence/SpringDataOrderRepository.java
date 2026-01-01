package drest.test.hackaton.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataOrderRepository
        extends MongoRepository<OrderDocument, String> {}
