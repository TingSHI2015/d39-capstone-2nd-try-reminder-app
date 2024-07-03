package com.github.tingshi2015.backend.tips;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TipRepository extends MongoRepository<Tip, String> {
}
