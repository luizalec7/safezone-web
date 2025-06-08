package com.safezone.web.controller;

import com.safezone.web.model.CampanhaSolidaria;
import com.safezone.web.service.CampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/campanhas")
@RequiredArgsConstructor
public class CampanhaController {

    private final CampanhaService campanhaService;

    @GetMapping
    public String listarCampanhas(Model model) {
        model.addAttribute("campanha", new CampanhaSolidaria());
        model.addAttribute("campanhas", campanhaService.listarTodas());
        return "campaigns"; // Certifique-se de que o nome do HTML é campaigns.html
    }

    @PostMapping("/salvar")
    public String salvarCampanha(@Valid @ModelAttribute("campanha") CampanhaSolidaria campanha,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("campanhas", campanhaService.listarTodas());
            return "campaigns";
        }
        campanhaService.salvar(campanha);
        return "redirect:/campanhas";
    }

    @GetMapping("/editar/{id}")
    public String editarCampanha(@PathVariable String id, Model model) {
        CampanhaSolidaria campanha = campanhaService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("campanha", campanha);
        model.addAttribute("campanhas", campanhaService.listarTodas());
        return "campaigns";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCampanha(@PathVariable String id) {
        campanhaService.excluir(id);
        return "redirect:/campanhas";
    }
}