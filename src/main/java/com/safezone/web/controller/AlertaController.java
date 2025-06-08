package com.safezone.web.controller;

import com.safezone.web.model.Alerta;
import com.safezone.web.service.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/alerts")
public class AlertaController {

    private final AlertaService alertaService;

    // Exibe a página de alertas
    @GetMapping
    public String exibirAlertas(Model model) {
        model.addAttribute("alertas", alertaService.listarTodos());
        return "alerts"; // Referencia a view alerts.html
    }

    // Adiciona um novo alerta
    @PostMapping
    public String adicionarAlerta(@RequestParam String type, @RequestParam String description, @RequestParam String location) {
        Alerta alerta = new Alerta(type, description, location);
        alertaService.salvarAlerta(alerta);
        return "redirect:/alerts"; // Redireciona para a página de alertas
    }
}