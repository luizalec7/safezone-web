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
    public String listar(Model model) {
        model.addAttribute("campanhas", campanhaService.listarTodas());
        return "campanha/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("campanha", new CampanhaSolidaria());
        return "campanha/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute CampanhaSolidaria campanha, BindingResult result) {
        if (result.hasErrors()) {
            return "campanha/form";
        }
        campanhaService.salvar(campanha);
        return "redirect:/campanhas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        CampanhaSolidaria campanha = campanhaService.buscarPorId(id).orElseThrow();
        model.addAttribute("campanha", campanha);
        return "campanha/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable String id) {
        campanhaService.excluir(id);
        return "redirect:/campanhas";
    }
}