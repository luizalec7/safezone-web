package com.safezone.web.controller;

import com.safezone.web.model.Ocorrencia;
import com.safezone.web.service.OcorrenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ocorrencias", ocorrenciaService.listarTodas());
        return "ocorrencia/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("ocorrencia", new Ocorrencia());
        return "ocorrencia/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Ocorrencia ocorrencia, BindingResult result) {
        if (result.hasErrors()) {
            return "ocorrencia/form";
        }
        ocorrenciaService.salvar(ocorrencia);
        return "redirect:/ocorrencias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(id).orElseThrow();
        model.addAttribute("ocorrencia", ocorrencia);
        return "ocorrencia/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        ocorrenciaService.excluir(id);
        return "redirect:/ocorrencias";
    }
}
