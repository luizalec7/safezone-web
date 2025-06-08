package com.safezone.web.repository;

import com.safezone.web.model.AreaSegura;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AreaSeguraRepository extends MongoRepository<AreaSegura, String> {
}