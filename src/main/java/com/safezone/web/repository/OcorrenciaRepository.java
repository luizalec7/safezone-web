package com.safezone.web.repository;

import com.safezone.web.model.Ocorrencia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OcorrenciaRepository extends MongoRepository<Ocorrencia, String> {
}
