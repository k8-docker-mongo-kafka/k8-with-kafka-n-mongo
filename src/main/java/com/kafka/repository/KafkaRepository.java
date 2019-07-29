package com.kafka.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kafka.model.DomainEvent;

@Repository
public interface KafkaRepository extends MongoRepository<DomainEvent, UUID> {

}
