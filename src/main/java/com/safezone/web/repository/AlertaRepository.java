package com.safezone.web.repository;

import com.safezone.web.model.Alerta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends MongoRepository<Alerta, String> {
}
