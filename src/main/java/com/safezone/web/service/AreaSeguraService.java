package com.safezone.web.service;

import com.safezone.web.model.AreaSegura;
import com.safezone.web.repository.AreaSeguraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AreaSeguraService {

    private final AreaSeguraRepository areaSeguraRepository;

    public List<AreaSegura> listarTodas() {
        return areaSeguraRepository.findAll();
    }

    public AreaSegura salvar(AreaSegura area) {
        return areaSeguraRepository.save(area);
    }

    public Optional<AreaSegura> buscarPorId(Long id) {
        return areaSeguraRepository.findById(id);
    }

    public void excluir(Long id) {
        areaSeguraRepository.deleteById(id);
    }
}