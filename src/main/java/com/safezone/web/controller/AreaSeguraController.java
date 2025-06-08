package com.safezone.web.controller;

import com.safezone.web.model.AreaSegura;
import com.safezone.web.service.AreaSeguraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/areas-seguras")
@RequiredArgsConstructor
public class AreaSeguraController {

    private final AreaSeguraService areaSeguraService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("areas", areaSeguraService.listarTodas());
        model.addAttribute("areaSegura", new AreaSegura());
        return "safe-areas";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute AreaSegura areaSegura, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("areas", areaSeguraService.listarTodas());
            return "safe-areas";
        }
        areaSeguraService.salvar(areaSegura);
        return "redirect:/areas-seguras";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        AreaSegura areaSegura = areaSeguraService.buscarPorId(id).orElseThrow();
        model.addAttribute("areaSegura", areaSegura);
        model.addAttribute("areas", areaSeguraService.listarTodas());
        return "safe-areas";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable String id) {
        areaSeguraService.excluir(id);
        return "redirect:/areas-seguras";
    }
}