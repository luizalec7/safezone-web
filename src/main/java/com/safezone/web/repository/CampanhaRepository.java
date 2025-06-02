package com.safezone.web.repository;

import com.safezone.web.model.CampanhaSolidaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampanhaRepository extends JpaRepository<CampanhaSolidaria, Long> {
}