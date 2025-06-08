package com.safezone.web.service;

import com.safezone.web.model.Alerta;
import com.safezone.web.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;

    // Salva o alerta
    public void salvarAlerta(Alerta alerta) {
        alertaRepository.save(alerta);
    }

    // Lista todos os alertas
    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }
}
