package com.safezone.web.repository;

import com.safezone.web.model.CampanhaSolidaria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampanhaRepository extends MongoRepository<CampanhaSolidaria, String> {
}