package com.grava.repository;

import com.grava.model.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Consumer, String> {
}
