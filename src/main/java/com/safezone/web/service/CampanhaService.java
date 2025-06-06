package com.safezone.web.service;

import com.safezone.web.model.CampanhaSolidaria;
import com.safezone.web.repository.CampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;

    public List<CampanhaSolidaria> listarTodas() {
        return campanhaRepository.findAll();
    }

    public CampanhaSolidaria salvar(CampanhaSolidaria campanha) {
        return campanhaRepository.save(campanha);
    }

    public Optional<CampanhaSolidaria> buscarPorId(String id) {
        return campanhaRepository.findById(id);
    }

    public void excluir(String id) {
        campanhaRepository.deleteById(id);
    }
}