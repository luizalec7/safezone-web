package com.safezone.web.service;

import com.safezone.web.model.Ocorrencia;
import com.safezone.web.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

    public List<Ocorrencia> listarTodas() {
        return ocorrenciaRepository.findAll();
    }

    public Ocorrencia salvar(Ocorrencia ocorrencia) {
        return ocorrenciaRepository.save(ocorrencia);
    }

    public Optional<Ocorrencia> buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id);
    }

    public void excluir(Long id) {
        ocorrenciaRepository.deleteById(id);
    }
}